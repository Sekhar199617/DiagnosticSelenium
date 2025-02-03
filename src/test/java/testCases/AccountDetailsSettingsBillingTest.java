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

            dp.searchForItem(p.getProperty("accountName"));
            dp.clickView();

            AccountDetailsPage ad = new AccountDetailsPage(driver);
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Settings");

            AccountDetailsSettingsPage ads = new AccountDetailsSettingsPage(driver);
            commonUtils.selectTab(commonUtils.findElementsByXpath(ads.settingsTabsList), "Billing");
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ads.editButton), "Edit");
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ads.createNewButton), "Create New");

            AccountDetailsUsersAndRolesPage au = new AccountDetailsUsersAndRolesPage(driver);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(au.newUserNameField), randomUser);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(au.mobileCountryCodeDropDown), null);
            commonUtils.selectDropDownValueWithClick(commonUtils.findElementsByXpath(au.countryList),
                    p.getProperty("mobileCountryCode"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(au.phoneNumberField), randomNumbers());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(au.emailField),
                    randomString() + "@gmail.com");
            commonUtils.validateRadioButton(commonUtils.findElementByXpath(au.userTimeZoneRadioButton));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(au.userDefaultTimeZoneDropDown),
                    p.getProperty("defaultTimeZone"));
            commonUtils.validateCheckbox(commonUtils.findElementByXpath(au.activeCheckbox));
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByCssSelector(au.saveButton));
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(au.dialogueText),
                    p.getProperty("settingsBillingDialogueText"),
                    commonUtils.findElementByXpath(au.dialogueOkButton));
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished New Billing Contact Test ******");
    }
}
