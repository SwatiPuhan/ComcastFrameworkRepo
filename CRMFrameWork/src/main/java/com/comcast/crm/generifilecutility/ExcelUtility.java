package com.comcast.crm.generifilecutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.google.common.collect.Table.Cell;

public class ExcelUtility {

	
	public String getDataFromExcel(String sheetName , int rowNum , int cellNum) throws Throwable {
		
		//to get the java object of the physical file
		FileInputStream fis=new FileInputStream("./testscriptdata/ContactCreateOrg.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data=wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		wb.close();
		return data;
		
		
	}
    public int getRowCount(String sheetName) throws Throwable
    {
    	FileInputStream fis=new FileInputStream("./testscriptdata/ContactCreateOrg.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		 Sheet sh = wb.getSheet(sheetName);
		int rowCount = sh.getLastRowNum();
		wb.close();
		return rowCount;
		
    	
    }
    public void setDataIntoExcel(String sheetName , int rowNum , int cellNum , String data) throws EncryptedDocumentException, IOException 
    
   {
    	FileInputStream fis=new FileInputStream("./testscriptdata/ContactCreateOrg.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
    	org.apache.poi.ss.usermodel.Cell cell = wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum);
    	cell.setCellType(CellType.STRING);
    	cell.setCellValue(data);
    	
    	
    	
    	FileOutputStream fos=new FileOutputStream("./testscriptdata/ContactCreateOr.xlsx");
    	
    	wb.write(fos);
    	wb.close();
   }
}
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    