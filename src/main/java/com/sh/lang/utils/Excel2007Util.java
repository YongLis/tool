package com.sh.lang.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @Auther: liyong
 * @Date:2019/4/25 21:07
 * @Desc: 支持2007以上版本(单sheet最大行数104万，超过则内存溢出)
 */
public class Excel2007Util extends DefaultHandler {
    private static final int DEFAULT_WIDTH = 2560;

    private static final String DEFAULT_SHEETNAME = "Sheet 1";

    public final static String YYYYMMDDHHMMSS3 = "yyyy-MM-dd HH:mm:ss";

    private static Logger logger = LoggerFactory.getLogger(Excel2007Util.class);

    public static List<ExecSheetEntity> readExcelFile(InputStream inputStream){
        List<ExecSheetEntity> list = new ArrayList<>();
        OPCPackage opcPackage = null;
        try {
            // 创建输入流，读取Excel
            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {
                // 每个页签创建一个Sheet对象
                XSSFSheet sheet = wb.getSheetAt(index);
                ExecSheetEntity sheetEntity = new ExecSheetEntity();
                sheetEntity.setSheetName(sheet.getSheetName());
                // sheet.getRows()返回该页的总行数
                if(sheet.getLastRowNum() > 0){
                    XSSFRow row0 = sheet.getRow(0);
                    int colNum = row0.getPhysicalNumberOfCells();
                    List<String> title = new ArrayList<>();
                    for(int i=0; i < colNum; i++){
                        title.add( getCellValue(row0.getCell(i)));
                    }
                    sheetEntity.setTitle(title);
                    List<List<String>> dataList = new ArrayList<>();
                    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                        XSSFRow row = sheet.getRow(i);
                        if(row != null && row.getLastCellNum() > 0){
                            List<String> data = new ArrayList<>();
                            for (int j = 0; j < colNum; j++) {
                                XSSFCell cell = row.getCell(j);
                                data.add(getCellValue(cell));
                            }
                            dataList.add(data);
                        }
                    }
                    sheetEntity.setDataList(dataList);
                    list.add(sheetEntity);
                }
            }
        } catch (Exception e) {
            logger.error("read excel error", e);
        }
        finally {
            try {
                if(inputStream != null){
                    inputStream.close();
                }
            }catch (Exception e){
                logger.error("inputStream close error", e);
            }
        }
        return list;
    }

    protected static String getCellValue(XSSFCell cell){
        if(cell != null && cell.toString().length() > 0){
            if(Cell.CELL_TYPE_NUMERIC == cell.getCellType()){
                return String.valueOf(cell.getNumericCellValue()).trim();
            }
            if(Cell.CELL_TYPE_STRING == cell.getCellType()){
                return String.valueOf(cell.getStringCellValue()).trim();
            }
            if(Cell.CELL_TYPE_BOOLEAN == cell.getCellType()){
                return String.valueOf(cell.getBooleanCellValue()).trim();
            }
            logger.info("当前仅支持数字和字符串格式，excel包含不支持的cell类型(如公式)");
        }
        return "";
    }


    public static XSSFWorkbook getWorkBook(String fileName ,List<String> title, List<List<String>> dataList) throws Exception{
        if(dataList!= null && dataList.size() > 1000000){
            logger.info("下载量不允许超过100万");
            return null;
        }

        // 创建工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建工作表
        String sheetName = "";
        if(StringUtils.isNotEmpty(fileName)){
            sheetName=fileName;
        }
        else{
            sheetName="sheet1";
        }
        XSSFSheet sheet = workbook.createSheet(sheetName);
        addSheetData(title, dataList, sheet);

        return workbook;
    }


    public static XSSFWorkbook getMutilSheetWorkBook(List<ExecSheetEntity> sheetEntities) throws Exception{
        // 创建工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        if(!sheetEntities.isEmpty()){
            for(ExecSheetEntity execSheetEntity : sheetEntities){
                int row = 0;
                // 创建工作表
                XSSFSheet sheet = workbook.createSheet(StringUtils.isNotEmpty(execSheetEntity.getSheetName()) ? execSheetEntity.getSheetName(): "sheet1");
                // 添加首行
                List<String> title = execSheetEntity.getTitle();
                List<List<String>> dataList = execSheetEntity.getDataList();
                addSheetData(title, dataList, sheet);
            }
        }
        return workbook;
    }
    public static int appendSheetData(List<List<String>> dataList, XSSFSheet sheet, int lastRowNo){
        int row = lastRowNo;
        // 添加数据
        if(dataList != null){
            for (int j = 0; j < dataList.size(); j++)
            {
                List<String> data = dataList.get(j);
                XSSFRow rows = sheet.createRow(row+1+j);
                for (int col = 0; col < data.size(); col++)
                {
                    // 向工作表中添加数据
                    XSSFCell cell = rows.createCell(col);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(data.get(col));
                }
            }
        }
        return row+dataList.size();
    }


    protected static void addSheetData(List<String> title, List<List<String>> dataList, XSSFSheet sheet){
        int row = 0;
        if(title != null){
            XSSFRow rows = sheet.createRow(0);
            for(int i=0; i< title.size() ; i++){
                rows.createCell(i).setCellValue(title.get(i));
            }
            row++;
        }
        // 添加数据
        if(dataList != null){
            for (int j = 0; j < dataList.size(); j++)
            {
                List<String> data = dataList.get(j);
                XSSFRow rows = sheet.createRow(j+row);
                for (int col = 0; col < data.size(); col++)
                {
                    // 向工作表中添加数据
                    XSSFCell cell = rows.createCell(col);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(data.get(col));
                }
            }
        }
    }

    private static Object[][] buildVals(String[] fields, List<? extends Object> valueList) throws Exception {
        if (valueList == null || valueList.size() == 0) {
            return null;
        }
        Object[][] vals = new Object[valueList.size()][fields.length];

        for (int i = 0; i < vals.length; ++i) {
            Object obj = valueList.get(i);

            for (int k = 0; k < fields.length; ++k) {
                vals[i][k] = getValue(obj, fields[k]);
            }
        }

        return vals;
    }

    /**
     * @param sheetName sheet名称
     * @param headers 标题
     * @param fields 字段
     * @param valueList 数据
     */

    public static XSSFWorkbook generateGrid(String sheetName, String[] headers, String[] fields, List valueList) throws Exception {
        return generateGrid(sheetName, headers, buildVals(fields, valueList));
    }

    public static XSSFWorkbook generateGrid(String sheetName, String[] headers, Object[][] content) {
        return generateGrid((XSSFWorkbook) (new XSSFWorkbook()), (String) sheetName, headers, (Object[][]) content);
    }

    public static XSSFWorkbook generateLargeGrid(String sheetName, String[] headers, String[] fields, List<? extends Object> valueList) throws Exception {
        return generateLargeGrid(sheetName, headers, buildVals(fields, valueList));
    }

    public static XSSFWorkbook generateLargeGrid(String sheetName, String[] headers, Object[][] content) {
        return generateGrid((XSSFWorkbook) (new XSSFWorkbook()), (String) sheetName, headers, (Object[][]) content);
    }

    public static XSSFWorkbook generateGrid(XSSFWorkbook workbook, String sheetName, String[] headers, Object[][] content) {
        if (StringUtils.isEmpty(sheetName)) {
            sheetName = "Sheet 1";
        }

        int rowNumber = 0;
        Sheet sheet = workbook.createSheet(sheetName);
        Row headRow = sheet.createRow(rowNumber);
        Font headerFont = workbook.createFont();
//        headerFont.setBoldweight((short) 700);
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(headerFont);
        int[] widths = new int[headers.length];

        int i;
        for (i = 0; i < headers.length; i++) {
            Cell cell = headRow.createCell(i);
            cell.setCellValue(StringUtils.isEmpty(headers[i]) ? "" : headers[i]);
            cell.setCellStyle(headerStyle);
            widths[i] = headers[i] == null ? 2560 : headers[i].getBytes().length * 256;
        }
        rowNumber++;
        if (content != null && content.length > 0) {
            for (i = 0; i < content.length; i++) {
                Row row = sheet.createRow(rowNumber + i);

                for (int k = 0; k < headers.length; ++k) {
                    Cell cell = row.createCell(k);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(String.valueOf(content[i][k] == null ? "" : content[i][k]));
                    int len = content[i][k] == null ? 2560 : String.valueOf(content[i][k]).getBytes().length * 256;
                    if (len > widths[k]) {
                        widths[k] = len;
                    }
                }
            }
        }

        for (i = 0; i < widths.length; i++) {
            sheet.autoSizeColumn(i);
        }

        return workbook;
    }

    private static Object getValue(Object obj, String fieldName) throws Exception {
        Object result = null;
        if (obj instanceof Map) {
            Map<?, ?> m = (Map) obj;
            result = m.get(fieldName);
        } else if (fieldName.indexOf(".") != -1) {
            String f1 = fieldName.substring(0, fieldName.indexOf("."));
            String f2 = fieldName.substring(fieldName.indexOf(".") + 1);
            Field f = obj.getClass().getDeclaredField(f1);
            f.setAccessible(true);
            Object newObj = f.get(obj);
            result = getValue(newObj, f2);
        } else {
            Field f = obj.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            result = f.get(obj);
            if (result instanceof Date) {
                result = DateFormatUtils.format((Date) result, YYYYMMDDHHMMSS3);
            }
        }

        return result;
    }


}
