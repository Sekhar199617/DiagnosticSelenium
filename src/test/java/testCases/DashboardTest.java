package testCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.io.IOException;

public class DashboardTest extends BaseClass {

    private DashboardPage dp;
    private AccountDetailsPage ad;
    private CommonUtils commonUtils;

    @BeforeMethod(groups = {"Smoke"})
    public void setupInitiated() throws IOException {

        login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

        dp = new DashboardPage(driver);
        ad = new AccountDetailsPage(driver);
        commonUtils = new CommonUtils(driver);

    }

    @Test(groups = {"Smoke"})
    public void verifySearchWithInputFunctionality() {

        dp.searchForItem(p.getProperty("accountName"));
        dp.validateAccountNameInTable(p.getProperty("accountName"));

        dp.clickView();

        ad.validateAccountDetailsTitle("Account Details for " + p.getProperty("accountName"));
        ad.validateDefaultSelectedTab();
    }

    @Test(groups = {"Smoke"})
    public void verifySearchWithoutInputFunctionality() {

        String activeAccounts = commonUtils.getTextFromElement(commonUtils.findElementByCssSelector(dp.activeAccounts));

        commonUtils.clickOnElement(commonUtils.findElementByXpath(dp.searchButton), "Search");
        commonUtils.scrollToBottomAndClick(commonUtils.findElementById(dp.totalEntriesText));

        String totalEntriesText = commonUtils.getTextFromElement(commonUtils.findElementById(dp.totalEntriesText));
        String[] totalEntriesList = totalEntriesText.split("of ");
        String totalEntries = totalEntriesList[1].split(" ")[0];

        if (activeAccounts.equals(totalEntries)) {
            Assert.assertEquals(activeAccounts, totalEntries);
            System.out.println("Total active accounts count (" + activeAccounts + ") is matching with total entries " +
                    "count (" + totalEntries + ").");
        } else {
            System.out.println("Total active accounts count (" + activeAccounts + ") is not matching with total " +
                    "entries (" + totalEntries + ").");
            Assert.fail();
        }

    }

    @AfterMethod
    public void deleteCookies() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
        }
    }
}
