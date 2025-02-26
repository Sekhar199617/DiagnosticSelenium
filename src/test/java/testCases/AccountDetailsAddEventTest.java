package testCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.AccountDetailsPage;
import pageObjects.AccountDetailsAddEventTagsPage;
import pageObjects.DashboardPage;
import pageObjects.PurchaseLevelAccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class AccountDetailsAddEventTest extends BaseClass {

    public DashboardPage dp;
    public CommonUtils commonUtils;
    public AccountDetailsPage ad;
    public AccountDetailsAddEventTagsPage addEventTagsPage;
    public String formName;
    public PurchaseLevelAccountPage pl;

    @BeforeMethod (groups = {"Smoke"})
    public void event_common_step(){
        logger.info("****** Starting Add Event Test Case ******");

        login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

        dp = new DashboardPage(driver);
        commonUtils = new CommonUtils(driver);
        ad = new AccountDetailsPage(driver);
        addEventTagsPage = new AccountDetailsAddEventTagsPage(driver);
        pl = new PurchaseLevelAccountPage(driver);

        dp.searchForItem(p.getProperty("accountName"));
        dp.clickView();
        commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Events/Tags");

    }

    @Test(groups = { "Smoke" }, priority = 1)
    public void verify_add_event() {
        try {
            formName = randomString();
            // Fill Event Details
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addEventTagsPage.addEventButton), null);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.eventNameTagField), formName );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.eventStartDateCalender), p.getProperty("eventStartDate"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.eventEndDateCalender), p.getProperty("eventEndDate") );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.eventStartTime), p.getProperty("eventStartTiming") );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.eventEndTime), p.getProperty("eventEndTiming") );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.testingWindowStartDateCalender), p.getProperty("testingWindowStartDate") );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.testingWindowEndDateCalender), p.getProperty("testingWindowEndDate"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.testingWindowStartTime), p.getProperty("testingWindowStartTiming") );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.testingWindowEndTime), p.getProperty("testingWindowEndTiming") );
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(addEventTagsPage.saveButton));

            logger.info("****** Finished Add Event Test Case ******");
        } catch (Exception e) {
            logger.error("Error occurred: " + e.getMessage());
            Assert.fail("Test failed due to exception.");
        }
    }

    @Test (groups = {"Smoke"}, priority = 2)
    public void verify_Edit_Event() throws InterruptedException {

        pl.performTableAction("accountsTable", formName, "Edit",1);
        Thread.sleep(2000);

    }

    @AfterMethod
    public void deleteCookies(){
        driver.manage().deleteAllCookies();
    }

}