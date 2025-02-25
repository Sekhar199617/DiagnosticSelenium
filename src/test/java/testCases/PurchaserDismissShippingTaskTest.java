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

public class PurchaserDismissShippingTaskTest extends BaseClass {

    public CommonUtils commonUtils;
    public PurchaseLevelAccountPage ob;

    @Test(groups = {"Smoke"})
    public void VerifyPurchaserDismissShippingTaskTest() {

        logger.info("****** Starting Purchaser Dismiss Shipping Task Test ******");
        try {

            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

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
            //Click on logo
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.diagnosticLogo),null);

            dp.selectHamburgerTab("Tasks");

            //Validate and click on shipping task Radio Button
            commonUtils.validateAndClickRadiobutton(ob.shippingTaskRadioButton);
            ob.performTableAction("purchaseUsersTable",p.getProperty("purchaserShippingTaskDismissAccountName"),"Dismiss",2);

            //Confirmation Popup
            commonUtils.validateGetText(commonUtils.findElementByXpath(ob.successfulConfirmationMessage),p.getProperty("purchaserDismissShippingTaskMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.successfulConfirmationOkButton),null);
            Thread.sleep(1000);
            //Validation Popup
            commonUtils.validateGetText(commonUtils.findElementByXpath(ob.successfulConfirmationMessage),p.getProperty("purchaserTaskDismissedValidationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.successfulConfirmationOkButton),null);

        } catch (Exception e) {
            Assert.fail();
        }


        logger.info("****** Finished Purchaser Dismiss Shipping Task Test ******");
    }
}
