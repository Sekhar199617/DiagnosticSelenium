package testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsPage;
import pageObjects.DashboardPage;
import pageObjects.PurchaseLevelAccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class PurchaserCopyFormUrlTest extends BaseClass {

    public CommonUtils commonUtils;
    public PurchaseLevelAccountPage ob;

    @Test(groups = {"Smoke"})
    public void VerifyPurchaserCopyFormUrl(){

        logger.info("***** Starting Purchaser Copy Form Url And Past it in Different Tab *****");
        try {
            login(p.getProperty("adminEmail"),p.getProperty("adminPassword"),true);

            DashboardPage dp = new DashboardPage(driver);

            commonUtils = new CommonUtils(driver);
            dp.searchForItem(p.getProperty("accountName"));
            dp.clickView();

            AccountDetailsPage ad = new AccountDetailsPage(driver);
            ob = new PurchaseLevelAccountPage(driver);

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");

            //Select account admin in user type dropdown
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.userTypeDropdown), p.getProperty("usersUserTypeAccountAdmin"));

            //Clicking on Assign Test in action dropdown for a account
            ob.performActionOnUser("accountsTableUserRoles", p.getProperty("userAccountAdminName"), "Assign Tests");

            //Switch the tab
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            ob.selectHamburgerTab("Forms");


            ob.clickOnFormsLink(p.getProperty("purchaserFormCopyUrlFormType"));
            String copiedURL = ob.getClipboardText();
            commonUtils.validateGetText(commonUtils.findElementByXpath(ob.successfulConfirmationMessage),p.getProperty("copyFormUrlConfirmationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.successfulConfirmationOkButton),null);

            ((JavascriptExecutor) driver).executeScript("window.open()");

            // Switch to the new tab
            for (String tab : driver.getWindowHandles()) {
                driver.switchTo().window(tab);
            }

            // Open site in the new tab
            driver.get(copiedURL);

            Thread.sleep(2000);


        } catch (Exception e) {
            Assert.fail();
        }


        logger.info("****** Finished Purchaser Dismiss Shipping Task Test ******");
    }

    }
