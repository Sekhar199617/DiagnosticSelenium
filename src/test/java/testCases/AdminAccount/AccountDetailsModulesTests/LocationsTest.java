package testCases.AdminAccount.AccountDetailsModulesTests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.AccountDetailsModules.LocationsPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class LocationsTest extends BaseClass {

    @Test
    public void verifyAddLocationTest() {

        logger.info("****** Starting Add Location Test ******");
        try {
            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            CommonUtils commonUtils = new CommonUtils(driver);
            AccountDetailsPage ad = new AccountDetailsPage(driver);
            LocationsPage locationsPage = new LocationsPage(driver);
            DashboardPage dashboardPage = new DashboardPage(driver);

            dashboardPage.searchForItem(p.getProperty("accountName"));
            dashboardPage.clickView();

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Locations");

            commonUtils.clickOnElement(commonUtils.findElementByXpath(locationsPage.addLocationButton), null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(locationsPage.validateAccountName),
                    p.getProperty("accountName"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(locationsPage.locationNameField),
                    randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(locationsPage.locationTypDropdown),
                    p.getProperty("locationType"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(locationsPage.addressField),
                    p.getProperty("address"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(locationsPage.address2Field),
                    p.getProperty("address2"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(locationsPage.countryDropdown),
                    p.getProperty("locationCountry"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(locationsPage.postalCodeField),
                    p.getProperty("locationPostcode"));
            WebElement cityElement = commonUtils.findElementByXpath(locationsPage.cityInputField);
            WebElement stateElement = commonUtils.findElementByXpath(locationsPage.stateInputField);
            WebElement cityDropdownElement = commonUtils.findElementByXpath(locationsPage.cityDropdown);
            WebElement stateDropdownElement = commonUtils.findElementByXpath(locationsPage.stateDropdown);
            commonUtils.handleCityAndState(p.getProperty("locationCountry"), cityElement, stateElement,
                    cityDropdownElement, stateDropdownElement, p.getProperty("locationCity"),
                    p.getProperty("locationState"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(locationsPage.mondayStartTimeDropdown),
                    p.getProperty("mondayStartTime") );
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(locationsPage.mondayEndTimeDropdown),
                    p.getProperty("mondayEndTime"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(locationsPage.toMFButton),null);
            commonUtils.scrollToUp();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(locationsPage.samplePickupTab),null);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(locationsPage.pickupPersonNameField),
                    randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(locationsPage.pickPersonPhoneField),
                    randomNumbers(10));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(locationsPage.desiredInitialPickupDate),
                    p.getProperty("desiredInitialPickupDate"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(locationsPage.getDesiredInitialPickupTime),
                    p.getProperty("getDesiredInitialPickupTime"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(locationsPage.pickupLocationField),
                    randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(locationsPage.extraHoursPickup),
                    p.getProperty("extraHoursPickup"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(
                    locationsPage.recurrenceScheduleMondayStartTime),p.getProperty("recurrenceScheduleMondayStartTime"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(
                    locationsPage.recurrenceScheduleMondayEndTime),p.getProperty("recurrenceScheduleMondayEndTime"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(locationsPage.recurrenceScheduleMFButton),
                    null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(locationsPage.submitLocation), null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(locationsPage.successfulMessage),
                    p.getProperty("successfulMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(locationsPage.okButton), null);

        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("****** Finished Add Location Test ******");
    }
}