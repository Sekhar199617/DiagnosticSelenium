package testCases.AdminAccount.HamburgerMenuModulesTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.AccountDetailsModules.Settings.SSOPage;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import pageObjects.AdminAccount.HamburgerMenuModules.EntityPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class EntitiesVisibilityToUser extends BaseClass {
    @Test
    public void verifySupportEntityVisibleToUser() {

        logger.info("****** Starting Support Entity Visible To User Test ******");
        try {

            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            EntityPage ep = new EntityPage(driver);

            loadTestData(
                    "./testData/AdminAccountData/adminLoginData.json",
                    "./testData/AdminAccountData/dashboardData.json",
                    "./testData/AdminAccountData/hamburgerMenuModulesData.json"
            );

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            // Selecting entities from hamburger menu
            dp.selectHamburgerTab("Entities");

            commonUtils.scrollToBottom();

            //Here passing name of entity and storing that entity type and clicking on edit option of that entity
            ep.editSupportEntity(ep.supportEntityChart, getTestData("supportEntitiesName"), "Edit");

            //In edit entity validating and clicking if the permit checkbox is ticked or not
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ep.AuthenticationTab), null);
            commonUtils.scrollToBottom();
            commonUtils.validateAndClickCheckbox(ep.checkboxLocator);
            commonUtils.scrollToBottom();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ep.updateSupportEntityButton), null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(ep.supportEntityUpdateValidationMessage),
                    getTestData("supportEntityUpdateValidationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ep.supportEntityUpdatedOKbutton),
                    null);

            //Clicking accounts tab in hamburger menu
            commonUtils.scrollToUp();
            dp.selectHamburgerTab("Accounts");
            dp.searchForItem(getTestData("accountName"));
            dp.clickView();

            //Clicking on Integration tab of account details page
            AccountDetailsPage ad = new AccountDetailsPage(driver);
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Integrations");

            SSOPage ip = new SSOPage(driver);

            //Add new integration
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ip.newIntegrationButton), null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ip.apiMappingButton), null);
            //Selecting the above stored entity type in Integration Dropdown
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ip.typesOfIntegrationDropdown),
                    ep.entityVariableName);

            //Checking the Support Entities Name is visible or not in partner dropdown
            ip.validateDropdownValue(ip.partnerDropdown, getTestData("supportEntitiesName"));

        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Support Entity Visible To User Test ******");
    }
}