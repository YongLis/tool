package com.sh.lang.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sh.lang.model.SheetEntity;

/**
 * 仅支持2003格式excel 
 */
public class Excel2003Util {
	private static Logger logger = LoggerFactory.getLogger(Excel2003Util.class);
	/**
	 * 读取excel
	 */
	
	public static List<SheetEntity> readExcel(InputStream inputStream){
		
		List<SheetEntity> sheetList = new ArrayList<SheetEntity>();
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			if(workbook.getNumberOfSheets() > 0){
				for(int i=0; i < workbook.getNumberOfSheets(); i++){
					HSSFSheet sheet = workbook.getSheetAt(i);
					if(sheet != null){
						SheetEntity sheetEntity = new SheetEntity();
						sheetEntity.setSheetName(sheet.getSheetName());
						List<String[]> dataList = new ArrayList<String[]>();
						int cellCount = 0;
						// 读取行数据
						for(int k=0; k <= sheet.getLastRowNum(); k++){
							HSSFRow row = sheet.getRow(k);
							if(row != null){
								if(k == 0){
									String[] title = new String[row.getLastCellNum()];
									cellCount = row.getLastCellNum();
									// 读取首行
									for(int j=0; j<row.getLastCellNum(); j++){
										title[j] = getCellValue(row.getCell(j));
									}
									sheetEntity.setTitle(title);
								}
								else{
									String[] data = new String[cellCount];
									// 读取首行
									for(int j=0; j<row.getLastCellNum(); j++){
										data[j] = getCellValue(row.getCell(j));
									}
									
									dataList.add(data);
								}
							}
						}
						
						sheetEntity.setDataList(dataList);
						sheetList.add(sheetEntity);
					}
				}
			}
			
		} catch (Exception e) {
			logger.error("读取文件异常", e);
		}
		finally{
			try {
				if(inputStream != null){
					inputStream.close();
				}
			} catch (Exception e2) {
				logger.error("流关闭异常");
			}
		}
		return sheetList;
		
	}
	
	public static void createExcel(List<SheetEntity> list, String fileName){
		
		try {
			HSSFWorkbook workbook = getWorkbook(list);
			workbook.write(new FileOutputStream(new File(fileName)));
		} catch (Exception e) {
			logger.error("excel文件生成错误", e);
		}
		
	}
	
	
	public static HSSFWorkbook getWorkbook(List<SheetEntity> list){
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		if(list != null){
			for(SheetEntity sheetEntity : list){
				HSSFSheet sheet = null;
				if(StringUtils.isNoneEmpty(sheetEntity.getSheetName())){
					sheet = workbook.createSheet(sheetEntity.getSheetName());
				}
				else{
					sheet = workbook.createSheet();
				}
				int rowIndex = 0;
				if(sheetEntity.getTitle() != null && sheetEntity.getTitle().length > 0){
					createFirstRow(workbook, sheet, sheetEntity.getTitle());
					rowIndex++;
					
				}
				
				if(sheetEntity.getDataList() != null && sheetEntity.getDataList().size() > 0){
					for(int j=0; j<sheetEntity.getDataList().size(); j++){
						HSSFRow row = sheet.createRow(rowIndex+j);
						String[] data = sheetEntity.getDataList().get(j); 
						for(int k=0; k< data.length; k++){
							HSSFCell cell = row.createCell(k);
							cell.setCellValue(data[k]);
						}
					}
				}
				
			}
		}
		return workbook;
		
	}
	
	private static HSSFRow createFirstRow(HSSFWorkbook workbook, HSSFSheet sheet, String[] title){
		HSSFRow row = sheet.createRow(0);
		
		for(int i=0; i<title.length; i++){
			HSSFCell cell = row.createCell(i, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(title[i]);
		}
		
		return row;
	}
	
	
	private static String getCellValue(HSSFCell cell){
		if(cell != null){
			if(cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN){
				return cell.getBooleanCellValue()+"";
			}
			
			if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
				return cell.getNumericCellValue()+"";
			}
			
			if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
				return cell.getStringCellValue();
			}
			
		}
		
		return "";
	}

	public static void main(String[] args) {
//		SheetEntity sheetEntity = new SheetEntity();
//		sheetEntity.setSheetName("名单");
//		String[] title = {"编号","姓名", "年龄","性别"};
//		sheetEntity.setTitle(title);
//		List<String[]> data = new ArrayList<String[]>();	
//		for(int i=0; i<100; i++){
//			String[] tmp = new String[title.length];
//			tmp[0] = "stu"+i;
//			tmp[1] = "tom";
//			tmp[2] = "28";
//			tmp[3] = "男";
//			data.add(tmp);
//		}
//		
//		sheetEntity.setDataList(data);
//		List<SheetEntity> sheetEntities = new ArrayList<SheetEntity>();
//		sheetEntities.add(sheetEntity);
//		
//		createExcel(sheetEntities, "D:\\tmp\\cel"+new Random().nextInt()+".xls");
		
		 String filepath = "D:\\tmp\\Book2.xls";
         try {
			List<SheetEntity> list = readExcel(new FileInputStream(filepath));
			System.out.println(list.size());
			List<ExecSheetEntity> sheetEntities = new ArrayList<ExecSheetEntity>();
			for(int i=0;i<list.size(); i++){
				SheetEntity sheetEntity = list.get(i);
			}
			
			
			XSSFWorkbook xssfWorkbook = Excel2007Util.getMutilSheetWorkBook(sheetEntities);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
