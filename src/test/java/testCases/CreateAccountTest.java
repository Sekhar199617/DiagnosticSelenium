package testCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CreateAccountPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class CreateAccountTest extends BaseClass {

	public CommonUtils commonUtils;

	@Test(groups = { "Smoke" })
	public void verify_Create_Account() {
		login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);
		try {
			logger.info("****** Starting Create Account Test Case ******");

			CreateAccountPage createAccountPage = new CreateAccountPage(driver);
			CommonUtils commonUtils = new CommonUtils(driver);

			commonUtils.clickOnElement(createAccountPage.clickCreateAccount, null);
			commonUtils.enterValueInTextField(createAccountPage.accountNameField, randomString());
			commonUtils.enterValueInTextField(createAccountPage.primaryContactNameField, randomString());
			commonUtils.enterValueInTextField(createAccountPage.emailField, randomString() + "@gmail.com");
			commonUtils.enterValueInTextField(createAccountPage.phoneField, p.getProperty("createAccountPhone"));

			// Validate and select the account type based on the config file
			String accountType = p.getProperty("accountType");
			if (accountType.equalsIgnoreCase("Individual")) {
				commonUtils.clickRadioButton(createAccountPage.accountTypeIndividualRadioButton);
			} else if (accountType.equalsIgnoreCase("Company")) {
				commonUtils.clickRadioButton(createAccountPage.accountTypeCompanyRadioButton);
			} else {
				throw new IllegalArgumentException(
						"Invalid account type specified in config.properties: " + accountType);
			}

			commonUtils.selectDropDownValue(createAccountPage.diagnosticMessagingSetDropdown, p.getProperty("diagnosticMessagingSetDropdown"));
			commonUtils.selectDropDownValue(createAccountPage.defaultIntakeFormDropdown,
					p.getProperty("defaultIntake"));
			commonUtils.selectDropDownValue(createAccountPage.billingCountryDropdown,
					p.getProperty("billingCountryName"));
			commonUtils.enterValueInTextField(createAccountPage.billingAddress_1Field,
					p.getProperty("billingAddress1"));
			commonUtils.enterValueInTextField(createAccountPage.billingCityField, p.getProperty("billingCity"));
			commonUtils.enterValueInTextField(createAccountPage.billingStateField, p.getProperty("billingState"));
			commonUtils.enterValueInTextField(createAccountPage.billingPostcodeField, p.getProperty("postCode"));

			commonUtils.scrollToBottomAndClick(createAccountPage.saveNewAccount_button);

		} catch (Exception e) {
			logger.error("Exception occurred during test execution: ", e);
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
		logger.info("****** Finished Create Account Test Case ******");
	}

	
//	  @AfterMethod public void delete_NewCreatedAccount() { try {
//	  login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);
//
//	  if (driver == null) {
//	  logger.error("WebDriver session is null. Skipping account deletion.");
//	  return; }
//
//	  driver.navigate().refresh(); logger.info("Page reloaded successfully.");
//	  Thread.sleep(3000); DashboardPage dp = new DashboardPage(driver); CommonUtils
//	  commonUtils = new CommonUtils(driver);
//
//	  commonUtils.enterValueInTextField(dp.searchField,
//	  p.getProperty("createAccountName"));
//	  commonUtils.clickOnElement(dp.searchButton, "Search");
//	  commonUtils.clickOnElement(dp.actionsDropDown, null);
//	  commonUtils.clickOnElement(dp.delete, null);
//	  commonUtils.clickOnElement(dp.deletePopup, null);
//	  commonUtils.clickOnElement(dp.ok_deletePopup, null); }catch (Exception e) {
//	  logger.error("Exception occurred during account deletion: ", e);
//	  Assert.fail("Account deletion failed due to exception: " + e.getMessage()); }
//	 }
	 

}
