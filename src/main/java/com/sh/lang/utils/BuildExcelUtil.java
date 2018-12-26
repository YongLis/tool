package com.sh.lang.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BuildExcelUtil {

	/**
	 * 通过传入标题数组，属性数组和对象的list集合得到导出Excel值
	 * 
	 * @param head
	 *            标题
	 * @param bean
	 *            属性
	 * @param list
	 *            对象
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IntrospectionException
	 */
	public static XSSFWorkbook XSSFWorkbookExport(String[] head, String[] bean, List list) throws IllegalAccessException, InvocationTargetException,
			IntrospectionException {
		if (list == null) {
			return null;
		}

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("默认");
		XSSFRow row = sheet.createRow(0);// 页
		XSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);

		row = sheet.createRow(0);// 行

		for (int i = 0; head.length > i; i++) {
			row.createCell(i).setCellValue(head[i]);// 列
		}

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i + 1);
			Object o = list.get(i);
			Map mapBean = converBean(o);
			for (int j = 0; bean.length > j; j++) {
				Object data = mapBean.get(bean[j]);
				if (data != null && data instanceof Date) {// 判断是否是时间属性
					try {
						row.createCell(j).setCellValue(DateFormatTime((Date)data));
					} catch (ParseException e) {
						row.createCell(j).setCellValue(data.toString());// 列
					}
				} else {
					row.createCell(j).setCellValue(data == null ? "" : data.toString());// 列
				}
			}
		}
		return wb;
	}

	/**
	 * 时间格式化
	 * 
	 * @param EnglishDate
	 * @return
	 * @throws ParseException
	 */
	private static String DateFormatTime(Date date) throws ParseException {
		if(date==null){
			return "";
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/**
	 * Map转化为JavaBean
	 * 
	 * @param type
	 *            类型
	 * @param map
	 *            集合
	 * @return 对象
	 * @throws IntrospectionException
	 *             分析属性
	 * @throws IllegalAccessException
	 *             实例化
	 * @throws InstantiationException
	 *             实例化
	 * @throws InvocationTargetException
	 *             调用setter
	 * @throws IllegalArgumentException
	 */
	private static Object converMap(Class type, Map map) throws IntrospectionException, InstantiationException, InvocationTargetException,
			IllegalAccessException, IllegalArgumentException {
		BeanInfo beanInfo = Introspector.getBeanInfo(type);// 类属性
		Object obj = type.newInstance();// 创建Bean对象
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();// bean对象赋值
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (map.containsKey(propertyName)) {
				Object value = map.get(propertyName);
				Object[] args = new Object[1];
				args[0] = value;
				descriptor.getWriteMethod().invoke(obj, args);
			}
		}
		return obj;
	}

	/**
	 * Bean对象转化为Map
	 * 
	 * @param bean
	 *            JavaBean对象
	 * @return Map值
	 * @throws IntrospectionException
	 *             分析类属性
	 * @throws IllegalAccessException
	 *             实例化JavaBean
	 * @throws InvocationTargetException
	 *             调用setter
	 */
	private static Map converBean(Object bean) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
		Map returnMap = new HashMap();
		if(bean instanceof Map){
			Map map=(Map) bean;
			returnMap.putAll(map);
			return returnMap;
		}
		Class type = bean.getClass();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);// 类属性
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();// bean对象赋值
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null) {
					returnMap.put(propertyName, result);
				} else {
					returnMap.put(propertyName, "");
				}
			}
		}
		return returnMap;
	}
}
