package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsPage;
import pageObjects.AccountDetailsSettingsPage;
import pageObjects.AccountDetailsUsersAndRolesPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class AccountDetailsSettingsBillingTest extends BaseClass {

    public String randomUser;

    @Test
    public void verifyCreateNewBillingContactTest() {

        logger.info("****** Starting New Billing Contact Test ******");
        try {
            randomUser = randomString();

            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);

            commonUtils.enterValueInTextField(dp.searchField, p.getProperty("accountName"));
            commonUtils.clickOnElement(dp.searchButton, "Search");
            commonUtils.clickOnElement(dp.actionsDropDown, null);
            commonUtils.clickOnElement(dp.view, "View");

            AccountDetailsPage ad = new AccountDetailsPage(driver);
            commonUtils.selectTab(ad.tabList, "Settings");

            AccountDetailsSettingsPage ads = new AccountDetailsSettingsPage(driver);
            commonUtils.selectTab(ads.settingsTabsList, "Billing");
            commonUtils.clickOnElement(ads.editButton, "Edit");
            commonUtils.clickOnElement(ads.createNewButton, "Create New");

            AccountDetailsUsersAndRolesPage au = new AccountDetailsUsersAndRolesPage(driver);
            commonUtils.enterValueInTextField(au.newUserNameField, randomUser);
            commonUtils.clickOnElement(au.mobileCountryCodeDropDown, null);
            commonUtils.selectMobileCountryCode(au.countryList, p.getProperty("mobileCountryCode"));
            commonUtils.enterValueInTextField(au.phoneNumberField, randomNumbers());
            commonUtils.enterValueInTextField(au.emailField, randomString() + "@gmail.com");
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
        logger.info("****** Finished New Billing Contact Test ******");
    }
}
