    package testCases;

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

    public class PurchaserPrecreatedCollectionInFormDataTest extends BaseClass {

        public CommonUtils commonUtils;
        public PurchaseLevelAccountPage pl;
        public AccountDetailsAddFormsPage addFormsPage;
        public DashboardPage dp;



        @BeforeMethod(groups= {"Smoke"})
        public void verifyPurchaserPrecreatedCollectionInFormDataTest() throws InterruptedException {

            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

                dp = new DashboardPage(driver);
                commonUtils = new CommonUtils(driver);

                dp.searchForItem(p.getProperty("accountName"));
                dp.clickView();

                AccountDetailsPage ad = new AccountDetailsPage(driver);
                pl = new PurchaseLevelAccountPage(driver);

                commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");

                //Select account admin in user type dropdown
                commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.userTypeDropdown),p.getProperty("usersUserTypeAccountAdmin"));

                //Clicking on Assign Test in action dropdown for a account
                pl.performTableAction("accountsTableUserRoles", p.getProperty("userAccountAdminName"), "Assign Tests",1);

        }

        @Test(groups = {"Smoke"}, priority = 1)
        public void verifyAccountDataPrecreatedCollectionTest() {
            try {
                logger.info("****** Starting Account Data Precreated Collection Test ******");

               addFormsPage = new AccountDetailsAddFormsPage(driver);
                 pl = new PurchaseLevelAccountPage(driver);
                //Switch the tab
                List<String> tabs = new ArrayList<>(driver.getWindowHandles());
                driver.switchTo().window(tabs.get(1));

                //Click on logo
                commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.diagnosticLogo),null);

                //Click on hamburger menu
                dp.selectHamburgerTab("Forms");

                commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.addButton),null);
                commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.createNewButton),null);
                commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.formNameField), randomString());

                commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formEntityTypeDropdown), p.getProperty("purchaserFormEntityTypeAccount"));

                if (pl.isElementDisplayed(commonUtils.findElementByXpath(pl.formScopeDropdown))) {
                    System.out.println("Form Scope Dropdown is displayed correctly.");
                } else {
                    System.out.println("Form Scope Dropdown is NOT displayed.");
                }

                if (pl.isElementDisplayed(commonUtils.findElementByXpath(pl.bundlesList))) {
                    System.out.println("Bundle List is displayed correctly.");
                } else {
                    System.out.println("Bundle List Dropdown is NOT displayed.");
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
               addFormsPage = new AccountDetailsAddFormsPage(driver);
               pl = new PurchaseLevelAccountPage(driver);

               //Switch the tab
                List<String> tabs = new ArrayList<>(driver.getWindowHandles());
                driver.switchTo().window(tabs.get(1));

                //Click on hamburger menu
                dp.selectHamburgerTab("Forms");

                commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.addButton),null);
                commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.createNewButton),null);

                commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.formNameField), randomString());
                commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formEntityTypeDropdown), p.getProperty("purchaserFormEntityTypePatient"));

                if (pl.isElementDisplayed(commonUtils.findElementByXpath(pl.formScopeDropdown))) {
                    System.out.println("Form Scope Dropdown is displayed correctly.");
                } else {
                    System.out.println("Form Scope Dropdown is NOT displayed.");
                }

                if (pl.isElementDisplayed(commonUtils.findElementByXpath(pl.bundlesList))) {
                    System.out.println("Bundle List is displayed correctly.");
                } else {
                    System.out.println("Bundle List Dropdown is NOT displayed.");
                }

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
            commonUtils.closeExtraWindows();
        }
    }
