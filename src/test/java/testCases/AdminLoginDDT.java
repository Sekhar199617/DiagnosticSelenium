package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;
import utilities.DataProviders;

public class AdminLoginDDT extends BaseClass {
	
	CommonUtils commonUtils;
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class)
	public void verify_loginDDT(String email, String password, String exp)
	{
		logger.info("****** Starting Admin Login DDT ******");
		try
		{
			login(email, password, true);
			
			DashboardPage dp = new DashboardPage(driver);
			boolean targetPage = dp.isMyAccountExists();
			
			LoginPage loginPage = new LoginPage(driver);
			commonUtils = new CommonUtils(driver);
			
			if(exp.equalsIgnoreCase("valid"))
			{
				if(targetPage)
				{
					commonUtils.clickOnElement(dp.togglerIcon, null);
					commonUtils.clickOnElement(dp.logoutButton, null);
					Assert.assertTrue(true);
				}else
				{
                    Assert.fail();
				}
			}
			
			if(exp.equalsIgnoreCase("invalid"))
			{
				if(targetPage)	{
					commonUtils.clickOnElement(dp.togglerIcon, null);
					commonUtils.clickOnElement(dp.logoutButton, null);
                    Assert.fail();
				}else {
					Assert.assertTrue(true);
					commonUtils.validateGetText(loginPage.alertMessage, p.getProperty("invalidCredentialsAlertMessage"));
				}
			}
		}catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("****** Finished Admin Login DDT ******");
	}
}
