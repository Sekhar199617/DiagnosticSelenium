package testCases;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsPage;
import pageObjects.DashboardPage;
import pageObjects.PurchaseLevelAccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PurchaseCopyLinkFromObservationColumnTest extends BaseClass {



    public CommonUtils commonUtils;
    public PurchaseLevelAccountPage ob;

    @Test(groups = {"Smoke"})
    public void VerifyPurchaseCopyLinkFromObservationColumn() {

        logger.info("****** Starting Purchase Copy Link From Observation Column Test ******");
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

            dp.selectHamburgerTab("Assignments");

            ob.clickFirstAssignmentView("assignmentsTable");
            Thread.sleep(2000);

            ob.clickFirstCopyIcon("detailsAssignmentsTable");
            String copiedURL = ob.getClipboardText();

            commonUtils.validateGetText(commonUtils.findElementByXpath(ob.successfulConfirmationMessage),p.getProperty("observationLinkCopyValidationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.successfulConfirmationOkButton),null);

            ob.openNewTabWithURL(copiedURL);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // Short wait time

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


