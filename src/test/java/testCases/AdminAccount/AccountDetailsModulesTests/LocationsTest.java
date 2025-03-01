package testCases.AdminAccount.AccountDetailsModulesTests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.AccountDetailsModules.LocationsPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class LocationsTest extends BaseClass {

    @Test
    public void verifyAddLocationTest() {

        logger.info("****** Starting Add Location Test ******");
        try {
            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);

            dp.searchForItem(p.getProperty("accountName"));
            dp.clickView();

            AccountDetailsPage ad = new AccountDetailsPage(driver);
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Locations");

            LocationsPage lp= new LocationsPage(driver);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(lp.addLocationButton), null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(lp.validateAccountName), p.getProperty("accountName"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.locationNameField), randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(lp.locationTypDropdown), p.getProperty("locationType"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.addressField), p.getProperty("address"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.address2Field), p.getProperty("address2"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(lp.countryDropdown), p.getProperty("locationCountry"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.postalCodeField), p.getProperty("locationPostcode"));
            WebElement cityElement = commonUtils.findElementByXpath(lp.cityInputField);
            WebElement stateElement = commonUtils.findElementByXpath(lp.stateInputField);
            WebElement cityDropdownElement = commonUtils.findElementByXpath(lp.cityDropdown);
            WebElement stateDropdownElement = commonUtils.findElementByXpath(lp.stateDropdown);
            commonUtils.handleCityAndState(p.getProperty("locationCountry"), cityElement, stateElement, cityDropdownElement, stateDropdownElement, p.getProperty("locationCity"), p.getProperty("locationState"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(lp.mondayStartTimeDropdown),p.getProperty("mondayStartTime") );
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(lp.mondayEndTimeDropdown),p.getProperty("mondayEndTime"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(lp.toMFButton),null);
            commonUtils.scrollToUp();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(lp.samplePickupTab),null);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.pickupPersonNameField),randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.pickPersonPhoneField),randomNumbers(10));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.desiredInitialPickupDate),p.getProperty("desiredInitialPickupDate"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.getDesiredInitialPickupTime),p.getProperty("getDesiredInitialPickupTime"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.pickupLocationField),randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.extraHoursPickup),p.getProperty("extraHoursPickup"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(lp.recurrenceScheduleMondayStartTime),p.getProperty("recurrenceScheduleMondayStartTime"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(lp.recurrenceScheduleMondayEndTime),p.getProperty("recurrenceScheduleMondayEndTime"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(lp.recurrenceScheduleMFButton),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(lp.submitLocation), null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(lp.successfulMessage), p.getProperty("successfulMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(lp.okButton), null);

        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("****** Finished Add Location Test ******");
    }

}
