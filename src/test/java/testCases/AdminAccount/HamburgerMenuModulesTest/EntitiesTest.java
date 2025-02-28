package testCases.AdminAccount.HamburgerMenuModulesTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.AccountDetailsModules.UsersAndRolesPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import pageObjects.AdminAccount.HamburgerMenuModules.EntitiesPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class EntitiesTest extends BaseClass {

    @Test
    public void verifyAddSupportEntity() {

        logger.info("****** Starting Add Support Entity Test ******");
        try{
            EntitiesPage entitiesPage = new EntitiesPage(driver);
            DashboardPage dashboardPage = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            UsersAndRolesPage usersAndRolesPage = new UsersAndRolesPage(driver);

            String loginJsonPath = "./testData//adminLoginData.json";
            loadTestData(loginJsonPath);

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            String hamburgerMenuModulesDataJsonPath = "./testData//hamburgerMenuModulesData.json";
            loadTestData(hamburgerMenuModulesDataJsonPath);

            dashboardPage.selectHamburgerTab("Entities");

            commonUtils.clickOnElement(commonUtils.findElementByXpath(entitiesPage.addSupportEntityButton),
                    null);
            Thread.sleep(2000);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(entitiesPage.entityNameField),
                    "Entity " + randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementByName(entitiesPage.typeDropDownField),
                    getTestData("entityType"));
            commonUtils.selectTab(commonUtils.findElementsByCssSelector(entitiesPage.tabList), "Authentication");
            commonUtils.selectCheckbox(commonUtils.findElementById(entitiesPage.permitAccountSupportCheckbox));
            commonUtils.selectCheckbox(commonUtils.findElementById(entitiesPage.activeOutboundApiCheckbox));
            commonUtils.selectCheckbox(commonUtils.findElementById(entitiesPage.activeOutboundWebhookCheckbox));

            commonUtils.enterValueInTextField(commonUtils.findElementById(entitiesPage.productionApiUrlField),
                    "https://" + randomString().toLowerCase() + "prod_api_url.com");
            commonUtils.enterValueInTextField(commonUtils.findElementById(entitiesPage.productionApiKeyField),
                    randomString().toLowerCase() + randomAlphaNumeric());
            commonUtils.enterValueInTextField(commonUtils.findElementById(entitiesPage.productionApiSecretField),
                    randomString().toLowerCase() + randomAlphaNumeric());
            commonUtils.enterValueInTextField(commonUtils.findElementById(entitiesPage.productionWebhookUrlField),
                    "https://" + randomString().toLowerCase() + "prod_webhook_url.com");
            commonUtils.enterValueInTextField(commonUtils.findElementById(entitiesPage.productionWebhookApiKeyField),
                    randomString().toLowerCase() + randomAlphaNumeric());
            commonUtils.selectCheckbox(commonUtils.findElementById(entitiesPage.acceptInboundWebhookCheckbox));
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByCssSelector(entitiesPage.authenticationApiKeyField));
            commonUtils.enterValueInTextField(commonUtils.findElementByCssSelector(entitiesPage.authenticationApiKeyField),
                    randomString().toLowerCase() + randomAlphaNumeric());
            commonUtils.scrollToElement(commonUtils.findElementByXpath(entitiesPage.authenticationApiKeyRadioButton));
            commonUtils.selectRadioButton(commonUtils.findElementByXpath(entitiesPage.authenticationApiKeyRadioButton));

            commonUtils.selectTab(commonUtils.findElementsByCssSelector(entitiesPage.tabList), "Comms");
            commonUtils.selectRandomCheckboxes(commonUtils.findElementsByCssSelector(
                    entitiesPage.entityCommunicationCheckboxList));

            commonUtils.selectTab(commonUtils.findElementsByCssSelector(entitiesPage.tabList), "Contacts");
            commonUtils.enterValueInTextField(commonUtils.findElementById(entitiesPage.contactNameField), randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementById(entitiesPage.emailAddressField),
                    randomString().toLowerCase() + "@gmail.com");
            commonUtils.enterValueInTextField(commonUtils.findElementById(entitiesPage.officePhoneField),
                    randomNumbers(10));
            commonUtils.enterValueInTextField(commonUtils.findElementById(entitiesPage.mobilePhoneField),
                    randomNumbers(10));
            commonUtils.enterValueInTextField(commonUtils.findElementById(entitiesPage.addressField),
                    randomString() + " Address");
            commonUtils.enterValueInTextField(commonUtils.findElementById(entitiesPage.cityField),
                    randomString() + "City");
            commonUtils.selectDropDownValue(commonUtils.findElementById(entitiesPage.stateDropdownField),
                    getTestData("entityState"));
            commonUtils.enterValueInTextField(commonUtils.findElementById(entitiesPage.zipcodeField),
                    randomNumbers(6));

            commonUtils.enterValueInTextField(commonUtils.findElementById(entitiesPage.billingContactNameField),
                    randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementById(entitiesPage.billingContactEmailField),
                    randomString().toLowerCase() + "@gmail.com");
            commonUtils.scrollToBottomAndClick(commonUtils.findElementById(entitiesPage.billingContactOfficePhoneField));
            commonUtils.enterValueInTextField(commonUtils.findElementById(entitiesPage.billingContactOfficePhoneField),
                    randomNumbers(10));
            commonUtils.enterValueInTextField(commonUtils.findElementById(entitiesPage.billingContactMobilePhoneField),
                    randomNumbers(10));
            commonUtils.enterValueInTextField(commonUtils.findElementById(entitiesPage.billingContactAddressField),
                    randomString() + " Address");
            commonUtils.enterValueInTextField(commonUtils.findElementById(entitiesPage.billingContactCityField),
                    randomString() + "City");
            commonUtils.selectDropDownValue(commonUtils.findElementById(entitiesPage.billingContactStateDropdownField),
                    getTestData("billingContactState"));
            commonUtils.enterValueInTextField(commonUtils.findElementById(entitiesPage.billingContactZipcodeField),
                    randomNumbers(6));
            commonUtils.selectDropDownValue(commonUtils.findElementById(entitiesPage.paymentTermsDropdownField),
                    getTestData("paymentTerms"));
            commonUtils.validateCheckbox(commonUtils.findElementByXpath(entitiesPage.activeCheckbox));
            commonUtils.scrollToBottomAndClick(commonUtils.findElementById(entitiesPage.saveButton));
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(
                            usersAndRolesPage.dialogueText),
                    getTestData("entityDialogueText"),
                    commonUtils.findElementByXpath(usersAndRolesPage.dialogueOkButton)
            );
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Add Support Entity Test ******");
    }
}