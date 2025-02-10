package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsPage;
import pageObjects.DashboardPage;
import pageObjects.PurchaseLevelAccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class PurchaseCopyLinkFromObservationColumn extends BaseClass {



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


            ob.selectHamburgerTab("Assignments");
           // commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.assignmentViewButton),null);
            ob.clickOnAssignmentView("assignmentsTable",p.getProperty("purchaserAssignmentNameToClickView"));
            Thread.sleep(2000);
            ob.clickOnObservationLink("dataTables_scroll",p.getProperty("purchaserAssignmentNameToClickView"));
            commonUtils.validateGetText(commonUtils.findElementByXpath(ob.observationLinkCopyValidationMessage),p.getProperty("observationLinkCopyValidationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.observationLinkCopyOkButton),null);


        } catch (Exception e) {
            Assert.fail();
        }


        logger.info("****** Finished Purchase Copy Link From Observation Column  Test ******");
    }
}


