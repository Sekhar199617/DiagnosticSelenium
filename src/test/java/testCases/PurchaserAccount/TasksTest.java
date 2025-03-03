package testCases.PurchaserAccount;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.Assert;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.AccountDetailsModules.Settings.SettingsPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import pageObjects.PurchaserAccount.AccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TasksTest extends BaseClass {

    public CommonUtils commonUtils;
    public AccountPage ob;
    public DashboardPage dp;
    public AccountDetailsPage ad;
    public SettingsPage as;

    @Test(groups = {"Smoke"})
    public void VerifyPurchaserDismissShippingTaskTest() {

        logger.info("****** Starting Purchaser Dismiss Shipping Task Test ******");
        try {

            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            dp = new DashboardPage(driver);
            commonUtils = new CommonUtils(driver);
            ad = new AccountDetailsPage(driver);
            ob = new AccountPage(driver);
            as = new SettingsPage(driver);
            loadTestData(
                    "./testData/accountDetailsData.json",
                    "./testData/purchaserAccountData/purchaser.json"
            );

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
            //Click on logo
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.diagnosticLogo),null);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            dp.selectHamburgerTab("Tasks");

            //Validate and click on shipping task Radio Button
            commonUtils.validateAndClickRadiobutton(ob.shippingTaskRadioButton);

            ob.performTableAction("purchaseUsersTable",getTestData("purchaserShippingTaskDismissAccountName"),"Dismiss",2);

            wait.until(ExpectedConditions.visibilityOf(commonUtils.findElementByXpath(as.successfulMessage)));
            //Confirmation Popup
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(as.successfulMessage),getTestData("purchaserDismissShippingTaskMessage"),commonUtils.findElementByXpath(ob.userUploadOkButton));

            //Validation Popup
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ob.taskDismissed)));

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ob.userUploadOkButton)));
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(ob.taskDismissed),getTestData("purchaserTaskDismissedValidationMessage"),commonUtils.findElementByXpath(ob.userUploadOkButton));

        } catch (Exception e) {
            Assert.fail();
        }

        logger.info("****** Finished Purchaser Dismiss Shipping Task Test ******");
    }
}
