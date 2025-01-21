package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
		//DataProvider 1
		@DataProvider(name = "LoginData")
		public String [][] getData() throws IOException
		{
			String path = ".\\testData\\Diagnostic_Admin_Login.xlsx"; //taking excel file from test data
			
			ExcelUtility xlutil = new ExcelUtility(path); // creating an object for XLUtility
			
			int totalRows = xlutil.getRowCount("Sheet1");
			int totalCols = xlutil.getCellCount("Sheet1", 1);
			
			String logindata[][] = new String[totalRows][totalCols]; //created for two dimensional array
			
			for(int i=1; i<=totalRows; i++) //1 //read the data from xl storing in two dimensional array
			{
				for(int j=0; j<totalCols; j++) //0  //i is rows j is col
				{
					logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j);
				}
			}
			
		 return logindata;
		}

}
