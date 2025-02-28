package testCases.AdminAccount.AccountDetailsModulesTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.AccountDetailsModules.EventTagsPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class EventsTagsTest extends BaseClass {

    @Test(groups = { "Smoke" })
    public void verify_add_event() {
        try {
            logger.info("****** Starting Add Event Test Case ******");

            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dashboardPage = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            AccountDetailsPage accountDetailsPage = new AccountDetailsPage(driver);
            EventTagsPage eventTagsPage = new EventTagsPage(driver);

            dashboardPage.searchForItem(p.getProperty("accountName"));
            dashboardPage.clickView();

            commonUtils.selectTab(commonUtils.findElementsByXpath(accountDetailsPage.tabList), "Events/Tags");

            // Fill Event Details
            commonUtils.clickOnElement(commonUtils.findElementByXpath(eventTagsPage.addEventButton),
                    null);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(eventTagsPage.eventNameTagField),
                    randomString() );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(eventTagsPage.eventStartDateCalender),
                    p.getProperty("eventStartDate"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(eventTagsPage.eventEndDateCalender),
                    p.getProperty("eventEndDate") );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(eventTagsPage.eventStartTime),
                    p.getProperty("eventStartTiming") );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(eventTagsPage.eventEndTime),
                    p.getProperty("eventEndTiming") );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(
                    eventTagsPage.testingWindowStartDateCalender), p.getProperty("testingWindowStartDate") );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(
                    eventTagsPage.testingWindowEndDateCalender), p.getProperty("testingWindowEndDate"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(eventTagsPage.testingWindowStartTime),
                    p.getProperty("testingWindowStartTiming") );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(eventTagsPage.testingWindowEndTime),
                    p.getProperty("testingWindowEndTiming") );
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(eventTagsPage.saveButton));

            logger.info("****** Finished Add Event Test Case ******");
        } catch (Exception e) {
            logger.error("Error occurred: " + e.getMessage());
            Assert.fail("Test failed due to exception.");
        }
    }
}