package testCases.AdminAccount.DashboardTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;
import java.io.IOException;

public class DashboardTest extends BaseClass {

    private DashboardPage dp;
    private AccountDetailsPage ad;
    private CommonUtils commonUtils;

    @BeforeMethod(groups = {"Smoke"})
    public void setupInitiated() throws IOException {

        String loginJsonPath = "./testData//adminLoginData.json";
        loadTestData(loginJsonPath);

        login(getTestData("adminEmail"), getTestData("adminPassword"), true);

        String dashboardDataJsonPath = "./testData//dashboardData.json";
        loadTestData(dashboardDataJsonPath);

        dp = new DashboardPage(driver);
        ad = new AccountDetailsPage(driver);
        commonUtils = new CommonUtils(driver);
    }

    @Test(groups = {"Smoke"})
    public void verifySearchWithInputFunctionality() {

        dp.searchForItem(getTestData("accountName"));
        dp.validateAccountNameInTable(getTestData("accountName"));

        dp.clickView();

        ad.validateAccountDetailsTitle("Account Details for " + getTestData("accountName"));
        ad.validateDefaultSelectedTab();
    }

    @Test(groups = {"Smoke"})
    public void verifySearchWithoutInputFunctionality() {

        String activeAccountsCount = commonUtils.getTextFromElement(commonUtils.findElementByCssSelector(dp.activeAccounts));

        commonUtils.clickOnElement(commonUtils.findElementByXpath(dp.searchButton), "Search");
        commonUtils.scrollToBottomAndClick(commonUtils.findElementById(dp.totalEntriesText));

        String totalEntriesText = commonUtils.getTextFromElement(commonUtils.findElementById(dp.totalEntriesText));
        String[] totalEntriesList = totalEntriesText.split("of ");
        String totalEntriesCount = totalEntriesList[1].split(" ")[0];

        if (Integer.parseInt(totalEntriesCount) >= Integer.parseInt(activeAccountsCount)) {
            System.out.println("Total entries count (" + totalEntriesCount + ") is greater than or equal to active " +
                    "accounts count (" + activeAccountsCount + ").");
        } else {
            System.out.println("Total entries (" + totalEntriesCount + ") is less than active " +
                    "accounts count (" + activeAccountsCount + ").");
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
