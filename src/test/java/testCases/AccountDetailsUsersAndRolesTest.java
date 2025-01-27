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
			CommonUtils commonUtils = new CommonUtils(driver);

			commonUtils.enterValueInTextField(dp.searchField, p.getProperty("accountName"));
			commonUtils.clickOnElement(dp.searchButton, "Search");
			commonUtils.clickOnElement(dp.actionsDropDown, null);
			commonUtils.clickOnElement(dp.view, "View");

			AccountDetailsPage ad = new AccountDetailsPage(driver);
			commonUtils.selectTab(ad.tabList, "Users & Roles");
			commonUtils.clickOnElement(ad.addText, "Add");

			AccountDetailsUsersAndRolesPage au = new AccountDetailsUsersAndRolesPage(driver);
			commonUtils.createUser(
					au.newUserNameField,
					au.mobileCountryCodeDropDown,
					au.countryList,
					au.phoneNumberField,
					au.emailField,
					au.roleDropDown,
					au.userTypeDropDown,
					au.licenseIDField,
					au.credentialsField,
					au.unselectedBundlesList,
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
					randomString(),  // randomUser
					p.getProperty("mobileCountryCode"),
					randomNumbers(), // randomPhoneNumber
					p.getProperty("role"),
					p.getProperty("userType"),
					p.getProperty("licenseID"),
					p.getProperty("credentials"),
					p.getProperty("unselectedBundle"),
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
