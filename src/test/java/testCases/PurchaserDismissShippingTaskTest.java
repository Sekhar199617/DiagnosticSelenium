package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsPage;
import pageObjects.AccountDetailsSettingsPage;
import pageObjects.DashboardPage;
import pageObjects.PurchaseLevelAccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PurchaserDismissShippingTaskTest extends BaseClass {

    public CommonUtils commonUtils;
    public PurchaseLevelAccountPage ob;
    public DashboardPage dp;
    public AccountDetailsPage ad;
    public AccountDetailsSettingsPage as;

    @Test(groups = {"Smoke"})
    public void VerifyPurchaserDismissShippingTaskTest() {

        logger.info("****** Starting Purchaser Dismiss Shipping Task Test ******");
        try {

            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            dp = new DashboardPage(driver);
            commonUtils = new CommonUtils(driver);
            ad = new AccountDetailsPage(driver);
            ob = new PurchaseLevelAccountPage(driver);
            as = new AccountDetailsSettingsPage(driver);
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

    public void loadTestDataForTest() {
        List<String> jsonFiles = Arrays.asList(
                "./testData/accountDetailsData.json",
                "./testData/purchaserAccountData/purchaser.json"
        );

        testData = commonUtils.mergeMultipleJsonFiles(jsonFiles);
    }
}
