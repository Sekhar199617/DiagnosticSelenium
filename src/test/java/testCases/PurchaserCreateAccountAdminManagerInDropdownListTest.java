package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsPage;
import pageObjects.DashboardPage;
import pageObjects.PurchaseLevelAccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class PurchaserCreateAccountAdminManagerInDropdownListTest  extends BaseClass {
    public CommonUtils commonUtils;
    public PurchaseLevelAccountPage ob;

    @Test(groups = {"Smoke"})
    public void VerifyPurchaserCreateAccountAdminManagerInDropdownListTest(){

        logger.info("***** Starting Purchaser Create Account Admin Manager In Dropdown List Test *****");
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

            //Click on setting in hamburger tab
            dp.selectHamburgerTab("Settings");
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.addUserAndRolesButton),null);

            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.newUserNameField),p.getProperty("purchaserNewUserNameField"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.newUserPhoneField),p.getProperty("purchaserNewUserPhoneField"));

            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.newUserEmailField),randomString() + "@gmail.com");
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.newUserRoleDropdown),p.getProperty("purchaserNewUserRoleDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.newUserUsertypeDropdown),p.getProperty("purchaserNewUserUsertypeDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.newUserTimeZoneDropdown),p.getProperty("purchaserNewUserTimeZoneDropdown"));
            commonUtils.selectRandomCheckboxes(commonUtils.findElementsByCssSelector(ob.newUserAdditionalPrivilegesCheckboxList));
            commonUtils.scrollToBottom();
            commonUtils.validateAndClickCheckbox(ob.newUserActiveCheckbox);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.newUserSaveButton),null);
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(ob.successfulConfirmationMessage),p.getProperty("purchaserNewUserValidationMessage"),commonUtils.findElementByXpath(ob.successfulConfirmationOkButton));

        } catch (Exception e) {
            Assert.fail();
        }


        logger.info("****** Finished Purchaser Create Account Admin Manager In Dropdown List Test ******");
    }
}
