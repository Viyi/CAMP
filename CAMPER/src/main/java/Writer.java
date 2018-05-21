import  java.io.*;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;
public class Writer {

	 public static void main(String[]args) {
	        try {
	            String filename = "NewExelFile.xls" ;
	            HSSFWorkbook workbook = new HSSFWorkbook();
	            HSSFSheet sheet = workbook.createSheet("FirstSheet");  

	            HSSFRow rowhead = sheet.createRow((short)0);
	            rowhead.createCell(0).setCellValue("Name");
	            rowhead.createCell(1).setCellValue("Gender");
	            rowhead.createCell(2).setCellValue("Grade");
	            rowhead.createCell(3).setCellValue("Cabin");
	            rowhead.createCell(4).setCellValue("Small Group");
	           

	            HSSFRow row = sheet.createRow((short)1);
	            row.createCell(0).setCellValue("Arthur");
	            row.createCell(1).setCellValue("Male");
	            row.createCell(2).setCellValue("13");
	            row.createCell(3).setCellValue("2");
	            row.createCell(4).setCellValue("4");

	            FileOutputStream fileOut = new FileOutputStream(filename);
	            workbook.write(fileOut);
	            fileOut.close();
	            workbook.close();
	            System.out.println("Your excel file has been generated!");

	        } catch ( Exception ex ) {
	            System.out.println(ex);
	        }
	    }
}
