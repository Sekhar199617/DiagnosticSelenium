package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountDetailsPage;
import pageObjects.AddFormsPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class AddAccountDataFormTest extends BaseClass {

    @Test(groups = { "Smoke" })
    public void verify_add_accountData() {
        try {
            logger.info("****** Starting Add Account Data Form Test Case ******");

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

          //  commonUtils.validateGetText(commonUtils.findElementByXpath(addFormsPage.selectedAccountName),p.getProperty("accountName"));
            commonUtils.validateGetText(commonUtils.findElementByXpath(addFormsPage.selectedAccountName),p.getProperty("accountName"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formScopeDropdown),p.getProperty("formScopeAccount"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.formNameField),randomString());



            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formEntityTypeDropdown), p.getProperty("formEntityTypeAccount"));

            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formTypeDropdown), p.getProperty("formTypeKiosk"));;
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.formDescriptionField),randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.selectLogoImageDropdown),p.getProperty("selectLogoImage"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.formInstructionField),randomString());
            commonUtils.scrollToBottom();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.addAccountFormFieldButton),null );

            commonUtils.scrollToBottom();
            String accountFieldName = p.getProperty("accountFieldName");
            commonUtils.selectDynamicField(accountFieldName, addFormsPage.AccountDynamicLocatorPattern1);
            commonUtils.clickSelectButton("account_name");

            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.field1InstructionField),randomString());
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.saveField1Button), null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.finishedButton),null);

            commonUtils.validateGetText(commonUtils.findElementByXpath(addFormsPage.successfulMessageText), p.getProperty("successfulFormCreationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.okButton), null);

        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("****** Finished Add Account Data Form Test Case ******");
    }
}