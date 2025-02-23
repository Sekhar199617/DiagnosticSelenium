package testCases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsOrdersPage;
import pageObjects.AccountDetailsPage;
import pageObjects.AccountDetailsUsersAndRolesPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class PurchaserProvisionedOrderTest extends BaseClass {

    @Test
    public void verifyProvisionedOrderCreateEditPresence() {
        logger.info("****** Starting Provisioned Order Create Edit Presence Test ******");
        try {
            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            commonUtils = new CommonUtils(driver);

            dp.searchForItem(p.getProperty("accountName"));
            dp.clickView();

            AccountDetailsPage ad = new AccountDetailsPage(driver);
            AccountDetailsUsersAndRolesPage au = new AccountDetailsUsersAndRolesPage(driver);
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");
            commonUtils.selectDropDownValue(commonUtils.findElementById(au.userTypeDropDownField), "Account Admins");
            au.performActionOnUser("accountsTableUserRoles", p.getProperty("userAccountAdminName"),
                    "Assign Tests");

            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            dp.selectHamburgerTab("Settings");
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Orders");

            AccountDetailsOrdersPage po = new AccountDetailsOrdersPage(driver);
            commonUtils.validateRadioButton(commonUtils.findElementByXpath(po.provisionedOrdersRadioButton));

            WebElement addButton = commonUtils.findElementByXpath(po.addOrderButton);
            WebElement editButton = commonUtils.findElementByXpath(po.editButton);

            if (addButton != null) {
                Assert.fail("Add button should not be present on the page, but it was found.");
            } else {
                System.out.println("Test passed: Add button is not present, as expected.");
            }

            if (editButton != null) {
                Assert.fail("Edit button should not be present on the page, but it was found.");
            } else {
                System.out.println("Test passed: Edit button is not present, as expected.");
            }
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Provisioned Order Create Edit Presence Test ******");
    }
}
