package testCases.AccountDetailsModulesTest.EventsTags;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountDetailsModules.AdminAccount.AccountDetailsPage;
import pageObjects.AccountDetailsModules.AdminAccount.EventsTags.EventTagsPage;
import pageObjects.AccountDetailsModules.AdminAccount.Dashboard.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class EventsTest extends BaseClass {

    @Test(groups = { "Smoke" })
    public void verify_add_event() {
        try {
            logger.info("****** Starting Add Event Test Case ******");

            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);

            dp.searchForItem(p.getProperty("accountName"));
            dp.clickView();

            AccountDetailsPage ad = new AccountDetailsPage(driver);
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Events/Tags");

            EventTagsPage addEventTagsPage = new EventTagsPage(driver);
            // Fill Event Details
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addEventTagsPage.addEventButton), null);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.eventNameTagField), randomString() );
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

}