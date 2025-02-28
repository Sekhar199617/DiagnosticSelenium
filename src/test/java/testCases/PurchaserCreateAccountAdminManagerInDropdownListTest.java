package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import pageObjects.AccountDetailsPage;
import pageObjects.DashboardPage;
import pageObjects.PurchaseLevelAccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PurchaserCreateAccountAdminManagerInDropdownListTest  extends BaseClass {
    public CommonUtils commonUtils;
    public PurchaseLevelAccountPage ob;
    public DashboardPage dp;
    public AccountDetailsPage ad;

    @Test(groups = {"Smoke"})
    public void VerifyPurchaserCreateAccountAdminManagerInDropdownListTest(){

        logger.info("***** Starting Purchaser Create Account Admin Manager In Dropdown List Test *****");
        try {
            login(p.getProperty("adminEmail"),p.getProperty("adminPassword"),true);

            dp = new DashboardPage(driver);
            commonUtils = new CommonUtils(driver);
            ad = new AccountDetailsPage(driver);
            ob = new PurchaseLevelAccountPage(driver);

            loadTestDataForTest();
            dp.searchForItem(getTestData("accountName"));
            dp.clickView();

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");

            //Select account admin in user type dropdown
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.userTypeDropdown), getTestData("usersUserTypeAccountAdmin"));

            //Clicking on Assign Test in action dropdown for a account
            ob.performTableAction("accountsTableUserRoles", getTestData("userAccountAdminName"), "Assign Tests",1);

            //Switch the tab
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            //Click on logo
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.diagnosticLogo),null);

            //Click on setting in hamburger tab
            dp.selectHamburgerTab("Settings");
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.addUserAndRolesButton),null);

            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.newUserNameField),getTestData("purchaserNewUserNameField"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.newUserPhoneField),getTestData("purchaserNewUserPhoneField"));

            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.newUserEmailField),randomString() + "@gmail.com");
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.newUserRoleDropdown),getTestData("purchaserNewUserRoleDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.newUserUsertypeDropdown),getTestData("purchaserNewUserUsertypeDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.newUserTimeZoneDropdown),getTestData("purchaserNewUserTimeZoneDropdown"));
            commonUtils.selectRandomCheckboxes(commonUtils.findElementsByCssSelector(ob.newUserAdditionalPrivilegesCheckboxList));
            commonUtils.scrollToBottom();
            commonUtils.validateAndClickCheckbox(ob.newUserActiveCheckbox);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.newUserSaveButton),null);
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(ob.successfulConfirmationMessage),getTestData("purchaserNewUserValidationMessage"),commonUtils.findElementByXpath(ob.successfulConfirmationOkButton));

        } catch (Exception e) {
            Assert.fail();
        }

        logger.info("****** Finished Purchaser Create Account Admin Manager In Dropdown List Test ******");
    }

    public void loadTestDataForTest() {
        List<String> jsonFiles = Arrays.asList(
                "./testData/accountDetailsData.json",
                "./testData/purchaserAccountData/purchaser.json"
        );

        testData = commonUtils.mergeMultipleJsonFiles(jsonFiles);
    }
}
