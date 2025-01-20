package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import testBase.BaseClass;

public class AdminLoginTest extends BaseClass {
	
	@Test
	public void verify_admin_login()
	{
		try {
			logger.info("****** Starting Admin Login Test Case ******");
			LoginPage lp = new LoginPage(driver);
			lp.enterEmail("avinash.jha@ksolves.com");
			lp.enterPassword("dev@123");
			lp.clickOnLogin();
		} catch(Exception e) {
			Assert.fail();
		}
		logger.info("****** Finished Admin Login Test Case ******");
	}

}
