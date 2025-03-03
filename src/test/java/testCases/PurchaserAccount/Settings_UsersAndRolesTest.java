package testCases.PurchaserAccount;

import org.testng.annotations.Test;
import org.testng.Assert;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import pageObjects.PurchaserAccount.AccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class Settings_UsersAndRolesTest extends BaseClass {
    public CommonUtils commonUtils;
    public AccountPage ob;
    public DashboardPage dp;
    public AccountDetailsPage ad;

    @Test(groups = {"Smoke"})
    public void VerifyPurchaserCreateAccountAdminManagerInDropdownListTest(){

        logger.info("***** Starting Purchaser Create Account Admin Manager In Dropdown List Test *****");
        try {

            dp = new DashboardPage(driver);
            commonUtils = new CommonUtils(driver);
            ad = new AccountDetailsPage(driver);
            ob = new AccountPage(driver);

            loadTestData(
                    "./testData/AdminAccountData/adminLoginData.json",
                    "./testData/AdminAccountData/dashboardData.json",
                    "./testData/PurchaserAccountData/purchaser.json",
                    "./testData/AdminAccountData/accountDetailsData.json"
            );

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            dp.searchForItem(getTestData("accountName"));
            dp.clickView();

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");

            //Select account admin in user type dropdown
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.userTypeDropdown),
                    getTestData("usersUserTypeAccountAdmin"));

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
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.addUserAndRolesButton),null);

            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.newUserNameField),
                    getTestData("purchaserNewUserNameField"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.newUserPhoneField),
                    getTestData("purchaserNewUserPhoneField"));

            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.newUserEmailField),
                    randomString() + "@gmail.com");
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.newUserRoleDropdown),
                    getTestData("purchaserNewUserRoleDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.newUserUsertypeDropdown),
                    getTestData("purchaserNewUserUsertypeDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.newUserTimeZoneDropdown),
                    getTestData("purchaserNewUserTimeZoneDropdown"));
            commonUtils.selectRandomCheckboxes(commonUtils.findElementsByCssSelector(
                    ob.newUserAdditionalPrivilegesCheckboxList));
            commonUtils.scrollToBottom();
            commonUtils.validateAndClickCheckbox(ob.newUserActiveCheckbox);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.newUserSaveButton),null);
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(
                    ob.successfulConfirmationMessage),
                    getTestData("purchaserNewUserValidationMessage"),
                    commonUtils.findElementByXpath(ob.successfulConfirmationOkButton)
            );

        } catch (Exception e) {
            Assert.fail();
        }

        logger.info("****** Finished Purchaser Create Account Admin Manager In Dropdown List Test ******");
    }
}