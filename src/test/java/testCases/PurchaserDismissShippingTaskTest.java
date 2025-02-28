package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.Assert;
import pageObjects.AccountDetailsModules.AdminAccount.AccountDetailsPage;
import pageObjects.AccountDetailsModules.AdminAccount.Settings.SettingsPage;
import pageObjects.AccountDetailsModules.AdminAccount.Dashboard.DashboardPage;
import pageObjects.PurchaseLevelAccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.time.Duration;
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
            SettingsPage as = new SettingsPage(driver);

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
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            dp.selectHamburgerTab("Tasks");

            //Validate and click on shipping task Radio Button
            commonUtils.validateAndClickRadiobutton(ob.shippingTaskRadioButton);

            ob.performTableAction("purchaseUsersTable",p.getProperty("purchaserShippingTaskDismissAccountName"),"Dismiss",2);

            wait.until(ExpectedConditions.visibilityOf(commonUtils.findElementByXpath(as.successfulMessage)));
            //Confirmation Popup
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(as.successfulMessage),p.getProperty("purchaserDismissShippingTaskMessage"),commonUtils.findElementByXpath(ob.userUploadOkButton));

            //Validation Popup
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ob.taskDismissed)));

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ob.userUploadOkButton)));
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(ob.taskDismissed),p.getProperty("purchaserTaskDismissedValidationMessage"),commonUtils.findElementByXpath(ob.userUploadOkButton));

        } catch (Exception e) {
            Assert.fail();
        }


        logger.info("****** Finished Purchaser Dismiss Shipping Task Test ******");
    }
}
