package testCases.AdminAccount.AccountDetailsModulesTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.AccountDetailsModules.UsersAndRolesPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class UsersAndRolesTest extends BaseClass {

	public String randomUser;
	public CommonUtils commonUtils;

	@Test(groups= {"Smoke"})
	public void verifyAddNewAccountUser() {

		logger.info("****** Starting Add New Account User Test ******");
		try{
			randomUser = randomString();

			login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

			commonUtils = new CommonUtils(driver);
			DashboardPage dashboardPage = new DashboardPage(driver);
			UsersAndRolesPage usersAndRolesPage = new UsersAndRolesPage(driver);
			AccountDetailsPage accountDetailsPage = new AccountDetailsPage(driver);

			dashboardPage.searchForItem(p.getProperty("accountName"));
			dashboardPage.clickView();

			commonUtils.selectTab(commonUtils.findElementsByXpath(accountDetailsPage.tabList), "Users & Roles");
			commonUtils.clickOnElement(commonUtils.findElementByXpath(accountDetailsPage.addText), "Add");

			commonUtils.createUser(
					usersAndRolesPage.newUserNameField,
					randomString(),
					usersAndRolesPage.mobileCountryCodeDropDown,
					usersAndRolesPage.countryList,
					usersAndRolesPage.phoneNumberField,
					usersAndRolesPage.emailField,
					usersAndRolesPage.roleDropDown,
					usersAndRolesPage.userTypeDropDown,
					usersAndRolesPage.licenseIDField,
					usersAndRolesPage.credentialsField,
					usersAndRolesPage.bundlesNotAttachedField,
					usersAndRolesPage.rightArrow,
					usersAndRolesPage.selectedBundleField,
					usersAndRolesPage.additionalPriviligesCheckboxesList,
					usersAndRolesPage.supportManagerPriviligesCheckboxesList,
					usersAndRolesPage.userTimeZoneRadioButton,
					usersAndRolesPage.userDefaultTimeZoneDropDown,
					usersAndRolesPage.activeCheckbox,
					usersAndRolesPage.saveButton,
					usersAndRolesPage.dialogueText,
					usersAndRolesPage.dialogueOkButton,
					p.getProperty("mobileCountryCode"),
					randomNumbers(10),
					p.getProperty("role"),
					p.getProperty("usersUserType"),
					p.getProperty("licenseID"),
					p.getProperty("credentials"),
					p.getProperty("defaultTimeZone"),
					p.getProperty("dialogueText")
			);
		}catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("****** Finished Add New Account User Test ******");
	}
}
