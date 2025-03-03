package testCases.PurchaserAccount;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.AccountDetailsModules.OrdersPage;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.AccountDetailsModules.UsersAndRolesPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class SettingsPurchaserProvisionedOrderTest extends BaseClass {

    @Test
    public void verifyProvisionedOrderCreateEditPresence() {

        logger.info("****** Starting Provisioned Order Create Edit Presence Test ******");
        try {

            commonUtils = new CommonUtils(driver);
            OrdersPage po = new OrdersPage(driver);
            DashboardPage dp = new DashboardPage(driver);
            UsersAndRolesPage au = new UsersAndRolesPage(driver);
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
            commonUtils.selectDropDownValue(commonUtils.findElementById(au.userTypeDropDownField), "Account Admins");
            au.performActionOnUser("accountsTableUserRoles", getTestData("userAccountAdminName"),
                    "Assign Tests");

            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            dp.selectHamburgerTab("Settings");
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Orders");

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
