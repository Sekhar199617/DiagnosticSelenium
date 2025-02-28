package testCases.AdminAccount.HamburgerMenuModulesTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.AccountDetailsModules.Settings.SSOPage;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import pageObjects.AdminAccount.HamburgerMenuModules.EntityPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class SupportEntityVisibleToUserTest extends BaseClass {
    @Test
    public void verifySupportEntityVisibleToUser() {

        logger.info("****** Starting Support Entity Visible To User Test ******");
        try {
            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dashboardPage = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            EntityPage entityPage = new EntityPage(driver);

            // Selecting entities from hamburger menu
            dashboardPage.selectHamburgerTab("Entities");

            commonUtils.scrollToBottom();

            //Here passing name of entity and storing that entity type and clicking on edit option of that entity
            entityPage.editSupportEntity(entityPage.supportEntityChart, p.getProperty("supportEntitiesName"),
                    "Edit");

            //In edit entity validating and clicking if the permit checkbox is ticked or not
            commonUtils.clickOnElement(commonUtils.findElementByXpath(entityPage.AuthenticationTab), null);
            commonUtils.scrollToBottom();
            commonUtils.validateAndClickCheckbox(entityPage.checkboxLocator);
            commonUtils.scrollToBottom();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(entityPage.updateSupportEntityButton),
                    null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(entityPage.supportEntityUpdateValidationMessage),
                    p.getProperty("supportEntityUpdateValidationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(entityPage.supportEntityUpdatedOKbutton),
                    null);

            //Clicking accounts tab in hamburger menu
            commonUtils.scrollToUp();
            dashboardPage.selectHamburgerTab("Accounts");
            dashboardPage.searchForItem(p.getProperty("accountName"));
            dashboardPage.clickView();

            //Clicking on Integration tab of account details page
            AccountDetailsPage ad = new AccountDetailsPage(driver);
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Integrations");

            SSOPage ip = new SSOPage(driver);

            //Add new integration
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ip.newIntegrationButton), null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ip.apiMappingButton), null);
            //Selecting the above stored entity type in Integration Dropdown
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ip.typesOfIntegrationDropdown),
                    entityPage.entityVariableName);

            //Checking the Support Entities Name is visible or not in partner dropdown
            ip.validateDropdownValue(ip.partnerDropdown, p.getProperty("supportEntitiesName"));

        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Support Entity Visible To User Test ******");
        }
    }
