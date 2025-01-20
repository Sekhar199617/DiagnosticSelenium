package testCases;

import org.testng.annotations.Test;

import pageObjects.LoginPage;
import testBase.BaseClass;

public class ObserverLoginTest extends BaseClass {
	
	@Test
	public void verify_observer_login()
	{
		logger.info("****** Starting Observer Login Test Case ******");
		LoginPage lp = new LoginPage(driver);
		lp.enterEmail("dgshor@gmail.com");
		lp.enterPassword("dev@123");
		lp.clickOnLogin();
		logger.info("****** Ended Observer Login Test Case ******");
	}
}
