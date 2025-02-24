package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsPage;
import pageObjects.DashboardPage;
import pageObjects.PurchaseLevelAccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class PurchaserEditUpdateBillingDetailsTest extends BaseClass {
    public CommonUtils commonUtils;
    public PurchaseLevelAccountPage ob;

    @Test(groups = {"Smoke"})
    public void VerifyPurchaserEditUpdateBillingDetailsTest(){

        logger.info("***** Starting Purchaser Edit Update Billing Details Test *****");
        try {
            login(p.getProperty("adminEmail"),p.getProperty("adminPassword"),true);

            DashboardPage dp = new DashboardPage(driver);

            commonUtils = new CommonUtils(driver);
            dp.searchForItem(p.getProperty("accountName"));
            dp.clickView();

            AccountDetailsPage ad = new AccountDetailsPage(driver);
            ob = new PurchaseLevelAccountPage(driver);

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");

            //Select account admin in user type dropdown
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.userTypeDropdown), p.getProperty("usersUserTypeAccountAdmin"));

            //Clicking on Assign Test in action dropdown for a account
            ob.performTableAction("accountsTableUserRoles", p.getProperty("userAccountAdminName"), "Assign Tests",1);

            //Switch the tab
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            DashboardPage dashboardPage = new DashboardPage(driver);
            dashboardPage.clickOnLogo(); // To change small case letters to normal

            //Click on setting in hamburger tab
            dp.selectHamburgerTab("Settings");

            //Click on Billing tab in setting
            commonUtils.selectTab(commonUtils.findElementsByXpath(ob.settingTab),"Billing");
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.editSettingButton),null);

            //Edit or Update Details
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.billingContactDropdown),p.getProperty("purchaserBillingContactType"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.billingCountryDropdown),p.getProperty("purchaserBillingCountryDropdown"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.billingAddress1Field),p.getProperty("purchaserBillingAddress1"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.billingAddress2Field),p.getProperty("purchaserBillingAddress2"));
            commonUtils.scrollToElement(commonUtils.findElementByXpath(ob.billingCityField));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.billingCityField),p.getProperty("purchaserBillingCity"));
            commonUtils.scrollToElement(commonUtils.findElementByXpath(ob.billingStateField));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.billingStateField),p.getProperty("purchaserBillingState"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.billingPostalCodeField),p.getProperty("purchaserBillingPostalCode"));

            commonUtils.scrollToUp();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.billingUpdateButton),null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(ob.successfulConfirmationMessage),p.getProperty("purchaserBillingDetailsUpdateMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.successfulConfirmationOkButton),null);

        } catch (Exception e) {
            AssertJUnit.fail();
        }

        logger.info("****** Finished Purchaser Edit Update Billing Details Test ******");
    }

}
