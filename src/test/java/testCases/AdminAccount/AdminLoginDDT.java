package testCases.AdminAccount;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
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
					commonUtils.clickOnElement(commonUtils.findElementByXpath(dp.togglerIcon), null);
					commonUtils.clickOnElement(commonUtils.findElementByXpath(dp.logoutButton), null);
					Assert.assertTrue(true);
				}else
				{
                    Assert.fail("Valid login failed, no dashboard found.");
				}
			}
			
			if(exp.equalsIgnoreCase("invalid"))
			{
				if(targetPage)	{
					commonUtils.clickOnElement(commonUtils.findElementByXpath(dp.togglerIcon), null);
					commonUtils.clickOnElement(commonUtils.findElementByXpath(dp.logoutButton), null);
                    Assert.fail();
				}else {
					Assert.assertTrue(true);
					commonUtils.validateGetText(commonUtils.findElementByXpath(loginPage.alertMessage),
							p.getProperty("invalidCredentialsAlertMessage"));
				}
			}

            if(exp.equalsIgnoreCase("empty")) {
                if (targetPage) {
                    commonUtils.clickOnElement(commonUtils.findElementByXpath(dp.togglerIcon), null);
                    commonUtils.clickOnElement(commonUtils.findElementByXpath(dp.logoutButton), null);
                    Assert.fail("Invalid login treated as valid.");
                } else {
                    Assert.assertTrue(true);
                    commonUtils.validateGetText(commonUtils.findElementById(loginPage.emailErrorText),
                            p.getProperty("emailErrorText"));
                    commonUtils.validateGetText(commonUtils.findElementById(loginPage.passwordErrorText),
                            p.getProperty("passwordErrorText"));
                }
            }
		}catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("****** Finished Admin Login DDT ******");
	}
}
