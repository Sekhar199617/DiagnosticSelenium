package testCases.AdminAccount.AccountDetailsModulesTests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.AccountDetailsModules.FormsPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class FormsTest extends BaseClass {

    @BeforeMethod(groups = {"Smoke"})
    public  void addFormCommonSteps (){
        login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

        DashboardPage dp = new DashboardPage(driver);
        CommonUtils commonUtils = new CommonUtils(driver);
        FormsPage addFormsPage = new FormsPage(driver);
        dp.searchForItem(p.getProperty("accountName"));
        dp.clickView();

        AccountDetailsPage ad = new AccountDetailsPage(driver);
        commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Forms");

        commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.addButton),null);
        commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.createNewButton),null);
        commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formScopeDropdown),p.getProperty("formScopeAccount"));
    }
    @Test(groups = {"Smoke"}, priority = 3)
    public void verify_add_accountData() {
        try {
            logger.info("****** Starting Add Account Data Form Test Case ******");

            CommonUtils commonUtils = new CommonUtils(driver);
            FormsPage addFormsPage = new FormsPage(driver);
            Thread.sleep(1000);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.formNameField), randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formEntityTypeDropdown), p.getProperty("formEntityTypeAccount"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formTypeDropdown), p.getProperty("formTypeKiosk"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.formDescriptionField), randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.selectLogoImageDropdown), p.getProperty("selectLogoImage"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.formInstructionField), randomString());
            commonUtils.scrollToBottom();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.addAccountFormFieldButton), null);
            commonUtils.scrollToBottom();
            commonUtils.selectDynamicField(p.getProperty("availableFieldNameAccount"), addFormsPage.availableFieldAccount);
            commonUtils.clickSelectButton(addFormsPage.availableFieldSelectButton);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.field1InstructionField), randomString());
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.saveField1Button), null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.finishedButton), null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(addFormsPage.successfulMessageText), p.getProperty("successfulFormCreationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.okButton), null);
        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("****** Finished Add Account Data Form Test Case ******");
    }

    @Test(groups = {"Smoke"}, priority = 2)
    public void verify_add_event() {
        try {
            logger.info("****** Starting Add Patient Data Form Test Case ******");

            CommonUtils commonUtils = new CommonUtils(driver);
            FormsPage addFormsPage = new FormsPage(driver);
            Thread.sleep(2000);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.formNameField), randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formEntityTypeDropdown), p.getProperty("formEntityTypePatient"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formTypeDropdown), p.getProperty("formTypeSelfRegistration"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.formDescriptionField), randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.selectLogoImageDropdown), p.getProperty("selectLogoImage"));
            commonUtils.scrollToBottom();
            commonUtils.selectBundles(p.getProperty("bundleName"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.rightArrowButton),null);
            commonUtils.scrollToBottom();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.addFormFieldButton),null);
            commonUtils.scrollToBottom();
            commonUtils.selectDynamicField(p.getProperty("availableFieldNamePatient1"),addFormsPage.availableField);
            commonUtils.clickSelectButton(addFormsPage.availableFieldSelectButton);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.saveField1button),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.addFormFieldButton),null);
            commonUtils.scrollToBottom();
            commonUtils.selectDynamicField(p.getProperty("availableFieldNamePatient"), addFormsPage.availableField);
            commonUtils.clickSelectButton(addFormsPage.availableFieldSelectButton);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.saveField1button),null);
            commonUtils.scrollToBottom();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.consentCheckbox),null);
            commonUtils.scrollToBottom();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.patientFinishedButton), null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(addFormsPage.successfulMessageText), p.getProperty("successfulFormCreationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.okButton), null);
        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("****** Finished Add Patient Data Form Test Case ******");
    }

    @Test(groups = {"Smoke"}, priority = 1)
    public void verify_create_form_with_custom_field() {
        try {
            logger.info("****** Starting Create Form With Custom Field Test Case ******");

            CommonUtils commonUtils = new CommonUtils(driver);
            FormsPage addFormsPage = new FormsPage(driver);
            Thread.sleep(1000);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.formNameField), randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formEntityTypeDropdown), p.getProperty("formEntityTypeAccount"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formTypeDropdown), p.getProperty("formTypeKiosk"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.formDescriptionField), randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.selectLogoImageDropdown), p.getProperty("selectLogoImage"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.formInstructionField), randomString());
            commonUtils.scrollToBottom();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.addAccountFormFieldButton), null);
            commonUtils.scrollToBottom();
            commonUtils.selectDynamicField(p.getProperty("availableFieldNameAccount"), addFormsPage.availableFieldAccount);
            commonUtils.clickSelectButton(addFormsPage.availableFieldSelectButton);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.field1InstructionField), randomString());
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.saveField1Button), null);

            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.addLegalConsentButton), null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.createLegalConsentButton), null);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.consentFormNameField), randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.instructionlegalConsentFormField), randomString());
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.saveLegalConsentFormButton), null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(addFormsPage.successfulMessageText),p.getProperty("consentFormSuccessfulMessageText"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.okButton),null);
            commonUtils.scrollToBottom();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.patientFinishedButton), null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(addFormsPage.successfulMessageText), p.getProperty("successfulFormCreationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.okButton), null);


        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("****** Finished Create Form With Custom Field Test Case ******");
    }

    @AfterMethod
    public void deleteCookies() {
        // Delete cookies after each test
        if (driver != null) {
            driver.manage().deleteAllCookies();
        }
        }
    }


