package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	public static String readExcel(int i,int j) throws IOException  {
		  File file=new File(System.getProperty("user.dir")+ "\\ExcelData\\Emicalculator.xlsx");
			FileInputStream readFile=new FileInputStream(file);
			 XSSFWorkbook wb=new XSSFWorkbook(readFile);
			 XSSFSheet sheet=wb.getSheet("Information");
			Cell cell1=sheet.getRow(i).getCell(j);
				DataFormatter formatter= new DataFormatter();
			   String amount1= formatter.formatCellValue(cell1);
			   wb.close();
			 return amount1;

}
}
