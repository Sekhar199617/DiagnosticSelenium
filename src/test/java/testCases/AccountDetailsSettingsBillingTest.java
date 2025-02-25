package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;
import utilities.CommonUtils;

public class AccountDetailsSettingsBillingTest extends BaseClass {

    public String randomUser;
    public String randomEmail;
    public String randomPhone;

    @Test
    public void verifyCreateAndUpdateBillingContactTest() {

        logger.info("****** Starting Create and Update Billing Contact Test ******");
        try {

            String jsonPath = "./testData//accountDetailsData.json";
            loadTestData(jsonPath);

            randomUser = randomString();
            randomEmail = randomString() + "@gmail.com";
            randomPhone = randomNumbers(10);

            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            CommonLocatorsPage cl = new CommonLocatorsPage();
            CommonUtils commonUtils = new CommonUtils(driver);
            AccountDetailsPage ad = new AccountDetailsPage(driver);
            AccountDetailsSettingsPage ads = new AccountDetailsSettingsPage(driver);
            AccountDetailsUsersAndRolesPage au = new AccountDetailsUsersAndRolesPage(driver);
            AccountDetailsSettingsBillingPage billingPage = new AccountDetailsSettingsBillingPage();

            dp.searchForItem(p.getProperty("accountName"));
            dp.clickView();

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Settings");

            //CREATE
            commonUtils.selectTab(commonUtils.findElementsByXpath(ads.settingsTabsList), "Billing");
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ads.editButton), "Edit");
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ads.createNewButton),
                    "Create New");

            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(au.newUserNameField), randomUser);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(au.mobileCountryCodeDropDown), null);
            commonUtils.selectDropDownValueWithClick(commonUtils.findElementsByXpath(au.countryList),
                    getTestData("mobileCountryCode"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(au.phoneNumberField), randomPhone);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(au.emailField), randomEmail);
            commonUtils.validateRadioButton(commonUtils.findElementByXpath(au.userTimeZoneRadioButton));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(au.userDefaultTimeZoneDropDown),
                    getTestData("defaultTimeZone"));
            commonUtils.validateCheckbox(commonUtils.findElementByXpath(au.activeCheckbox));
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByCssSelector(au.saveButton));
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(cl.dialogueText),
                    getTestData("billingDialogueText"),
                    commonUtils.findElementByXpath(cl.dialogueOkButton));

            //EDIT
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ads.editButton), "Edit");
            commonUtils.validateCheckbox(commonUtils.findElementByCssSelector(ads.activeCheckbox));
            commonUtils.validateCheckbox(commonUtils.findElementByCssSelector(ads.demoAccountCheckbox));
            commonUtils.validateSelectedDropdownValue(commonUtils.findElementsByCssSelector(
                    billingPage.billingContactDropdownOptions), randomUser
            );

            // NOTE : Billing Email & Billing Phone Fields are disabled

            commonUtils.selectDropDownValue(commonUtils.findElementById(billingPage.billingCountryDropdownField),
                    getTestData("billingCountry"));
            commonUtils.scrollToElement(commonUtils.findElementByCssSelector(
                    billingPage.billingAddress1Field));
            commonUtils.enterValueInTextField(commonUtils.findElementByCssSelector(
                    billingPage.billingAddress1Field), randomAlphaNumeric() + " Updated");
            commonUtils.scrollToElement(commonUtils.findElementByCssSelector(
                    billingPage.billingAddress2Field));
            commonUtils.enterValueInTextField(commonUtils.findElementByCssSelector(
                    billingPage.billingAddress2Field), randomAlphaNumeric() + " Updated"
            );
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByCssSelector(billingPage.billingCityField));
            commonUtils.enterValueInTextField(commonUtils.findElementByCssSelector(billingPage.billingCityField),
                    getTestData("billingCity"));
            commonUtils.enterValueInTextField(commonUtils.findElementByCssSelector(billingPage.billingStateField),
                    getTestData("billingState"));
            commonUtils.enterValueInTextField(commonUtils.findElementByCssSelector(
                    billingPage.billingPostalCodeField), randomNumbers(6));
            commonUtils.scrollToUp();
            commonUtils.clickOnElement(commonUtils.findElementById(billingPage.updateButtonField),
                    "Update");
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(cl.dialogueText),
                    getTestData("billingUpdatedDialogueText"),
                    commonUtils.findElementByXpath(cl.dialogueOkButton));
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Create and Update Billing Contact Test ******");
    }
}
