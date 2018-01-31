/* 
 * Copyright (C), 2017-2018
 * File Name: @(#)
 * Encoding UTF-8
 * Author: chasdream
 * Version: 1.0
 * Date: 2018-01-31
 */
package com.moho.commons.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 读取excel文件内容
 * <p>
 * <p>
 * <a href=""><i>View Source</i></a>
 *
 * @author chasdream
 * @version 1.0
 * @date 2018-01-31
 * @since 1.0.0
 */
public class ExcelReader {

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    private ExcelReader() {
    }

    /**
     * 获取Workbook对象
     *
     * @param file 待读取的文件对象
     * @return
     */
    public static Workbook getWorkbook(File file) {
        FileInputStream inputStream = null;
        Workbook workbook = null;
        if (null == file) {
            throw new RuntimeException("file does not exist: file is null");
        }
        try {
            inputStream = new FileInputStream(file);
            switch (fileFormat(file)) {
                default:
                    throw new RuntimeException("file format error: " + file.toString());
                case XLS:
                    workbook = new HSSFWorkbook(inputStream);
                    break;
                case XLSX:
                    workbook = new XSSFWorkbook(inputStream);
                    break;
            }
            return workbook;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            close(workbook, inputStream);
        }
    }

    /**
     * 获取Sheet对象
     *
     * @param file 待读取的文件对象
     * @return
     */
    public static Sheet getSheet(File file) {
        return getSheet(file, 0);
    }

    /**
     * 获取Sheet对象
     *
     * @param file  待读取的文件对象
     * @param index 工作表位置
     * @return
     */
    public static Sheet getSheet(File file, Integer index) {
        if (index < 0) {
            throw new RuntimeException("the worksheet does not exist: " + index);
        }
        Sheet sheet = getWorkbook(file).getSheetAt(index);
        if (null == sheet) {
            throw new RuntimeException("the worksheet does not exist: " + index);
        }
        return sheet;
    }

    /**
     * 获取Sheet对象
     *
     * @param file      待读取的文件对象
     * @param sheetName 工作表名称
     * @return
     */
    public static Sheet getSheet(File file, String sheetName) {
        if (null == sheetName || "".equals(sheetName.trim())) {
            throw new RuntimeException("the worksheet does not exist: " + sheetName);
        }
        Sheet sheet = getWorkbook(file).getSheet(sheetName);
        if (null == sheet) {
            throw new RuntimeException("the worksheet does not exist: " + sheetName);
        }
        return sheet;
    }

    /**
     * 获取单元格值
     *
     * @param cell 单元格对象
     * @return
     */
    public static Object getCellValue(Cell cell) {
        if (null == cell) {
            throw new RuntimeException("the cell does not exist");
        }
        switch (cell.getCellTypeEnum()) {
            default:
                return null;
            case STRING://字符串
                return cell.getStringCellValue();
            case NUMERIC://数值
                return cell.getNumericCellValue();
            case FORMULA://公式
                cell.setCellType(CellType.STRING);
                return cell.getStringCellValue();
            case BLANK://null
                return null;
            case BOOLEAN://boolean
                return cell.getBooleanCellValue();
            case ERROR://在单元的误差值
                return cell.getErrorCellValue();
        }
    }

    /**
     * 获取文件格式
     */
    private static String fileFormat(File file) {
        String fileName = file.getName();
        String[] fileNames = fileName.split("\\.");
        if (null != fileNames && fileNames.length > 0) {
            return fileNames[fileNames.length - 1].toLowerCase();
        }
        return "";
    }

    /**
     * 关闭相关流
     */
    private static void close(Workbook workbook, FileInputStream in) {
        try {
            if (null != workbook) {
                workbook.close();
            }
            if (null != in) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
