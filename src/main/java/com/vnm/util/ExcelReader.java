package com.vnm.util;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	public ExcelReader(String filePath, String SheetName) {
		try {
			workbook = new XSSFWorkbook(filePath);
			sheet = workbook.getSheet(SheetName);
		} catch (IOException e) {
			System.out.println("Cannot find the file");
		}

	}

	public int getRowCount() {
		return sheet.getPhysicalNumberOfRows();
	}

	public int getColCount() {
		return sheet.getRow(0).getLastCellNum();
	}

	public String getCellData(int rowNum, int colNum) {
		return sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
	}

	public Object[][] getTestData() {
		Object data[][] = null;
		int rowCount = getRowCount();
		int colCount = getColCount();
		data = new Object[rowCount-1][colCount];
		for (int row = 1; row <rowCount; row++) {
			for (int col = 0; col < colCount; col++) {
				data[row-1][col] = getCellData(row, col);
			}
		}
		return data;
	}

}
