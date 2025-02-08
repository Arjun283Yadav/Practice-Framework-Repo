package com.automation.utils;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

	public static InputStream fis;
	public static Workbook wbook;
	public static Sheet sheet;
	public static Row row;
	public static Cell cell;

	
	public static String getCellValue(String filePath, String sheetName, int rowNo, int cellNo) {
		String cellValue = null;
		try {
			fis =new FileInputStream(filePath);
			wbook = new XSSFWorkbook(fis);
			sheet = wbook.getSheet(sheetName);
			row = sheet.getRow(rowNo);
			cell = row.getCell(cellNo, MissingCellPolicy.CREATE_NULL_AS_BLANK);
//			cellValue = cell.getStringCellValue();
			 if (cell.getCellType()==CellType.STRING) {
				 cellValue = cell.getStringCellValue();
				} else if(cell.getCellType()==CellType.NUMERIC){
					Double dobj = cell.getNumericCellValue();
					Integer iobj = dobj.intValue();
					cellValue = iobj.toString();
				}
			wbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellValue;
	}
	
	public static int getRowCount(String filePath, String sheetName) {
		int totalRowCount = -1;
		try {
			fis = new FileInputStream(filePath);
			wbook = new XSSFWorkbook(fis);
			sheet = wbook.getSheet(sheetName);
			totalRowCount = sheet.getPhysicalNumberOfRows();
			wbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalRowCount;
	}
	
	public static int getColoumCount(String filePath, String sheetName) {
		int totalCellCount = -1;
		try {
			fis = new FileInputStream(filePath);
			wbook = new XSSFWorkbook(fis);
			sheet = wbook.getSheet(sheetName);
			row = sheet.getRow(0);
			totalCellCount = row.getLastCellNum();
			wbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalCellCount;
	}
	
}
