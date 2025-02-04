package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsIntegrationPage;
import pageObjects.AccountDetailsPage;
import pageObjects.DashboardPage;
import pageObjects.EntityPage;
import testBase.BaseClass;
import utilities.CommonUtils;
import java.util.List;

public class SupportEntityVisibleToUserTest extends BaseClass {
    @Test
    public void verifySupportEntityVisibleToUser() {

        logger.info("****** Starting Support Entity Visible To User Test ******");
        try {
            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            EntityPage ep = new EntityPage(driver);

            // Selecting entities from hamburger menu
            dp.selectHamburgerTab("Entities");

            commonUtils.scrollToBottom();

            //Here passing name of entity and storing that entity type and clicking on edit option of that entity
            ep.editSupportEntity(ep.supportEntityChart, p.getProperty("supportEntitiesName"), "Edit");

            //In edit entity validating and clicking if the permit checkbox is ticked or not
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ep.AuthenticationTab), null);
            commonUtils.scrollToBottom();
            commonUtils.validateAndClickCheckbox(ep.checkboxLocator);
            commonUtils.scrollToBottom();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ep.updateSupportEntityButton), null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(ep.supportEntityUpdateValidationMessage), p.getProperty("supportEntityUpdateValidationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ep.supportEntityUpdatedOKbutton), null);

            //Clicking accounts tab in hamburger menu
            dp.selectHamburgerTab("Accounts");
            dp.searchForItem(p.getProperty("accountName"));
            dp.clickView();

            //Clicking on Integration tab of account details page
            AccountDetailsPage ad = new AccountDetailsPage(driver);
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Integrations");

            AccountDetailsIntegrationPage ip = new AccountDetailsIntegrationPage(driver);

            //Add new integration
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ip.newIntegrationButton), null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ip.apiMappingButton), null);
            //Selecting the above stored entity type in Integration Dropdown
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ip.typesOfIntegrationDropdown),ep.entityVariableName);
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ip.typesOfIntegrationDropdown), entityVariableName);

            //Checking the Support Entities Name is visible or not in partner dropdown
            String expectedValue = p.getProperty("supportEntitiesName");
            WebElement dropdownElement = commonUtils.findElementByXpath(ip.partnerDropdown) ;
            Select dropdown = new Select(dropdownElement);

            ip.validateDropdownValue(ip.partnerDropdown, p.getProperty("supportEntitiesName"));

        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Support Entity Visible To User Test ******");
        }
    }
