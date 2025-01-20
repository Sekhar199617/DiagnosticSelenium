package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import testBase.BaseClass;

public class ObserverLoginTest extends BaseClass {
	
	@Test(groups= {"Smoke"})
	public void verify_observer_login()
	{
		try
		{
			logger.info("****** Starting Observer Login Test Case ******");
			login(p.getProperty("observerEmail"), p.getProperty("observerPassword"), false); 
			logger.info("****** Ended Observer Login Test Case ******");
		}catch (Exception e)
		{
			Assert.fail();
		}
		logger.info("****** Finished Observer Login Test Case ******");
		
	}
}
