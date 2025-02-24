package testCases;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObjects.AccountDetailsPage;
import pageObjects.DashboardPage;
import pageObjects.PurchaseLevelAccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PurchaserNewOrderTestArrivedAndTestShippedMailTest extends BaseClass {

    public CommonUtils commonUtils;
    public PurchaseLevelAccountPage pl;

    @Test(groups = {"Smoke"})
    public void VerifyPurchaserNewOrderTestArrivedAndTestShippedMailTest() {

        logger.info("****** Starting Purchaser New Order Test Arrived And Test Shipped Mail Test ******");
        try {

            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);

            commonUtils = new CommonUtils(driver);
            dp.searchForItem(p.getProperty("accountName"));
            dp.clickView();

            AccountDetailsPage ad = new AccountDetailsPage(driver);
             pl = new PurchaseLevelAccountPage(driver);

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");

            //Select account admin in user type dropdown
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.userTypeDropdown), p.getProperty("usersUserTypeAccountAdmin"));

            //Clicking on Assign Test in action dropdown for a account
            pl.performTableAction("accountsTableUserRoles", p.getProperty("userAccountAdminName"), "Assign Tests",1);

            //Switch the tab
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            //Click on logo
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.diagnosticLogo),null);

            //Create New Order
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.newOrderButton), null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.newPatientLink), null);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.firstNameNewOrderField), p.getProperty("purchaserRecipientFirstName"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.lastNameNewOrderField), p.getProperty("purchaserRecipientLastName"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.emailNewOrderField), p.getProperty("purchaserRecipientEmail"));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].dispatchEvent(new Event('click', { bubbles: true }));", commonUtils.findElementByXpath(pl.countryDropdown));
            Thread.sleep(1000);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            String countryXPath = "//li[@role='option' and contains(., '" + p.getProperty("purchaseCountryCodeNewOrder") + "')]";
            WebElement countryOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(countryXPath)));
            js.executeScript("arguments[0].scrollIntoView(true);", countryOption);
            Thread.sleep(500); // Ensure smooth scrolling
            js.executeScript("arguments[0].click();", countryOption);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.mobileNumberNewOrderField), p.getProperty("purchaserMobileNumberNewOrder"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.demoUserCheckboxNewOrder), null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.continueNewOrderButton), null);
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.countryNewOrderDropdown), p.getProperty("purchaseCountryNameNewOrder"));
            WebElement regionElement = commonUtils.findElementByXpath(pl.regionInputNewOrderField);
            WebElement regionDropdownElement = commonUtils.findElementByXpath(pl.regionNewOrderDropdown);
            String countryNameNewOrder = p.getProperty("purchaseCountryNameNewOrder");
            //Select city and region
           // handleCityAndRegion(countryNameNewOrder, regionElement, regionDropdownElement, p.getProperty("purchaseRegionNameNewOrder"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.shippingAddressNewOrder), p.getProperty("purchaserShippingAddressNewOrder"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.shippingAddress2NewOrder), p.getProperty("purchaserShippingAddress2NewOrder"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.cityNewOrderField), p.getProperty("purchaserCityNewOrderField"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.postalCodeNewOrderField), p.getProperty("purchaserPostalCodeNewOrderField"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.continueNewOrderButton), null);
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.itemTypeNewOrderDropdown), p.getProperty("purchaserItemTypeNewOrderDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.addBundleNewOrderDropdown), p.getProperty("purchaserAddBundleNewOrderDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.flavorNewOrderDropdown), p.getProperty("purchaserFlavorNewOrderDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.addQuantityDropdown), p.getProperty("purchaserAddQuantityDropdown"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.addToOrderButton), null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.continueNewOrderButton), null);
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.selectShipmentScheduleDropdown), p.getProperty("purchaserSelectShipmentScheduleDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.eventNameNewOrderDropdown), p.getProperty("purchaserEventNameNewOrderDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.shippingSpeedNewOrderDropdown), p.getProperty("purchaserShippingSpeedNewOrderDropdown"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.orderingPhysicianNameField), p.getProperty("purchaserOrderingPhysicianName"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.npiOrIdField), p.getProperty("purchaserNpiOrIdField"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.physicianEmailField), p.getProperty("purchaserPhysicianEmail"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.physicianPhoneField), p.getProperty("purchaserPhysicianPhone"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.continueNewOrderButton), null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.confirmOrderButton), null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(pl.successfulConfirmationMessage), p.getProperty("purchaserNewOrderSuccessfulConfirmationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.successfulConfirmationOkButton), null);

        } catch (Exception e) {
            Assert.fail();
        }

        logger.info("****** Finished Purchaser New Order Test Arrived And Test Shipped Mail  Test ******");
    }

}
