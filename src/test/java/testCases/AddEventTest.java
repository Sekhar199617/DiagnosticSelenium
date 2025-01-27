package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountDetailsPage;
import pageObjects.AddEventTagsPage;
import pageObjects.BasePage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class AddEventTest extends BaseClass {

	@Test(groups = { "Smoke" })
	public void verify_add_event() {
		try {
			logger.info("****** Starting Add Event Test Case ******");

			login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

			DashboardPage dp = new DashboardPage(driver);
			CommonUtils commonUtils = new CommonUtils(driver);

			commonUtils.enterValueInTextField(dp.searchField, p.getProperty("accountName"));
			commonUtils.clickOnElement(dp.searchButton, "Search");
			commonUtils.clickOnElement(dp.actionsDropDown, null);
			commonUtils.clickOnElement(dp.view, "View");

			AccountDetailsPage ad = new AccountDetailsPage(driver);
			commonUtils.selectTab(ad.tabList, "Events/Tags");

			AddEventTagsPage addEventTagsPage = new AddEventTagsPage(driver);
			// Fill Event Details
			commonUtils.clickOnElement(addEventTagsPage.addEventButton, null);
			commonUtils.enterValueInTextField(addEventTagsPage.eventNameTagField, randomString());
			commonUtils.enterValueInTextField(addEventTagsPage.eventStartDateCalender, p.getProperty("eventStartDate"));
			commonUtils.enterValueInTextField(addEventTagsPage.eventEndDateCalender, p.getProperty("eventEndDate"));
			commonUtils.enterValueInTextField(addEventTagsPage.eventStartTime, p.getProperty("eventStartTiming"));
			commonUtils.enterValueInTextField(addEventTagsPage.eventEndTime, p.getProperty("eventEndTiming"));
			commonUtils.enterValueInTextField(addEventTagsPage.testingWindowStartDateCalender,
					p.getProperty("testingWindowStartDate"));
			commonUtils.enterValueInTextField(addEventTagsPage.testingWindowEndDateCalender,
					p.getProperty("testingWindowEndDateCalender"));
			commonUtils.enterValueInTextField(addEventTagsPage.testingWindowStartTime,
					p.getProperty("testingWindowStartTiming"));
			commonUtils.enterValueInTextField(addEventTagsPage.testingWindowEndTime,
					p.getProperty("testingWindowEndTiming"));

			// Save Event
			commonUtils.scrollToBottomAndClick(addEventTagsPage.saveButton);

			logger.info("****** Finished Add Event Test Case ******");
		} catch (Exception e) {
			logger.error("Error occurred: " + e.getMessage());
			Assert.fail("Test failed due to exception.");
		}
	}

}