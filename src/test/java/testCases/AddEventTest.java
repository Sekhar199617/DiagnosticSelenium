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

	AddEventTagsPage addEventTagsPage;
	AccountDetailsPage accountDetailsPage;
	CommonUtils commonUtils;

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
			commonUtils.enterValueInTextField(addEventTagsPage.eventEndtTime, p.getProperty("eventEndtTiming"));
			commonUtils.enterValueInTextField(addEventTagsPage.testing_windowStartDateCalender,
					p.getProperty("testing_windowStartDate"));
			commonUtils.enterValueInTextField(addEventTagsPage.testing_windowEndDateCalender,
					p.getProperty("testing_windowEndDateCalender"));
			commonUtils.enterValueInTextField(addEventTagsPage.testing_windowStartTime,
					p.getProperty("testing_windowStartTiming"));
			commonUtils.enterValueInTextField(addEventTagsPage.testing_windowEndtTime,
					p.getProperty("testing_windowEndtTiming"));

			// Save Event
			commonUtils.clickOnElement(addEventTagsPage.saveButton, null);

			logger.info("****** Finished Add Event Test Case ******");
		} catch (Exception e) {
			logger.error("Error occurred: " + e.getMessage());
			Assert.fail("Test failed due to exception.");
		}
	}

}