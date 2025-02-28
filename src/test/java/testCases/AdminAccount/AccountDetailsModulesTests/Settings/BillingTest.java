package testCases.AdminAccount.AccountDetailsModulesTests.Settings;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.AccountDetailsModules.UsersAndRolesPage;
import pageObjects.AdminAccount.AccountDetailsModules.Settings.SettingsPage;
import pageObjects.AdminAccount.AccountDetailsModules.Settings.BillingPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class BillingTest extends BaseClass {

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

            DashboardPage dashboardPage = new DashboardPage(driver);
            CommonLocatorsPage commonLocatorsPage = new CommonLocatorsPage();
            CommonUtils commonUtils = new CommonUtils(driver);
            AccountDetailsPage accountDetailsPage = new AccountDetailsPage(driver);
            SettingsPage settingsPage = new SettingsPage(driver);
            UsersAndRolesPage usersAndRolesPage = new UsersAndRolesPage(driver);
            BillingPage billingPage = new BillingPage(driver);

            dashboardPage.searchForItem(p.getProperty("accountName"));
            dashboardPage.clickView();

            commonUtils.selectTab(commonUtils.findElementsByXpath(accountDetailsPage.tabList), "Settings");

            //CREATE
            commonUtils.selectTab(commonUtils.findElementsByXpath(settingsPage.settingsTabsList), "Billing");
            commonUtils.clickOnElement(commonUtils.findElementByXpath(settingsPage.editButton), "Edit");
            commonUtils.clickOnElement(commonUtils.findElementByXpath(settingsPage.createNewButton),
                    "Create New");

            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(usersAndRolesPage.newUserNameField),
                    randomUser);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(usersAndRolesPage.mobileCountryCodeDropDown),
                    null);
            commonUtils.selectDropDownValueWithClick(commonUtils.findElementsByXpath(usersAndRolesPage.countryList),
                    getTestData("mobileCountryCode"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(usersAndRolesPage.phoneNumberField),
                    randomPhone);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(usersAndRolesPage.emailField),
                    randomEmail);
            commonUtils.validateRadioButton(commonUtils.findElementByXpath(usersAndRolesPage.userTimeZoneRadioButton));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(usersAndRolesPage.userDefaultTimeZoneDropDown),
                    getTestData("defaultTimeZone"));
            commonUtils.validateCheckbox(commonUtils.findElementByXpath(usersAndRolesPage.activeCheckbox));
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByCssSelector(usersAndRolesPage.saveButton));
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(
                    commonLocatorsPage.dialogueText),
                    getTestData("billingDialogueText"),
                    commonUtils.findElementByXpath(commonLocatorsPage.dialogueOkButton)
            );

            //EDIT
            commonUtils.clickOnElement(commonUtils.findElementByXpath(settingsPage.editButton), "Edit");
            commonUtils.validateCheckbox(commonUtils.findElementByCssSelector(settingsPage.activeCheckbox));
            commonUtils.validateCheckbox(commonUtils.findElementByCssSelector(settingsPage.demoAccountCheckbox));
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
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(
                    commonLocatorsPage.dialogueText),
                    getTestData("billingUpdatedDialogueText"),
                    commonUtils.findElementByXpath(commonLocatorsPage.dialogueOkButton)
            );
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Create and Update Billing Contact Test ******");
    }
}
