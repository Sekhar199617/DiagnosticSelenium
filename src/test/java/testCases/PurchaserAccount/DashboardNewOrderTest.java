package testCases.PurchaserAccount;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import pageObjects.PurchaserAccount.AccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DashboardNewOrderTest extends BaseClass {

    public CommonUtils commonUtils;
    public AccountPage pl;

    @Test(groups = {"Smoke"})
    public void VerifyPurchaserNewOrderTestArrivedAndTestShippedMailTest() {

        logger.info("****** Starting Purchaser New Order Test Arrived And Test Shipped Mail Test ******");
        try {

            DashboardPage dp = new DashboardPage(driver);
            commonUtils = new CommonUtils(driver);
            AccountDetailsPage ad = new AccountDetailsPage(driver);
            pl = new AccountPage(driver);

            loadTestData(
                    "./testData/AdminAccountData/adminLoginData.json",
                    "./testData/AdminAccountData/accountDetailsData.json",
                    "./testData/AdminAccountData/dashboardData.json",
                    "./testData/PurchaserAccountData/purchaser.json"
            );

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            dp.searchForItem(getTestData("accountName"));
            dp.clickView();

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.userTypeDropdown),
                    getTestData("userType"));
            pl.performTableAction("accountsTableUserRoles", getTestData("userAccountAdminName"),
                    "Assign Tests",1);
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.diagnosticLogo),null);

            //Create New Order
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.newOrderButton), null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.newPatientLink), null);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.firstNameNewOrderField),
                    getTestData("purchaserRecipientFirstName"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.lastNameNewOrderField),
                    getTestData("purchaserRecipientLastName"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.emailNewOrderField),
                    getTestData("purchaserRecipientEmail"));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].dispatchEvent(new Event('click', { bubbles: true }));",
                    commonUtils.findElementByXpath(pl.countryDropdown));
            Thread.sleep(1000);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            String countryXPath = "//li[@role='option' and contains(., '"
                    + getTestData("purchaseCountryCodeNewOrder") + "')]";
            WebElement countryOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(countryXPath)));
            js.executeScript("arguments[0].scrollIntoView(true);", countryOption);
            Thread.sleep(500); // Ensure smooth scrolling
            js.executeScript("arguments[0].click();", countryOption);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.mobileNumberNewOrderField),
                    randomNumbers(10));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.demoUserCheckboxNewOrder), null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.continueNewOrderButton), null);
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.countryNewOrderDropdown),
                    getTestData("purchaseCountryNameNewOrder"));
            WebElement regionElement = commonUtils.findElementByXpath(pl.regionInputNewOrderField);
            WebElement regionDropdownElement = commonUtils.findElementByXpath(pl.regionNewOrderDropdown);
            String countryNameNewOrder = getTestData("purchaseCountryNameNewOrder");

            //Select city and region
           // handleCityAndRegion(countryNameNewOrder, regionElement, regionDropdownElement, getTestData("purchaseRegionNameNewOrder"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.shippingAddressNewOrder),
                    getTestData("purchaserShippingAddressNewOrder"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.shippingAddress2NewOrder),
                    getTestData("purchaserShippingAddress2NewOrder"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.cityNewOrderField),
                    getTestData("purchaserCityNewOrderField"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.postalCodeNewOrderField),
                    randomNumbers(6));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.continueNewOrderButton), null);
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.itemTypeNewOrderDropdown),
                    getTestData("purchaserItemTypeNewOrderDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.addBundleNewOrderDropdown),
                    getTestData("purchaserAddBundleNewOrderDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.flavorNewOrderDropdown),
                    getTestData("purchaserFlavorNewOrderDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.addQuantityDropdown),
                    getTestData("purchaserAddQuantityDropdown"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.addToOrderButton), null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.continueNewOrderButton), null);
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.selectShipmentScheduleDropdown),
                    getTestData("purchaserSelectShipmentScheduleDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.eventNameNewOrderDropdown),
                    getTestData("purchaserEventNameNewOrderDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.shippingSpeedNewOrderDropdown),
                    getTestData("purchaserShippingSpeedNewOrderDropdown"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.orderingPhysicianNameField),
                    getTestData("purchaserOrderingPhysicianName"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.npiOrIdField),
                    getTestData("purchaserNpiOrIdField"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.physicianEmailField),
                    getTestData("purchaserPhysicianEmail"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pl.physicianPhoneField),
                    randomNumbers(10));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.continueNewOrderButton), null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.confirmOrderButton), null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(pl.successfulConfirmationMessage),
                    getTestData("purchaserNewOrderSuccessfulConfirmationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.successfulConfirmationOkButton), null);

        } catch (Exception e) {
            Assert.fail();
        }

        logger.info("****** Finished Purchaser New Order Test Arrived And Test Shipped Mail  Test ******");
    }
}