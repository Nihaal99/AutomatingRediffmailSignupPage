package com.Dropdown.excel;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelInput {
	// Creating array of string named customerData
	public static String[] customerData = new String[3];
	String excelFilePath = System.getProperty("user.dir") + "\\Book1.xlsx";

	public static String[] readExcelData(String excelFilePath) throws IOException {
		/*
		 * file object of FileInputStream accepts the excelFilePath as parameter which
		 * contains location of the Excel file
		 */
		FileInputStream file = new FileInputStream(excelFilePath);
		/*
		 * Object of XSSFWorkbook i.e. wb is created which accepts file as parameter
		 * which reads the excel file
		 */
		XSSFWorkbook wb = new XSSFWorkbook(file);

		XSSFSheet ws = wb.getSheet("Sheet1");
		XSSFRow row = ws.getRow(0);
		for (int colNo = 0; colNo <= 2; colNo++) {
			// storing data of excel sheet in customerData array
			customerData[colNo] = String.valueOf(row.getCell(colNo));
		}
		// Printing values of customerData array
		for (int colNo = 0; colNo <= 2; colNo++) {
			System.out.println(customerData[colNo]);
		}
		return customerData;
	}

}

// Do not change the class name

// Use this declaration to store values parsed from excel
