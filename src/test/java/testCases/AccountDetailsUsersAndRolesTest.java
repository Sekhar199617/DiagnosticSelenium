package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsPage;
import pageObjects.AccountDetailsUsersAndRolesPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class AccountDetailsUsersAndRolesTest extends BaseClass {

	public String randomUser;
	public CommonUtils commonUtils;

	@Test(groups= {"Smoke"})
	public void verifyAddNewAccountUser() {

		logger.info("****** Starting Add New Account User Test ******");
		try{
			randomUser = randomString();

			login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

			DashboardPage dp = new DashboardPage(driver);
			commonUtils = new CommonUtils(driver);

			dp.searchForItem(p.getProperty("accountName"));
			dp.clickView();

			AccountDetailsPage ad = new AccountDetailsPage(driver);
			commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");
			commonUtils.clickOnElement(commonUtils.findElementByXpath(ad.addText), "Add");

			AccountDetailsUsersAndRolesPage au = new AccountDetailsUsersAndRolesPage(driver);
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
