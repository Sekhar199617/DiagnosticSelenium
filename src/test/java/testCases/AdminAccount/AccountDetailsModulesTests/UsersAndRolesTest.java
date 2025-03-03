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

			loadTestData(
					"./testData/adminLoginData.json",
					"./testData/accountDetailsData.json",
					"./testData/dashboardData.json"
			);

			login(getTestData("adminEmail"), getTestData("adminPassword"), true);

			DashboardPage dp = new DashboardPage(driver);
			commonUtils = new CommonUtils(driver);

			dp.searchForItem(getTestData("accountName"));
			dp.clickView();

			AccountDetailsPage ad = new AccountDetailsPage(driver);
			commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");
			commonUtils.clickOnElement(commonUtils.findElementByXpath(ad.addText), "Add");

			UsersAndRolesPage au = new UsersAndRolesPage(driver);
			commonUtils.createUser(
					au.newUserNameField,
					randomString(),
					au.mobileCountryCodeDropDown,
					au.countryList,
					au.phoneNumberField,
					au.emailField,
					au.roleDropDown,
					au.userTypeDropDown,
					au.licenseIDField,
					au.credentialsField,
					au.bundlesNotAttachedField,
					au.rightArrow,
					au.selectedBundleField,
					au.additionalPriviligesCheckboxesList,
					au.supportManagerPriviligesCheckboxesList,
					au.userTimeZoneRadioButton,
					au.userDefaultTimeZoneDropDown,
					au.activeCheckbox,
					au.saveButton,
					au.dialogueText,
					au.dialogueOkButton,
					getTestData("mobileCountryCode"),
					randomNumbers(10),
					getTestData("role"),
					getTestData("userType"),
					randomNumbers(6),
					randomAlphaNumeric(),
					getTestData("defaultTimeZone"),
					getTestData("newUsersDialogueText")
			);
		}catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("****** Finished Add New Account User Test ******");
	}
}
