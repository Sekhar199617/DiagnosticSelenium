package testCases.PurchaserAccount;

import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import pageObjects.AdminAccount.AccountDetailsModules.EventsTagsPage;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import pageObjects.PurchaserAccount.AccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;
import java.util.ArrayList;
import java.util.List;

public class SettingsEventsTagsTest extends BaseClass {

    public CommonUtils commonUtils;
    public AccountPage ob;

    @Test(groups = {"Smoke"})
    public void VerifyPurchaserCreateAccountAdminManagerInDropdownListTest(){

        logger.info("***** Starting Purchaser Create Account Admin Manager In Dropdown List Test *****");
        try {

            AccountDetailsPage ad = new AccountDetailsPage(driver);
            ob = new AccountPage(driver);
            EventsTagsPage ev = new EventsTagsPage(driver);
            DashboardPage dp = new DashboardPage(driver);
            commonUtils = new CommonUtils(driver);

            loadTestData(
                    "./testData/AdminAccountData/adminLoginData.json",
                    "./testData/AdminAccountData/dashboardData.json",
                    "./testData/AdminAccountData/accountDetailsData.json",
                    "./testData/PurchaserAccountData/purchaser.json"
            );

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            dp.searchForItem(getTestData("accountName"));
            dp.clickView();

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");

            //Select account admin in user type dropdown
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.userTypeDropdown),
                    getTestData("userType"));

            //Clicking on Assign Test in action dropdown for a account
            ob.performTableAction("accountsTableUserRoles", getTestData("userAccountAdminName"),
                    "Assign Tests",1);

            //Switch the tab
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            //Click on logo
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.diagnosticLogo),null);

            //Click on setting in hamburger tab
            dp.selectHamburgerTab("Settings");
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Events/Tags");

            commonUtils.clickOnElement(commonUtils.findElementByXpath(ev.addEventButton), null);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ev.eventNameTagField), randomString() );

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value='" + ob.convertDateFormat(
                    getTestData("purchaserEventStartDate")) + "';",
                    commonUtils.findElementByXpath(ev.eventStartDateCalender));
            js.executeScript("arguments[0].value='" + ob.convertDateFormat(
                            getTestData("purchaserEventEndDate")) + "';",
                    commonUtils.findElementByXpath(ev.eventEndDateCalender));

            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ev.eventStartTime),
                    getTestData("purchaserEventStartTiming") );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ev.eventEndTime),
                    getTestData("purchaserEventEndTiming") );

            js.executeScript("arguments[0].value='" + ob.convertDateFormat(
                            getTestData("purchaserTestingWindowStartDate")) + "';",
                    commonUtils.findElementByXpath(ev.testingWindowStartDateCalender));

            js.executeScript("arguments[0].value='" + ob.convertDateFormat(
                            getTestData("purchaserTestingWindowEndDate")) + "';",
                    commonUtils.findElementByXpath(ev.testingWindowEndDateCalender));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ev.testingWindowStartTime),
                    getTestData("purchaserTestingWindowStartTiming") );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ev.testingWindowEndTime),
                    getTestData("purchaserTestingWindowEndTiming") );
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(ev.saveButton));
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(
                    ob.successfulConfirmationMessage), getTestData("purchaserEventSuccessfulMessage"),
                    commonUtils.findElementByXpath(ob.successfulConfirmationOkButton));

        } catch (Exception e) {
            Assert.fail();
        }

        logger.info("****** Finished Purchaser Create Account Admin Manager In Dropdown List Test ******");
    }

}