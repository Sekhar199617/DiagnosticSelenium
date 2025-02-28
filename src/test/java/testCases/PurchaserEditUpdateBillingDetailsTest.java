package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import pageObjects.AccountDetailsPage;
import pageObjects.DashboardPage;
import pageObjects.PurchaseLevelAccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PurchaserEditUpdateBillingDetailsTest extends BaseClass {
    public CommonUtils commonUtils;
    public PurchaseLevelAccountPage ob;
    public DashboardPage dp;
    public AccountDetailsPage ad;

    @Test(groups = {"Smoke"})
    public void VerifyPurchaserEditUpdateBillingDetailsTest(){

        logger.info("***** Starting Purchaser Edit Update Billing Details Test *****");
        try {
            login(p.getProperty("adminEmail"),p.getProperty("adminPassword"),true);

            dp = new DashboardPage(driver);
            commonUtils = new CommonUtils(driver);
            ad = new AccountDetailsPage(driver);
            ob = new PurchaseLevelAccountPage(driver);
            loadTestDataForTest();

            dp.searchForItem(getTestData("accountName"));
            dp.clickView();

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");

            //Select account admin in user type dropdown
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.userTypeDropdown), getTestData("usersUserTypeAccountAdmin"));

            //Clicking on Assign Test in action dropdown for a account
            ob.performTableAction("accountsTableUserRoles", getTestData("userAccountAdminName"), "Assign Tests",1);

            //Switch the tab
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            dp.clickOnLogo(); // To change small case letters to normal

            //Click on setting in hamburger tab
            dp.selectHamburgerTab("Settings");

            //Click on Billing tab in setting
            commonUtils.selectTab(commonUtils.findElementsByXpath(ob.settingTab),"Billing");
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.editSettingButton),null);

            //Edit or Update Details
            commonUtils.selectRandomDropDown(commonUtils.findElementsByXpath(ob.billingContactDropdown));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.billingCountryDropdown),getTestData("purchaserBillingCountryDropdown"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.billingAddress1Field),getTestData("purchaserBillingAddress1"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.billingAddress2Field),getTestData("purchaserBillingAddress2"));
            commonUtils.scrollToElement(commonUtils.findElementByXpath(ob.billingCityField));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.billingCityField),getTestData("purchaserBillingCity"));
            commonUtils.scrollToElement(commonUtils.findElementByXpath(ob.billingStateField));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.billingStateField),getTestData("purchaserBillingState"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.billingPostalCodeField),getTestData("purchaserBillingPostalCode"));

            commonUtils.scrollToUp();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.billingUpdateButton),null);

            commonUtils.validateDialogueTextAndClickConfirm((commonUtils.findElementByXpath(ob.successfulConfirmationMessage)),getTestData("purchaserBillingDetailsUpdateMessage"),commonUtils.findElementByXpath(ob.successfulConfirmationOkButton));

        } catch (Exception e) {
            Assert.fail();
        }

        logger.info("****** Finished Purchaser Edit Update Billing Details Test ******");
    }

    public void loadTestDataForTest() {
        List<String> jsonFiles = Arrays.asList(
                "./testData/accountDetailsData.json",
                "./testData/purchaserAccountData/purchaser.json"
        );

        testData = commonUtils.mergeMultipleJsonFiles(jsonFiles);
    }

}
