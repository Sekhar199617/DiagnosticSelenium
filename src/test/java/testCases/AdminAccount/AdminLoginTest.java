package testCases.AdminAccount;
import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.BaseClass;

public class AdminLoginTest extends BaseClass {

	@Test(groups= {"Smoke"})
	public void verify_admin_login()
	{
		try {
			logger.info("****** Starting Admin Login Test Case ******");
			
			String jsonPath = "./testData//adminLoginData.json";
			loadTestData(jsonPath);
			
			login(getTestData("adminEmail"), getTestData("adminPassword"), true);
			Thread.sleep(3000);
		} catch(Exception e) {
			Assert.fail();
		}
		logger.info("****** Finished Admin Login Test Case ******");
	}

}
