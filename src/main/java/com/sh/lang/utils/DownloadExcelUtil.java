package com.sh.lang.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：yong.li
 * @date ：Created in 2021/1/26 10:24
 * @description：excel下载工具
 * @modified By：
 */
public class DownloadExcelUtil {
    private static Logger logger = LoggerFactory.getLogger(DownloadExcelUtil.class);

    public void download2003(String fileName, String[] headers, String[] fields, List valueList, HttpServletRequest request, HttpServletResponse response) {
        logger.info("下载关联查询列表");
        try {
            String newFileName = "";
            if(StringUtils.isNotEmpty(fileName)){
                newFileName = fileName + DateUtil.date2String(new Date(), DateUtil.YYYYMMDDHHMMSSSSS) + ".xls";
            }else{
                newFileName = DateUtil.date2String(new Date(), DateUtil.YYYYMMDDHHMMSSSSS) + ".xls";
            }

            fileName = DownLoadUtil.encodeFileName(fileName, request.getHeader("User-Agent"));
            Workbook grid = SimpleExcelGenerator.generateGrid(fileName, headers, fields, valueList);
            response.setContentType("application/force-download");
            response.setContentType("applicationnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            grid.write(response.getOutputStream());
        } catch (Exception e) {
            logger.info("下载关联查询列表异常,", e);
        }
    }

    /**
     *
     */
    public void download2007(String fileName, String[] headers, String[] fields, List valueList, HttpServletRequest request, HttpServletResponse response) {
        logger.info("下载关联查询列表");
        try {
            String newFileName = "";
            if(StringUtils.isNotEmpty(fileName)){
                newFileName = fileName + DateUtil.date2String(new Date(), DateUtil.YYYYMMDDHHMMSSSSS) + ".xlsx";
            }else{
                newFileName = DateUtil.date2String(new Date(), DateUtil.YYYYMMDDHHMMSSSSS) + ".xlsx";
            }

            fileName = DownLoadUtil.encodeFileName(fileName, request.getHeader("User-Agent"));
            XSSFWorkbook grid = Excel2007Util.generateGrid(fileName, headers, fields, valueList);
            response.setContentType("application/force-download");
            response.setContentType("applicationnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            grid.write(response.getOutputStream());
        } catch (Exception e) {
            logger.info("下载关联查询列表异常,", e);
        }
    }

}
