package testCases.AdminAccount.DashboardTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.AdminAccount.AccountDetailsModules.FormsPage;
import pageObjects.AdminAccount.Dashboard.CreateAccountPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;
import java.time.Duration;

public class CreateAccountTest extends BaseClass {

	public CommonUtils commonUtils;
	public CreateAccountPage createAccountPage;
	public String newAccountName;
	public DashboardPage dashboardPage;
	public FormsPage formsPage;

	@Test(groups = { "Smoke" }, priority = 1)
	public void verifyCreateAccount() {
		login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);
		try {
			logger.info("****** Starting Create Account And Delete Created Account Test Case ******");

			formsPage = new FormsPage(driver);
			commonUtils = new CommonUtils(driver);
			dashboardPage = new DashboardPage(driver);
			createAccountPage = new CreateAccountPage(driver);

			newAccountName = randomString();
			commonUtils.clickOnElement(commonUtils.findElementByXpath(createAccountPage.clickCreateAccount),
					null );
			commonUtils.enterValueInTextField(commonUtils.findElementByXpath(createAccountPage.accountNameField),
					newAccountName);
			commonUtils.enterValueInTextField(commonUtils.findElementByXpath(createAccountPage.primaryContactNameField),
					randomString());
			commonUtils.enterValueInTextField(commonUtils.findElementByXpath(createAccountPage.emailField),
					randomString()+"@gmail.com");
			commonUtils.enterValueInTextField(commonUtils.findElementByXpath(createAccountPage.phoneField),
					randomNumbers(10));

			// Validate and select the account type based on the config file
			String accountType = p.getProperty("accountType");
			if (accountType.equalsIgnoreCase("Individual")) {
				commonUtils.clickRadioButton(commonUtils.findElementByXpath(
						createAccountPage.accountTypeIndividualRadioButton));
			} else if (accountType.equalsIgnoreCase("Company")) {
				commonUtils.clickRadioButton(commonUtils.findElementByXpath(
						createAccountPage.accountTypeCompanyRadioButton));
			} else {
				throw new IllegalArgumentException(
						"Invalid account type specified in config.properties: " + accountType);
			}

			commonUtils.selectDropDownValue(commonUtils.findElementByXpath(
					createAccountPage.diagnosticMessagingSetDropdown),
					p.getProperty("diagnosticMessagingSetDropdown"));
			commonUtils.selectDropDownValue(commonUtils.findElementByXpath(createAccountPage.defaultIntakeFormDropdown),
					p.getProperty("defaultIntake"));
			commonUtils.selectDropDownValue(commonUtils.findElementByXpath(createAccountPage.billingCountryDropdown),
					p.getProperty("billingCountryName"));
			commonUtils.enterValueInTextField(commonUtils.findElementByXpath(createAccountPage.billingAddress_1Field),
					p.getProperty("billingAddress1"));
			commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(createAccountPage.billingCityField));
			commonUtils.enterValueInTextField(commonUtils.findElementByXpath(createAccountPage.billingCityField),
					p.getProperty("billingCity"));
			commonUtils.enterValueInTextField(commonUtils.findElementByXpath(createAccountPage.billingStateField),
					p.getProperty("billingState"));
			commonUtils.enterValueInTextField(commonUtils.findElementByXpath(createAccountPage.billingPostcodeField),
					p.getProperty("postCode"));
			commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(createAccountPage.saveNewAccount_button));

			WebElement accountDetailsElement = commonUtils.findElementByXpath(createAccountPage.newAccountHeading);
			String actualText = accountDetailsElement.getText().trim();

			// Validate if the names match
			if (actualText.contains(newAccountName)) {
				System.out.println("Test Passed: Names match");
			} else {
				System.out.println("Test Failed: Names do not match");
			}

			//Click on Back To Search
			commonUtils.clickOnElement(commonUtils.findElementByXpath(createAccountPage.backToSearchButton),
					null);
			commonUtils.clickOnElement(commonUtils.findElementByXpath(dashboardPage.searchButton),null);
			driver.navigate().refresh();
			commonUtils.scrollToBottom();
			Thread.sleep(2000);

			//Perform delete option for user
			createAccountPage.performAccountDeleteActionOnUser("accountsTable",newAccountName,"Delete");

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(commonUtils.findElementByXpath(createAccountPage.deleteAccount)));
			deleteButton.click();

			commonUtils.clickOnElement(commonUtils.findElementByXpath(formsPage.okButton),null);

			commonUtils.scrollToUp();
			//Check the deleted account is displayed or not
			commonUtils.clickOnElement(commonUtils.findElementByXpath(dashboardPage.searchButton), null);
			driver.navigate().refresh();
			boolean deleted = createAccountPage.isAccountDeleted(newAccountName);

			if (deleted) {
				System.out.println("Test Passed: User is deleted.");
			} else {
				System.out.println("Test Failed: User is still present.");
			}

		} catch (Exception e) {
			logger.error("Exception occurred during test execution: ", e);
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
		logger.info("****** Finished Create Account And Delete Created Account Test Case ******");
	}
}