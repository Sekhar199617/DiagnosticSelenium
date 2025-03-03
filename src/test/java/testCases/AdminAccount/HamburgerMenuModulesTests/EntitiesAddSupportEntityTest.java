package testCases.AdminAccount.HamburgerMenuModulesTests;

import org.testng.annotations.Test;
import pageObjects.AdminAccount.AccountDetailsModules.UsersAndRolesPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import pageObjects.AdminAccount.HamburgerMenuModules.EntitiesPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class EntitiesAddSupportEntityTest extends BaseClass {

    @Test
    public void verifyAddSupportEntity() throws InterruptedException {

        login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

        DashboardPage dp = new DashboardPage(driver);
        CommonUtils commonUtils = new CommonUtils(driver);

        dp.selectHamburgerTab("Entities");

        EntitiesPage ep = new EntitiesPage(driver);
        commonUtils.clickOnElement(commonUtils.findElementByXpath(ep.addSupportEntityButton),
                null);
        Thread.sleep(2000);
        commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ep.entityNameField),
                "Entity " + randomString());
        commonUtils.selectDropDownValue(commonUtils.findElementByName(ep.typeDropDownField),
                p.getProperty("entityType"));
        commonUtils.selectTab(commonUtils.findElementsByCssSelector(ep.tabList), "Authentication");
        commonUtils.selectCheckbox(commonUtils.findElementById(ep.permitAccountSupportCheckbox));
        commonUtils.selectCheckbox(commonUtils.findElementById(ep.activeOutboundApiCheckbox));
        commonUtils.selectCheckbox(commonUtils.findElementById(ep.activeOutboundWebhookCheckbox));

        commonUtils.enterValueInTextField(commonUtils.findElementById(ep.productionApiUrlField),
                "https://" + randomString().toLowerCase() + "prod_api_url.com");
        commonUtils.enterValueInTextField(commonUtils.findElementById(ep.productionApiKeyField),
                randomString().toLowerCase() + randomAlphaNumeric());
        commonUtils.enterValueInTextField(commonUtils.findElementById(ep.productionApiSecretField),
                randomString().toLowerCase() + randomAlphaNumeric());
        commonUtils.enterValueInTextField(commonUtils.findElementById(ep.productionWebhookUrlField),
                "https://" + randomString().toLowerCase() + "prod_webhook_url.com");
        commonUtils.enterValueInTextField(commonUtils.findElementById(ep.productionWebhookApiKeyField),
                randomString().toLowerCase() + randomAlphaNumeric());
        commonUtils.selectCheckbox(commonUtils.findElementById(ep.acceptInboundWebhookCheckbox));
        commonUtils.scrollToBottomAndClick(commonUtils.findElementByCssSelector(ep.authenticationApiKeyField));
        commonUtils.enterValueInTextField(commonUtils.findElementByCssSelector(ep.authenticationApiKeyField),
                randomString().toLowerCase() + randomAlphaNumeric());
        commonUtils.scrollToElement(commonUtils.findElementByXpath(ep.authenticationApiKeyRadioButton));
        commonUtils.selectRadioButton(commonUtils.findElementByXpath(ep.authenticationApiKeyRadioButton));

        commonUtils.selectTab(commonUtils.findElementsByCssSelector(ep.tabList), "Comms");
        commonUtils.selectRandomCheckboxes(commonUtils.findElementsByCssSelector(ep.entityCommunicationCheckboxList));

        commonUtils.selectTab(commonUtils.findElementsByCssSelector(ep.tabList), "Contacts");
        commonUtils.enterValueInTextField(commonUtils.findElementById(ep.contactNameField), randomString());
        commonUtils.enterValueInTextField(commonUtils.findElementById(ep.emailAddressField),
                randomString().toLowerCase() + "@gmail.com");
        commonUtils.enterValueInTextField(commonUtils.findElementById(ep.officePhoneField), randomNumbers(10));
        commonUtils.enterValueInTextField(commonUtils.findElementById(ep.mobilePhoneField), randomNumbers(10));
        commonUtils.enterValueInTextField(commonUtils.findElementById(ep.addressField),
                randomString() + " Address");
        commonUtils.enterValueInTextField(commonUtils.findElementById(ep.cityField), randomString() + "City");
        commonUtils.selectDropDownValue(commonUtils.findElementById(ep.stateDropdownField),
                p.getProperty("entityState"));
        commonUtils.enterValueInTextField(commonUtils.findElementById(ep.zipcodeField), randomNumbers(6));

        commonUtils.enterValueInTextField(commonUtils.findElementById(ep.billingContactNameField), randomString());
        commonUtils.enterValueInTextField(commonUtils.findElementById(ep.billingContactEmailField),
                randomString().toLowerCase() + "@gmail.com");
        commonUtils.scrollToBottomAndClick(commonUtils.findElementById(ep.billingContactOfficePhoneField));
        commonUtils.enterValueInTextField(commonUtils.findElementById(ep.billingContactOfficePhoneField),
                randomNumbers(10));
        commonUtils.enterValueInTextField(commonUtils.findElementById(ep.billingContactMobilePhoneField),
                randomNumbers(10));
        commonUtils.enterValueInTextField(commonUtils.findElementById(ep.billingContactAddressField),
                randomString() + " Address");
        commonUtils.enterValueInTextField(commonUtils.findElementById(ep.billingContactCityField), randomString() + "City");
        commonUtils.selectDropDownValue(commonUtils.findElementById(ep.billingContactStateDropdownField),
                p.getProperty("billingContactState"));
        commonUtils.enterValueInTextField(commonUtils.findElementById(ep.billingContactZipcodeField),
                randomNumbers(6));
        commonUtils.selectDropDownValue(commonUtils.findElementById(ep.paymentTermsDropdownField),
                p.getProperty("paymentTerms"));
        commonUtils.validateCheckbox(commonUtils.findElementByXpath(ep.activeCheckbox));
        commonUtils.scrollToBottomAndClick(commonUtils.findElementById(ep.saveButton));

        UsersAndRolesPage au = new UsersAndRolesPage(driver);
        commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(au.dialogueText),
                p.getProperty("entityDialogueText"),
                commonUtils.findElementByXpath(au.dialogueOkButton));
    }
}
