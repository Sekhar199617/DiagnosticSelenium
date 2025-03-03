package testCases.PurchaserAccount;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.AdminAccount.AccountDetailsModules.UsersAndRolesPage;
import pageObjects.AdminAccount.AccountDetailsModules.OnlineEducationPage;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;
import java.util.ArrayList;
import java.util.List;

public class Settings_OnlineEducationTest extends BaseClass {

    @Test(groups= {"Smoke"})
    public void verifyExperiencesForElectronicBundlesPresence() {

        try{

            logger.info("****** Starting Verify Experiences For Electronic Bundles Presence Test ******");

            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dashboardPage = new DashboardPage(driver);
            OnlineEducationPage aoe = new OnlineEducationPage(driver);
            AccountDetailsPage ad = new AccountDetailsPage(driver);
            UsersAndRolesPage au = new UsersAndRolesPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);

            dashboardPage.searchForItem(p.getProperty("accountName"));
            dashboardPage.clickView();

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Online Education");

            commonUtils.validateRadioButton(commonUtils.findElementById(aoe.electronicAndAdhocRadioButton));
            List<String[]> electronicRowDataList = aoe.getTableRowData(aoe.electronicTableRows);

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");
            commonUtils.selectDropDownValue(commonUtils.findElementById(au.userTypeDropDownField), "Account Admins");
            au.performActionOnUser("accountsTableUserRoles", p.getProperty("userAccountAdminName"),
                    "Assign Tests");

            List<String> electronicTabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(electronicTabs.get(1));

            dashboardPage.clickOnLogo(); // To change small case letters to normal

            dashboardPage.selectHamburgerTab("Settings");
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Online Education");
            commonUtils.validateRadioButton(commonUtils.findElementById(aoe.electronicAndAdhocRadioButton));

            WebElement experiencesForElectronicText = commonUtils.findElementByXpath(aoe.experiencesForElectronicText);

            if(experiencesForElectronicText!= null) {
                Assert.assertTrue(experiencesForElectronicText.isDisplayed(), "Option is Present");
                commonUtils.validateGetText(experiencesForElectronicText,
                        "Experiences for Electronic and Ad-hoc Bundles");
            }else{
                Assert.fail("Option is not present");
            }

            boolean electronicDataConsistent = aoe.validateDataInPurchaserAccount(aoe.electronicTableRows, electronicRowDataList);
            if (electronicDataConsistent) {
                System.out.println("Test Passed: Experiences For Electronic Bundles Data is Consistent.");
            } else {
                Assert.fail("Test Failed: Experiences For Electronic Bundles Data Mismatch Detected.");
            }

            WebElement electronicManageButton = commonUtils.findElementByXpath(aoe.manageButton);

            if (electronicManageButton != null) {
                Assert.fail("Electronic Manage button should not be present on the page, but it was found.");
            } else {
                System.out.println("Test passed: Electronic Manage button is not present, as expected.");
            }

            driver.close();
            driver.switchTo().window(electronicTabs.get(0));

            logger.info("****** Finished Verify Experiences For Electronic Bundles Presence Test ******");

            logger.info("****** Starting Verify Experiences For Provisioned Orders Presence Test ******");
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Online Education");
            commonUtils.selectRadioButton(commonUtils.findElementById(aoe.purchaserExpProvisionedOrderRadioButton));
            List<String[]> poRowDataList = aoe.getTableRowData(aoe.poTableRows);

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");
            commonUtils.selectDropDownValue(commonUtils.findElementById(au.userTypeDropDownField), "Account Admins");
            au.performActionOnUser("accountsTableUserRoles", p.getProperty("userAccountAdminName"),
                    "Assign Tests");

            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            dashboardPage.clickOnLogo(); // To change small case letters to normal

            dashboardPage.selectHamburgerTab("Settings");
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Online Education");
            commonUtils.selectRadioButton(commonUtils.findElementById(aoe.purchaserExpProvisionedOrderRadioButton));

            WebElement experiencesForPOText = commonUtils.findElementByXpath(aoe.experiencesForProvisionedOrdersText);

            if (experiencesForPOText != null) {
                Assert.assertTrue(experiencesForPOText.isDisplayed(), "Option is Present");
                commonUtils.validateGetText(experiencesForPOText,
                        "Experiences for Provisioned Orders");
            } else {
                Assert.fail("Option is not present");
            }

            boolean isDataConsistent = aoe.validateDataInPurchaserAccount(aoe.poTableRows, poRowDataList);
            if (isDataConsistent) {
                System.out.println("Test Passed: Experiences For Provisioned Orders Data is Consistent.");
            } else {
                Assert.fail("Test Failed: Experiences For Provisioned Orders Data Mismatch Detected.");
            }

            WebElement manageButton = commonUtils.findElementByXpath(aoe.manageButton);

            if (manageButton != null) {
                Assert.fail("Manage button should not be present on the page, but it was found.");
            } else {
                System.out.println("Test passed: Manage button is not present, as expected.");
            }

        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Verify Experiences For Provisioned Orders Presence Test ******");
    }

}