package utilities;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;

public class DataProviders {
	
		//DataProvider 1
		@DataProvider(name = "LoginDataExcel")
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

        //DataProvider 2
        @DataProvider(name = "LoginData")
        public Object[][] readDataFromJson() throws Throwable {

            JSONParser jsonparser = new JSONParser();
            FileReader reader = new FileReader("./src//test//resources//logindata.json");

            JSONArray jsonArray = (JSONArray) jsonparser.parse(reader);
            Object[][] login_data = new Object[jsonArray.size()][3];

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject loginJsonObj = (JSONObject) jsonArray.get(i);
                String email = (String) loginJsonObj.get("email");
                String password = (String) loginJsonObj.get("password");
                String exp = (String) loginJsonObj.get("exp");
                login_data[i][0] = email;
                login_data[i][1] = password;
                login_data[i][2] = exp;
            }

            return login_data;
        }

}
