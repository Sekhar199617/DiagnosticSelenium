package testCases;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import pageObjects.AccountDetailsAddFormsPage;
import pageObjects.CreateAccountPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.time.Duration;

public class CreateAccountTest extends BaseClass {

	public CommonUtils commonUtils;
	public CreateAccountPage createAccountPage;
	public String newAccountName;

	@Test(groups = { "Smoke" }, priority = 1)
	public void verify_Create_Account() {
		login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);
		try {
			logger.info("****** Starting Create Account Test Case ******");

			createAccountPage = new CreateAccountPage(driver);
			commonUtils = new CommonUtils(driver);

			newAccountName = randomString();
			commonUtils.clickOnElement(commonUtils.findElementByXpath(createAccountPage.clickCreateAccount),null );
			commonUtils.enterValueInTextField(commonUtils.findElementByXpath(createAccountPage.accountNameField),newAccountName);
			commonUtils.enterValueInTextField(commonUtils.findElementByXpath(createAccountPage.primaryContactNameField),randomString());
			commonUtils.enterValueInTextField(commonUtils.findElementByXpath(createAccountPage.emailField),randomString()+"@gmail.com");
			commonUtils.enterValueInTextField(commonUtils.findElementByXpath(createAccountPage.phoneField),randomNumbers(10));

			// Validate and select the account type based on the config file
			String accountType = p.getProperty("accountType");
			if (accountType.equalsIgnoreCase("Individual")) {
				commonUtils.clickRadioButton(commonUtils.findElementByXpath(createAccountPage.accountTypeIndividualRadioButton));
			} else if (accountType.equalsIgnoreCase("Company")) {
				commonUtils.clickRadioButton(commonUtils.findElementByXpath(createAccountPage.accountTypeCompanyRadioButton));
			} else {
				throw new IllegalArgumentException(
						"Invalid account type specified in config.properties: " + accountType);
			}

			commonUtils.selectDropDownValue(commonUtils.findElementByXpath(createAccountPage.diagnosticMessagingSetDropdown),
					p.getProperty("diagnosticMessagingSetDropdown"));
			commonUtils.selectDropDownValue(commonUtils.findElementByXpath(createAccountPage.defaultIntakeFormDropdown),
					p.getProperty("defaultIntake"));
			commonUtils.selectDropDownValue(commonUtils.findElementByXpath(createAccountPage.billingCountryDropdown),
					p.getProperty("billingCountryName"));
			commonUtils.enterValueInTextField(commonUtils.findElementByXpath(createAccountPage.billingAddress_1Field),
					p.getProperty("billingAddress1"));
			commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(createAccountPage.billingCityField));
			commonUtils.enterValueInTextField(commonUtils.findElementByXpath(createAccountPage.billingCityField), p.getProperty("billingCity"));
			commonUtils.enterValueInTextField(commonUtils.findElementByXpath(createAccountPage.billingStateField), p.getProperty("billingState"));
			commonUtils.enterValueInTextField(commonUtils.findElementByXpath(createAccountPage.billingPostcodeField), p.getProperty("postCode"));
			commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(createAccountPage.saveNewAccount_button));

			WebElement accountDetailsElement = commonUtils.findElementByXpath(createAccountPage.newAccountHeading);
			String actualText = accountDetailsElement.getText().trim();

			// Validate if the names match
			if (actualText.contains(newAccountName)) {
				System.out.println("Test Passed: Names match");
			} else {
				System.out.println("Test Failed: Names do not match");
			}

		} catch (Exception e) {
			logger.error("Exception occurred during test execution: ", e);
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
		logger.info("****** Finished Create Account Test Case ******");
	}


	@Test(groups = { "Smoke" }, priority = 2)
	  public void delete_NewCreatedAccount() {
		  login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);
		  try {

			  logger.info("****** Starting Delete New Created Account Test Case ******");
			  DashboardPage dp = new DashboardPage(driver);
			  commonUtils = new CommonUtils(driver);
			  AccountDetailsAddFormsPage af = new AccountDetailsAddFormsPage(driver);

			  commonUtils.clickOnElement(commonUtils.findElementByXpath(dp.searchButton),null);
			  driver.navigate().refresh();
			  commonUtils.scrollToBottom();
			 createAccountPage.performActionOnUser(newAccountName);

			  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			  WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(commonUtils.findElementByXpath(createAccountPage.deleteAccount)));
			  deleteButton.click();

			 commonUtils.clickOnElement(commonUtils.findElementByXpath(af.okButton),null);

		  } catch (Exception e) {
			  logger.error("Exception occurred during test execution: ", e);
			  Assert.fail("Test failed due to exception: " + e.getMessage());
		  }
		  logger.info("****** Finished Delete New Created Account Test Case ******");


	 }

	@Test(groups = { "Smoke" }, priority = 3)
	public void verifyDeleteAccountIsNotPresentInSearch() {
		login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);
		try {

			logger.info("****** Starting Verify Delete Account Is Not Present In Search Test Case ******");
			DashboardPage dp = new DashboardPage(driver);
			commonUtils = new CommonUtils(driver);
			AccountDetailsAddFormsPage af = new AccountDetailsAddFormsPage(driver);

			commonUtils.clickOnElement(commonUtils.findElementByXpath(dp.searchButton), null);
			driver.navigate().refresh();

			boolean deleted = createAccountPage.isUserDeleted(newAccountName);

			if (deleted) {
				System.out.println("Test Passed: User is deleted.");
			} else {
				System.out.println("Test Failed: User is still present.");
			}

		} catch (Exception e) {
			logger.error("Exception occurred during test execution: ", e);
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
		logger.info("****** Finished Verify Delete Account Is Not Present In Search Test Case ******");
	}


	@AfterMethod
	public void deleteCookies() {
		// Delete cookies after each test
		if (driver != null) {
			driver.manage().deleteAllCookies();
		}
	}

}
