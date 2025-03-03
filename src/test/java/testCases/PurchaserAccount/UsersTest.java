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

            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            commonUtils = new CommonUtils(driver);

            dp.searchForItem(p.getProperty("accountName"));
            dp.clickView();

            AccountDetailsPage ad = new AccountDetailsPage(driver);
            AccountPage pl = new AccountPage(driver);

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");

            //Clicking on Assign Test in action dropdown for a account
            pl.performTableAction("accountsTableUserRoles", p.getProperty("userAccountAdminName"), "Assign Tests",1);

            //Switch the tab
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            //Click on logo
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.diagnosticLogo),null);

            //Click on hamburger menu
            dp.selectHamburgerTab("Users");

            //Clicking on view for user in purchase user
            pl.performTableAction("purchaseUsersTable", p.getProperty("purchaseAccountLevelUserName"), "View", 1);


            commonUtils.validateGetText(commonUtils.findElementByXpath(pl.accountLevelHeading),p.getProperty("purchaseAccountLevelHeadingMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.accountLevelCancelButton),null);

        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Purchaser View The User Of Account Level Test ******");
    }
}
