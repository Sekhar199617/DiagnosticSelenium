package testCases.PurchaserAccount;

import org.testng.annotations.Test;
import org.testng.Assert;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import pageObjects.PurchaserAccount.AccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class UsersTest extends BaseClass {

    public CommonUtils commonUtils;

    @Test(groups= {"Smoke"})
    public void verifyPurchaserViewTheUserOfAccountLevelTest() {

        logger.info("****** Starting Purchaser View The User Of Account Level Test ******");
        try{

            commonUtils = new CommonUtils(driver);
            AccountPage pl = new AccountPage(driver);
            DashboardPage dp = new DashboardPage(driver);
            AccountDetailsPage ad = new AccountDetailsPage(driver);

            loadTestData(
                    "./testData/AdminAccountData/adminLoginData.json",
                    "./testData/AdminAccountData/dashboardData.json",
                    "./testData/AdminAccountData/accountDetailsData.json"
            );

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            dp.searchForItem(getTestData("accountName"));
            dp.clickView();

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");

            //Clicking on Assign Test in action dropdown for a account
            pl.performTableAction("accountsTableUserRoles", getTestData("userAccountAdminName"),
                    "Assign Tests",1);

            //Switch the tab
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            //Click on logo
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.diagnosticLogo),null);

            //Click on hamburger menu
            dp.selectHamburgerTab("Users");

            //Clicking on view for user in purchase user
            pl.performTableAction("purchaseUsersTable", getTestData("purchaseAccountLevelUserName"),
                    "View", 1);

            commonUtils.validateGetText(commonUtils.findElementByXpath(pl.accountLevelHeading),
                    getTestData("purchaseAccountLevelHeadingMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.accountLevelCancelButton),null);

        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Purchaser View The User Of Account Level Test ******");
    }
}
