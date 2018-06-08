package com.unknown.CAMP;

import  java.io.*;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;
public class Writer {
	 HSSFWorkbook workbook = new HSSFWorkbook();
     HSSFSheet sheet = workbook.createSheet("FirstSheet");    
	
	private int rowNum = 1;
	public Writer() {
		 
         HSSFRow rowhead = sheet.createRow((short)0);
         rowhead.createCell(0).setCellValue("Name");
         rowhead.createCell(1).setCellValue("Gender");
         rowhead.createCell(2).setCellValue("Cabin");
         rowhead.createCell(3).setCellValue("Small Group");
         rowhead.createCell(4).setCellValue("First Request");
         rowhead.createCell(5).setCellValue("Second Request");
	}
	
	public void endSpreadsheet(String fName) throws Exception {
		String filename = fName ;
		
		FileOutputStream fileOut = new FileOutputStream(filename);

		workbook.write(fileOut);
         fileOut.close();
         workbook.close();
         System.out.println("File has been generated!");
	}
	
	public void writeCamper(Camper c) {
		
		HSSFRow row = sheet.createRow((short)rowNum);
         row.createCell(0).setCellValue(c.getName());
         if(c.getGender() == 2) {
        	 row.createCell(1).setCellValue("Female");
         }else {
        	 row.createCell(1).setCellValue("Male");
         }
         
        
         row.createCell(2).setCellValue(c.getCabin());
         row.createCell(3).setCellValue(c.getGroup());
         row.createCell(4).setCellValue(c.getRequest());
         row.createCell(5).setCellValue(c.getRequest2());
         rowNum++;
	}
	
	
	
	 public static void main(String[]args) {
		 Writer w = new Writer();
	       w.writeCamper(null);
	       w.writeCamper(null);
	       try {
		//	w.endSpreadsheet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
}

