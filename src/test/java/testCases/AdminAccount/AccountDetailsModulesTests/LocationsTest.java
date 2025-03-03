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

            LocationsPage lp= new LocationsPage(driver);
            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            AccountDetailsPage ad = new AccountDetailsPage(driver);

            loadTestData(
                    "./testData/AdminAccountData/adminLoginData.json",
                    "./testData/AdminAccountData/dashboardData.json",
                    "./testData/AdminAccountData/accountDetailsData.json"
            );

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            dp.searchForItem(getTestData("accountName"));
            dp.clickView();

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Locations");
            commonUtils.clickOnElement(commonUtils.findElementByXpath(lp.addLocationButton), null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(lp.validateAccountName),
                    getTestData("accountName"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.locationNameField), randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(lp.locationTypDropdown),
                    getTestData("locationType"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.addressField),
                    getTestData("address"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.address2Field),
                    getTestData("address2"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(lp.countryDropdown),
                    getTestData("locationCountry"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.postalCodeField),
                    getTestData("locationPostcode"));
            WebElement cityElement = commonUtils.findElementByXpath(lp.cityInputField);
            WebElement stateElement = commonUtils.findElementByXpath(lp.stateInputField);
            WebElement cityDropdownElement = commonUtils.findElementByXpath(lp.cityDropdown);
            WebElement stateDropdownElement = commonUtils.findElementByXpath(lp.stateDropdown);
            commonUtils.handleCityAndState(getTestData("locationCountry"), cityElement, stateElement,
                    cityDropdownElement, stateDropdownElement,
                    getTestData("locationCity"),
                    getTestData("locationState"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(lp.mondayStartTimeDropdown),
                    getTestData("mondayStartTime") );
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(lp.mondayEndTimeDropdown),
                    getTestData("mondayEndTime"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(lp.toMFButton),null);
            commonUtils.scrollToUp();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(lp.samplePickupTab),null);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.pickupPersonNameField),randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.pickPersonPhoneField),
                    randomNumbers(10));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.desiredInitialPickupDate),
                    getTestData("desiredInitialPickupDate"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.getDesiredInitialPickupTime),
                    getTestData("getDesiredInitialPickupTime"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.pickupLocationField),randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.extraHoursPickup),
                    getTestData("extraHoursPickup"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(lp.recurrenceScheduleMondayStartTime),
                    getTestData("recurrenceScheduleMondayStartTime"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(lp.recurrenceScheduleMondayEndTime),
                    getTestData("recurrenceScheduleMondayEndTime"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(lp.recurrenceScheduleMFButton),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(lp.submitLocation), null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(lp.successfulMessage),
                    getTestData("successfulMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(lp.okButton), null);

        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("****** Finished Add Location Test ******");
    }
}