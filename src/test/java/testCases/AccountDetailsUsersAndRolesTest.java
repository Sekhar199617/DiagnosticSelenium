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
			commonUtils.enterValueInTextField(au.newUserNameField, randomUser);
			commonUtils.clickOnElement(au.mobileCountryCodeDropDown, null);
			commonUtils.selectDropDownValueWithClick(au.countryList, p.getProperty("mobileCountryCode"));
			commonUtils.enterValueInTextField(au.phoneNumberField, randomNumbers());
			commonUtils.enterValueInTextField(au.emailField, randomString() + "@gmail.com");
			commonUtils.selectDropDownValue(au.roleDropDown, p.getProperty("role"));
			commonUtils.selectDropDownValue(au.userTypeDropDown, p.getProperty("userType"));

			switch(p.getProperty("userType")) {
				case "Account Admin":
					commonUtils.validateInputText(au.accountNameField, p.getProperty("accountName"));
					break;
				case "Provider":
				case "Medical Staff":
					commonUtils.enterValueInTextField(au.licenseIDField, p.getProperty("licenseID"));
					commonUtils.enterValueInTextField(au.credentialsField, p.getProperty("credentials"));
					break;
				case "Observer":
					commonUtils.selectDropDownValue(au.unselectedBundlesList, p.getProperty("unselectedBundle"));
					commonUtils.clickOnElement(au.rightArrow, null);
					commonUtils.validateInputText(au.selectedBundleField, p.getProperty("unselectedBundle"));
					break;
				case "Manager":
					commonUtils.selectRandomCheckboxes(au.additionalPriviligesCheckboxesList);
					break;
				case "Support Entity Admin":
					break;
				case "Support Entity Manager":
					commonUtils.selectRandomCheckboxes(au.supportManagerPriviligesCheckboxesList);
					break;
				default :
					break;
			}

			commonUtils.validateRadioButton(au.userTimeZoneRadioButton);
			commonUtils.selectDropDownValue(au.userDefaultTimeZoneDropDown, p.getProperty("defaultTimeZone"));
			commonUtils.validateCheckbox(au.activeCheckbox);
			commonUtils.scrollToBottomAndClick(au.saveButton);
			commonUtils.validateGetText(au.dialogueText, p.getProperty("dialogueText"));
			commonUtils.clickOnElement(au.dialogueOkButton, "Ok");

		}catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("****** Finished Add New Account User Test ******");
	}
}
