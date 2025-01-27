package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountDetailsPage;
import pageObjects.AddLocationPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class AddLocationTest extends BaseClass {

	@Test(groups= {"Smoke"})
	public void verifyAddLocationTest() {

		logger.info("****** Starting Online Education Test ******");
		try {
			login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

			DashboardPage dp = new DashboardPage(driver);
			CommonUtils commonUtils = new CommonUtils(driver);

			commonUtils.enterValueInTextField(dp.searchField, p.getProperty("accountName"));
			commonUtils.clickOnElement(dp.searchButton, "Search");
			commonUtils.clickOnElement(dp.actionsDropDown, null);
			commonUtils.clickOnElement(dp.view, "View");

			AccountDetailsPage ad = new AccountDetailsPage(driver);
			commonUtils.selectTab(ad.tabList, "Locations");
			
			AddLocationPage lp= new AddLocationPage(driver);
			commonUtils.clickOnElement(lp.addLocationButton, null);
			commonUtils.enterValueInTextField(lp.locationNameField, randomString());
			commonUtils.selectDropDownValue(lp.locationTypDropdown, p.getProperty("locationType"));
			commonUtils.enterValueInTextField(lp.addressField, randomAlphaNumeric());
			commonUtils.enterValueInTextField(lp.address2Field, randomAlphaNumeric());

			String country = p.getProperty("locationCountry");
			String city, state ;

			if (country.equalsIgnoreCase("United States")) {
				// Provide valid dropdown values for the United States
				city = "New York";  // Replace with an actual dropdown value
				state = "New York"; // Replace with an actual dropdown value
			} else {
				// Use random strings for city and state for other countries
				city = randomString();
				state = randomString();
			}
			lp.selectCountry(country);
			commonUtils.enterValueInTextField(lp.postalCodeField, randomNumbers());

			lp.handleCityAndState(country,city,state);
			commonUtils.clickOnElement(lp.submitLocation, null);

		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("****** Finished Online Education Test ******");
	}

}
