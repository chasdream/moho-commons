package com.moho.commons;

import com.moho.commons.poi.ExcelReader;
import junit.framework.TestCase;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;

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
}
