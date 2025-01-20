package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import testBase.BaseClass;

public class AdminLoginTest extends BaseClass {
	
	@Test(groups= {"Smoke"})
	public void verify_admin_login()
	{
		try {
			logger.info("****** Starting Admin Login Test Case ******");
			login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);
			Thread.sleep(3000);
		} catch(Exception e) {
			Assert.fail();
		}
		logger.info("****** Finished Admin Login Test Case ******");
	}

}
