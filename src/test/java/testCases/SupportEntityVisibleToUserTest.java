package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsIntegrationPage;
import pageObjects.AccountDetailsPage;
import pageObjects.DashboardPage;
import pageObjects.EntityPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.util.Arrays;
import java.util.List;

public class SupportEntityVisibleToUserTest extends BaseClass {

    public DashboardPage dp;
    public CommonUtils commonUtils;
    public EntityPage ep;
    public AccountDetailsPage ad;
    public AccountDetailsIntegrationPage ip;


    @Test
    public void verifySupportEntityVisibleToUser() {

        logger.info("****** Starting Support Entity Visible To User Test ******");
        try {
            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            dp = new DashboardPage(driver);
            commonUtils = new CommonUtils(driver);
            ep = new EntityPage(driver);
            ad = new AccountDetailsPage(driver);
            ip = new AccountDetailsIntegrationPage(driver);
            loadTestDataForTest();

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
            commonUtils.validateGetText(commonUtils.findElementByXpath(ep.supportEntityUpdateValidationMessage), getTestData("supportEntityUpdateValidationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ep.supportEntityUpdatedOKbutton), null);

            //Clicking accounts tab in hamburger menu
            commonUtils.scrollToUp();
            dp.selectHamburgerTab("Accounts");
            dp.searchForItem(getTestData("accountName"));
            dp.clickView();

            //Clicking on Integration tab of account details page
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Integrations");

            //Add new integration
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ip.newIntegrationButton), null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ip.apiMappingButton), null);
            //Selecting the above stored entity type in Integration Dropdown
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ip.typesOfIntegrationDropdown),ep.entityVariableName);

            //Checking the Support Entities Name is visible or not in partner dropdown
            ip.validateDropdownValue(ip.partnerDropdown, getTestData("supportEntitiesName"));

        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Support Entity Visible To User Test ******");
        }

    public void loadTestDataForTest() {
        List<String> jsonFiles = Arrays.asList(
                "./testData/adminAccountData/accountDetailsData.json",
                "./testData/adminAccountData/dashboardData.json"
        );

        testData = commonUtils.mergeMultipleJsonFiles(jsonFiles); // ✅ Directly assign merged data
    }
    }
