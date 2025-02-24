package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class PurchaserViewTheUserOfAccountLevelTest extends BaseClass {

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
            PurchaseLevelAccountPage pl = new PurchaseLevelAccountPage(driver);

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");

            //Select account admin in user type dropdown
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.userTypeDropdown),p.getProperty("usersUserTypeAccountAdmin"));

            //Clicking on Assign Test in action dropdown for a account
            pl.performTableAction("accountsTableUserRoles", p.getProperty("userAccountAdminName"), "Assign Tests",1);

            //Switch the tab
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            //Click on hamburger menu
            dp.selectHamburgerTab("Users");

            //Clicking on view for user in purchase user
            pl.performTableAction("purchaseUsersTable", p.getProperty("purchaseAccountLevelUserName"), "View", 1);


            commonUtils.validateGetText(commonUtils.findElementByXpath(pl.accountLevelHeading),p.getProperty("purchaseAccountLevelHeadingMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.accountLevelCancelButton),null);

        }catch(Exception e)
        {
            AssertJUnit.fail();
        }
        logger.info("****** Finished Purchaser View The User Of Account Level Test ******");
    }
}
