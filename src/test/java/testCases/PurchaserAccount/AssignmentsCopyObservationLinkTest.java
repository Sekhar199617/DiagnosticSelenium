package testCases.PurchaserAccount;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import pageObjects.PurchaserAccount.AccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AssignmentsCopyObservationLinkTest extends BaseClass {


    public CommonUtils commonUtils;
    public AccountPage ob;
    public DashboardPage dp;
    public AccountDetailsPage ad;

    @Test(groups = {"Smoke"})
    public void VerifyPurchaseCopyLinkFromObservationColumn() {

        logger.info("****** Starting Purchase Copy Link From Observation Column Test ******");
        try {

            loadTestData("./testData/PurchaserAccountData/purchaser.json",
                    "./testData/AdminAccountData/accountDetailsData.json",
                    "./testData/AdminAccountData/adminLoginData.json");

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            dp = new DashboardPage(driver);
            commonUtils = new CommonUtils(driver);
            ad = new AccountDetailsPage(driver);
            ob = new AccountPage(driver);

            dp.searchForItem(getTestData("accountName"));
            dp.clickView();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Short wait time

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ob.userTypeDropdown)));
            //Select account admin in user type dropdown
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.userTypeDropdown), getTestData("usersUserTypeAccountAdmin"));

            //Clicking on Assign Test in action dropdown for a account
            ob.performTableAction("accountsTableUserRoles", getTestData("userAccountAdminName"), "Assign Tests",1);

            //Switch the tab
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            //Click on logo
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.diagnosticLogo),null);

            dp.selectHamburgerTab("Assignments");

            ob.clickFirstAssignmentView("assignmentsTable");
            Thread.sleep(2000);

            ob.clickFirstCopyIcon("detailsAssignmentsTable");
            String copiedURL = ob.getClipboardText();

            commonUtils.validateGetText(commonUtils.findElementByXpath(ob.successfulConfirmationMessage),getTestData("observationLinkCopyValidationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.successfulConfirmationOkButton),null);

            ob.openNewTabWithURL(copiedURL);

            try {
                WebElement saveLanguageButton = wait.until(ExpectedConditions.visibilityOf(commonUtils.findElementByXpath(ob.saveLanguage)));

                if (saveLanguageButton.isDisplayed()) {
                    saveLanguageButton.click();
                    System.out.println("Clicked on Save Language Preference button.");
                }
            } catch (Exception e) {
                System.out.println("Save Language Preference button not found within timeout. Continuing test.");
            }

            WebElement welcomeHeader = commonUtils.findElementByXpath(ob.welcomeTextMessage);
            String actualText = welcomeHeader.getText();
            System.out.println("Actual Welcome Header Text: " + actualText);

            Assert.assertTrue(actualText.trim().contains("Welcome Back"), "Welcome message does not contain 'Welcome Back'!");

        } catch (Exception e) {
            Assert.fail();
        }


        logger.info("****** Finished Purchase Copy Link From Observation Column  Test ******");
    }
}


