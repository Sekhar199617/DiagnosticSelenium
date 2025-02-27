package testCases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountDetailsPage;
import pageObjects.AccountDetailsAddLocationPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class AccountDetailsAddLocationTest extends BaseClass {

    public  String jsonPath;
    public CommonUtils commonUtils;
    public DashboardPage dp;
    public AccountDetailsPage ad;
    public AccountDetailsAddLocationPage lp;

    @Test
    public void verifyAddLocationTest() {

        logger.info("****** Starting Add Location Test ******");
        try {
            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            dp = new DashboardPage(driver);
            commonUtils = new CommonUtils(driver);
            ad = new AccountDetailsPage(driver);
            lp= new AccountDetailsAddLocationPage(driver);
            jsonPath = "./testData//accountDetailsData.json";
            loadTestData(jsonPath);

            dp.searchForItem(getTestData("accountName"));
            dp.clickView();
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Locations");

            commonUtils.clickOnElement(commonUtils.findElementByXpath(lp.addLocationButton), null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(lp.validateAccountName), getTestData("accountName"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.locationNameField), randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(lp.locationTypDropdown), getTestData("locationType"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.addressField), getTestData("newLocationAddress"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.address2Field), getTestData("newLocationAddress2"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(lp.countryDropdown), getTestData("newLocationCountry"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.postalCodeField), getTestData("newLocationPostcode"));
            WebElement cityElement = commonUtils.findElementByXpath(lp.cityInputField);
            WebElement stateElement = commonUtils.findElementByXpath(lp.stateInputField);
            WebElement cityDropdownElement = commonUtils.findElementByXpath(lp.cityDropdown);
            WebElement stateDropdownElement = commonUtils.findElementByXpath(lp.stateDropdown);
            commonUtils.handleCityAndState(getTestData("newLocationCountry"), cityElement, stateElement, cityDropdownElement, stateDropdownElement, getTestData("newLocationCity"), getTestData("newLocationState"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(lp.mondayStartTimeDropdown),getTestData("mondayStartTime") );
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(lp.mondayEndTimeDropdown),getTestData("mondayEndTime"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(lp.toMFButton),null);
            commonUtils.scrollToUp();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(lp.samplePickupTab),null);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.pickupPersonNameField),randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.pickPersonPhoneField),randomNumbers(10));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.desiredInitialPickupDate),getTestData("desiredInitialPickupDate"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.getDesiredInitialPickupTime),getTestData("getDesiredInitialPickupTime"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.pickupLocationField),randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(lp.extraHoursPickup),getTestData("extraHoursPickup"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(lp.recurrenceScheduleMondayStartTime),getTestData("recurrenceScheduleMondayStartTime"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(lp.recurrenceScheduleMondayEndTime),getTestData("recurrenceScheduleMondayEndTime"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(lp.recurrenceScheduleMFButton),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(lp.submitLocation), null);
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(lp.successfulMessage), getTestData("newLocationSuccessfulMessage"),commonUtils.findElementByXpath(lp.okButton));

        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("****** Finished Add Location Test ******");
    }

}
