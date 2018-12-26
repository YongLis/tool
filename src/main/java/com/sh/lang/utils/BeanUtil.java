package com.sh.lang.utils;


import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.expression.Resolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *org.apache.commons.beanutils.BeanUtils.copyProperties 复制枚举类型报错，现在跳过
 */
public class BeanUtil {
	static{
		DateConverter dateConverter = new DateConverter();
		 dateConverter.setUseLocaleFormat(false);
		dateConverter.setLocale(Locale.CHINESE);
		dateConverter.setPatterns(new String[] { DateUtil.YYYY_MM_DD,
				DateUtil.YYYY_MM_DD_HHMMSS, DateUtil.YYYYMMDD,
				DateUtil.YYYYMMDD_HHMMSS, DateUtil.YYYYMMDDHHMMSS });
		ConvertUtils.register(dateConverter, Date.class);
	}
	private static final Logger log = LoggerFactory.getLogger(BeanUtil.class);

	/**
	 * 仿照 BeanUtilsBean.getInstance().copyProperties(dest, orig);
	 * 
	 * @param dest
	 * @param orig
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ParseException
	 */
	public static void copyProperties(Object dest, Object orig)
			throws IllegalAccessException, InvocationTargetException,
			ParseException {
		BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
		// Validate existence of the specified beans
		if (dest == null) {
			throw new IllegalArgumentException("No destination bean specified");
		}
		if (orig == null) {
			throw new IllegalArgumentException("No origin bean specified");
		}
		if (log.isDebugEnabled()) {
			log.debug("BeanUtils.copyProperties(" + dest + ", " + orig + ")");
		}

		// Copy the properties, converting as necessary
		if (orig instanceof DynaBean) {
			DynaProperty[] origDescriptors = ((DynaBean) orig).getDynaClass()
					.getDynaProperties();
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				// Need to check isReadable() for WrapDynaBean
				// (see Jira issue# BEANUTILS-61)
				if (beanUtilsBean.getPropertyUtils().isReadable(orig, name)
						&& beanUtilsBean.getPropertyUtils().isWriteable(dest,
								name)) {
					Object value = ((DynaBean) orig).get(name);
					copyProperty(beanUtilsBean, dest, name, value);
				}
			}
		} else if (orig instanceof Map) {
			Iterator entries = ((Map) orig).entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry entry = (Map.Entry) entries.next();
				String name = (String) entry.getKey();
				if (beanUtilsBean.getPropertyUtils().isWriteable(dest, name)) {
					copyProperty(beanUtilsBean, dest, name, entry.getValue());
				}
			}
		} else /* if (orig is a standard JavaBean) */{
			PropertyDescriptor[] origDescriptors = beanUtilsBean
					.getPropertyUtils().getPropertyDescriptors(orig);
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				if ("class".equals(name)) {
					continue; // No point in trying to set an object's class
				}
				if (beanUtilsBean.getPropertyUtils().isReadable(orig, name)
						&& beanUtilsBean.getPropertyUtils().isWriteable(dest,
								name)) {
					try {
						Object value = beanUtilsBean.getPropertyUtils()
								.getSimpleProperty(orig, name);
						copyProperty(beanUtilsBean, dest, name, value);
					} catch (NoSuchMethodException e) {
						// Should not happen
					}
				}
			}
		}

	}

	private static void copyProperty(BeanUtilsBean beanUtilsBean, Object bean,
			String name, Object value) throws IllegalAccessException,
			InvocationTargetException, ParseException {

   	 // Resolve any nested expression to get the actual target bean
       Object target = bean;
       Resolver resolver = beanUtilsBean.getPropertyUtils().getResolver();
       while (resolver.hasNested(name)) {
           try {
               target = beanUtilsBean.getPropertyUtils().getProperty(target, resolver.next(name));
               name = resolver.remove(name);
           } catch (NoSuchMethodException e) {
               return; // Skip this property setter
           }
       }
       if (log.isTraceEnabled()) {
           log.trace("    Target bean = " + target);
           log.trace("    Target name = " + name);
       }

       // Declare local variables we will require
       String propName = resolver.getProperty(name); // Simple name of target property
       Class type = null;                            // Java type of target property
       int index  = resolver.getIndex(name);         // Indexed subscript value (if any)
       String key = resolver.getKey(name);           // Mapped key value (if any)

       // Calculate the target property type
       if (target instanceof DynaBean) {
           DynaClass dynaClass = ((DynaBean) target).getDynaClass();
           DynaProperty dynaProperty = dynaClass.getDynaProperty(propName);
           if (dynaProperty == null) {
               return; // Skip this property setter
           }
           type = dynaProperty.getType();
       } else {
           PropertyDescriptor descriptor = null;
           try {
               descriptor =
               		beanUtilsBean.getPropertyUtils().getPropertyDescriptor(target, name);
               if (descriptor == null) {
                   return; // Skip this property setter
               }
           } catch (NoSuchMethodException e) {
               return; // Skip this property setter
           }
           type = descriptor.getPropertyType();
           if (type == null) {
               // Most likely an indexed setter on a POJB only
               if (log.isTraceEnabled()) {
                   log.trace("    target type for property '" +
                             propName + "' is null, so skipping ths setter");
               }
               return;
           }
       }
   if(Enum.class.isAssignableFrom(type)&&!(value instanceof Enum)){
	   copyEnumProperty(bean, name, value, type);
   }else{
   	beanUtilsBean.copyProperty(bean, name, value);
   }
       
   }

	private static void copyEnumProperty(Object bean, String name, Object value,
			Class type) throws IllegalAccessException,
			InvocationTargetException, ParseException {
		if (value instanceof String) {
			Method m =null;
			try {
				m= type.getMethod("byTypeCode",
						String.class);
			} catch (Exception e) {
			    log.error("错误", e);
				try {
					m= type.getMethod("byCode",
							String.class);
				} catch (Exception e1) {
				    log.error("错误", e);
				}
			}
			if (m != null) {
				Object v = m.invoke(type, value);
				if (v != null) {
					BeanUtil.copyProperty(bean, name, v);
				}
			}
		} else if (value instanceof Number) {
			Method m=null;
			try {
				m = type.getMethod("byTypeNo",
						Integer.class);
			} catch (Exception e) {
			    log.error("错误", e);
			}
			if (m != null) {
				Object v = m.invoke(type, value);
				if (v != null) {
					BeanUtil.copyProperty(bean, name, v);
				}
			}
		}
	}

	/**
	 * BeanUtilsBean.getInstance().copyProperty(bean, name, value);
	 * 
	 * @param bean
	 * @param name
	 * @param value
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ParseException
	 */
	private static void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException,
			ParseException {
		BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
		copyProperty(beanUtilsBean, bean, name, value);
	}
}
