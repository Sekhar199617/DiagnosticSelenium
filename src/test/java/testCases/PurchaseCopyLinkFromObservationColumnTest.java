package testCases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsPage;
import pageObjects.DashboardPage;
import pageObjects.PurchaseLevelAccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.util.ArrayList;
import java.util.List;

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
            ob.performActionOnUser("accountsTableUserRoles", p.getProperty("userAccountAdminName"), "Assign Tests");

            //Switch the tab
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            dp.selectHamburgerTab("Assignments");
           // ob.selectHamburgerTab("Assignments");
            ob.clickOnAssignmentView("assignmentsTable",p.getProperty("purchaserAssignmentNameToClickView"));
            Thread.sleep(2000);

            //Copy observation link
            ob.clickOnObservationLink("detailsAssignmentsTable",p.getProperty("purchaserAssignmentNameToClickView"));
            String copiedURL = ob.getClipboardText();

            commonUtils.validateGetText(commonUtils.findElementByXpath(ob.successfulConfirmationMessage),p.getProperty("observationLinkCopyValidationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.successfulConfirmationOkButton),null);

            ob.openNewTabWithURL(copiedURL);

            WebElement welcomeHeader = commonUtils.findElementByXpath(ob.welcomeTextMessage);
            String actualText = welcomeHeader.getText();
            String firstName = p.getProperty("purchaserAssignmentNameToClickView").split(" ")[0];

            // Construct the expected text dynamically
            String expectedText = "Welcome Back, " + firstName;

            // Assertion to validate the message
            Assert.assertEquals(actualText, expectedText, "Welcome message is incorrect!");


        } catch (Exception e) {
            Assert.fail();
        }


        logger.info("****** Finished Purchase Copy Link From Observation Column  Test ******");
    }
}


