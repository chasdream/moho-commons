package com.moho.commons;

import com.moho.commons.model.ExcelModel;
import com.moho.commons.poi.ExcelReader;
import com.moho.commons.poi.ExcelWriter;
import junit.framework.TestCase;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class ExcelReaderTest extends TestCase {

    public void testGetSheet() {
        File file = new File("C:\\Users\\haibo\\Desktop\\exceltest\\test.xlsx");
        Sheet sheet = ExcelReader.getSheet(file);
        int rowNum = sheet.getLastRowNum();
        for (int i = 0; i <= rowNum; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                cell.setCellType(CellType.STRING);
                System.out.println("第" + i + "行第" + j + "列: " + ExcelReader.getCellValue(cell));
            }
        }

    }

    public void testGeneratorExcel() {
        List<ExcelModel> list = new ArrayList<>();

        ExcelModel model = new ExcelModel();
        model.setDate(new Date());
        model.setDateTime(LocalDateTime.now());
        model.setId("1");
        model.setInt1(12);
        model.setStatus("1");
        model.setStr("str1");
        list.add(model);

        ExcelModel model1 = new ExcelModel();
        model1.setDate(new Date());
        model1.setDateTime(LocalDateTime.now());
        model1.setId("2");
        model1.setStatus("2");
        list.add(model1);

        ExcelWriter.generatorExcel(list, ExcelModel.class, "test");
    }
}
