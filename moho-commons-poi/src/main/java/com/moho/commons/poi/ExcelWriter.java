package com.moho.commons.poi;

import com.alibaba.fastjson.JSON;
import com.moho.commons.poi.annotation.Excel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;

/**
 * 生成Excel文件
 * <p>
 * <p>
 * <a href=""><i>View Source</i></a>
 *
 * @author chasdream
 * @version 1.0.0
 * @date 2018-11-24
 */
public class ExcelWriter {

    private final static String PATTERN = "yyyy-MM-dd hh:mm:ss";

    /**
     * 将list集合生成Excel文档
     *
     * @param list     写入Excel文档的数据集合
     * @param clazz    待处理的对象
     * @param fileName 导出文件名
     * @param <T>
     */
    public static <T> void generatorExcel(List<T> list, Class<T> clazz, String fileName) {

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("sheet1");

        Row row = sheet.createRow(0);

        //生成Excel标题栏
        int titleCount = 0;
        for (Field field : clazz.getDeclaredFields()) {

            Annotation[] annotations = field.getAnnotations();

            if (annotations.length > 0 && annotations[0] instanceof Excel) {
                Excel excel = (Excel) annotations[0];
                Cell cell = row.createCell(titleCount);
                cell.setCellValue(excel.title());
                titleCount++;
            }
        }

        //将list写入Excel文档
        if (null != list && !list.isEmpty()) {

            for (int i = 0; i <= list.size() - 1; i++) {

                T t = list.get(i);

                row = sheet.createRow(i + 1);

                int valueCount = 0;
                for (Field field : t.getClass().getDeclaredFields()) {

                    Annotation[] annotations = field.getAnnotations();

                    if (annotations.length > 0 && annotations[0] instanceof Excel) {

                        Excel excel = (Excel) annotations[0];

                        Cell cell = row.createCell(valueCount);

                        valueCount++;

                        try {

                            field.setAccessible(Boolean.TRUE);

                            if (null != field.get(t) && !"".equals(excel.convert())) {
                                cell.setCellValue(JSON.parseObject(excel.convert()).getString(field.get(t).toString()));
                                continue;
                            }

                            if (field.get(t) instanceof Date) {
                                cell.setCellValue(new SimpleDateFormat(PATTERN).format(field.get(t)));
                                continue;
                            }

                            if (field.get(t) instanceof LocalDateTime) {
                                cell.setCellValue(DateTimeFormatter.ofPattern(PATTERN).format((TemporalAccessor) field.get(t)));
                                continue;
                            }

                            if (null != field.get(t)) {
                                cell.setCellValue(field.get(t).toString());
                                continue;
                            }

                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        //通过io流将文档输出
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(fileName + ".xlsx"));
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.close();
                }
                if (null != workbook) {
                    workbook.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("excel writer stream close exception: " + e);
            }
        }
    }
}
