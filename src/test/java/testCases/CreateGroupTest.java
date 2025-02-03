package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsPage;
import pageObjects.AddGroupPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class CreateGroupTest extends BaseClass {

    @Test(groups = { "Smoke" })
    public void verify_create_group() {
        try {
            logger.info("****** Starting Create Group Test Case ******");

            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);

            commonUtils.enterValueInTextField(dp.searchField, p.getProperty("accountName"));
            commonUtils.clickOnElement(dp.searchButton, "Search");
            commonUtils.clickOnElement(dp.actionsDropDown, null);
            commonUtils.clickOnElement(dp.view, "View");

            AccountDetailsPage ad = new AccountDetailsPage(driver);
            commonUtils.selectTab(ad.tabList, "Groups");

            AddGroupPage gp = new AddGroupPage(driver);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(gp.addGroupButton), null);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(gp.groupNameField),randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(gp.orderingModeDropdown),p.getProperty("orderingModeDropdown"));


            logger.info("****** Finished Create Group Test Case ******");
        } catch (Exception e) {
            logger.error("Error occurred: " + e.getMessage());
            Assert.fail("Test failed due to exception.");
        }
    }
}
