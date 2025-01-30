package testCases;

import java.security.cert.Certificate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountDetailsPage;
import pageObjects.AddFormsPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class AddPatientDataFormTest extends BaseClass {



    @Test(groups = { "Smoke" })
    public void verify_add_event() {
        try {
            logger.info("****** Starting Add Patient Data Form Test Case ******");
            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);

            commonUtils.enterValueInTextField(dp.searchField, p.getProperty("accountName"));
            commonUtils.clickOnElement(dp.searchButton, "Search");
            commonUtils.clickOnElement(dp.actionsDropDown, null);
            commonUtils.clickOnElement(dp.view, "View");

            AccountDetailsPage ad = new AccountDetailsPage(driver);
            commonUtils.selectTab(ad.tabList, "Forms");

            AddFormsPage addFormsPage = new AddFormsPage(driver);

            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.addButton),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.createNewButton),null);

            commonUtils.validateGetText(commonUtils.findElementByXpath(addFormsPage.selectedAccountName),p.getProperty("accountName"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formScopeDropdown),p.getProperty("formScopeAccount"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.formNameField),randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formEntityTypeDropdown), p.getProperty("formEntityTypePatient"));

            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formTypeDropdown), p.getProperty("formTypeSelfRegistration"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.afterFormSubmissionRedirectToDropDown), p.getProperty("afterFormSubmissionRedirect"));

            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.formDescriptionField),randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.selectLogoImageDropdown),p.getProperty("selectLogoImage"));

            commonUtils.scrollToBottom();
            commonUtils.selectBundles(p.getProperty("bundleName"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.rightArrowButton),null);
            commonUtils.scrollToBottom();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.addFormFieldButton),null);
            commonUtils.scrollToBottom();

            String fieldName = p.getProperty("fieldName");
            commonUtils.selectDynamicField(fieldName, addFormsPage.dynamicLocatorPattern1);
            commonUtils.clickSelectButton(p.getProperty("fieldNameSelectButton"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.saveField1button),null);
            commonUtils.scrollToBottom();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.consentCheckbox),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.patientFinishedButton),null);

            commonUtils.validateGetText(commonUtils.findElementByXpath(addFormsPage.successfulMessageText), p.getProperty("successfulFormCreationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.okButton), null);


        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("****** Finished Add Patient Data Form Test Case ******");
    }

}