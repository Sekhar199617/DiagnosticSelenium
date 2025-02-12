package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsAddFormsPage;
import pageObjects.AccountDetailsPage;
import pageObjects.DashboardPage;
import pageObjects.PurchaseLevelAccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PurchaserPrecreatedCollectionInFormDataTest extends BaseClass {

    public CommonUtils commonUtils;

    @BeforeMethod(groups= {"Smoke"})
    public void verifyPurchaserPrecreatedCollectionInFormDataTest() {

        login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            commonUtils = new CommonUtils(driver);

            dp.searchForItem(p.getProperty("accountName"));
            dp.clickView();

            AccountDetailsPage ad = new AccountDetailsPage(driver);
            PurchaseLevelAccountPage pl = new PurchaseLevelAccountPage(driver);

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");

            //Select account admin in user type dropdown
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.userTypeDropdown),p.getProperty("usersUserTypeAccountAdmin"));

            //Clicking on Assign Test in action dropdown for a account
            pl.performActionOnUser("accountsTableUserRoles", p.getProperty("userAccountAdminName"), "Assign Tests");

            //Switch the tab
           List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));


            //Click on hamburger menu
            pl.selectPurchaseLevelHamburgerTab("Forms");

            AccountDetailsAddFormsPage addFormsPage = new AccountDetailsAddFormsPage(driver);

            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.addButton),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.createNewButton),null);

            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formEntityTypeDropdown), p.getProperty("formEntityTypeAccount"));
    }

    @Test(groups = {"Smoke"}, priority = 1)
    public void verifyAccountDataPrecreatedCollectionTest() {
        try {
            logger.info("****** Starting Account Data Precreated Collection Test ******");

            AccountDetailsAddFormsPage addFormsPage = new AccountDetailsAddFormsPage(driver);

            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formEntityTypeDropdown), p.getProperty("formEntityTypeAccount"));
            PurchaseLevelAccountPage pl = new PurchaseLevelAccountPage(driver);
            //Switch the tab
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));


            //Click on hamburger menu
            pl.selectPurchaseLevelHamburgerTab("Forms");



            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.addButton),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.createNewButton),null);

            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formEntityTypeDropdown), p.getProperty("formEntityTypeAccount"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formEntityTypeDropdown), p.getProperty("purchaserFormEntityTypeAccount"));
            WebElement formType2Content = driver.findElement(By.cssSelector("row d-flex align-items-center mb-3 formScope"));
            if (formType2Content.isDisplayed()) {
                System.out.println("Form Scope Dropdown is displayed correctly.");
            } else {
                System.out.println("Form Scope Dropdown is NOT displayed.");
            }

        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("****** Finished Account Data Precreated Collection Test ******");
    }

    @Test(groups = {"Smoke"}, priority = 2)
    public void verifyPatientDataPrecreatedCollectionTest() {
        try {
            logger.info("****** Starting Patient Data Precreated Collection Test ******");
            AccountDetailsAddFormsPage addFormsPage = new AccountDetailsAddFormsPage(driver);
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formEntityTypeDropdown), p.getProperty("formEntityTypeAccount"));

            PurchaseLevelAccountPage pl = new PurchaseLevelAccountPage(driver);
            //Switch the tab
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(2));


            //Click on hamburger menu
            pl.selectPurchaseLevelHamburgerTab("Forms");

            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.addButton),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.createNewButton),null);

            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formEntityTypeDropdown), p.getProperty("formEntityTypeAccount"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formEntityTypeDropdown), p.getProperty("formEntityTypeAccount"));

        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("****** Finished Patient Data Precreated Collection Tese ******");
    }

    @AfterMethod
    public void deleteCookies() {
        // Delete cookies after each test
        if (driver != null) {
            driver.manage().deleteAllCookies();
        }
    }
}
