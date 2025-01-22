package testCases;

import org.testng.annotations.Test;
import pageObjects.AccountDetailsPage;
import pageObjects.AccountDetailsSettingsPage;
import pageObjects.AccountDetailsUsersAndRolesPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class AccountDetailsSettingsBillingTest extends BaseClass {

    @Test
    public void verifyCreateNewBillingContactTest() {

        login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

        MyAccountPage ma = new MyAccountPage(driver);

        ma.searchForElement(p.getProperty("accountName"));
        ma.clickOnActionsDropDown();
        ma.clickOnView();

        AccountDetailsPage ad = new AccountDetailsPage(driver);
        ad.selectTab("Settings");

        AccountDetailsSettingsPage ads = new AccountDetailsSettingsPage(driver);
        ads.selectSettingsTab("Billing");
        ads.clickOnEdit();
        ads.clickOnCreateNew();

        AccountDetailsUsersAndRolesPage aduar = new AccountDetailsUsersAndRolesPage(driver);
        aduar.enterNewUserName(randomString());
        aduar.clickOnCountryDropDown();
        aduar.selectMobileCountryCode(p.getProperty("mobileCountryCode"));
        aduar.enterPhoneNumber(randomNumbers());
        aduar.enterEmail(randomString() + "@gmail.com");
        aduar.verifyShowAssigneeSessionTimeRadioButton();
        aduar.selectUserDefaultTimeZone(p.getProperty("defaultTimeZone"));
        aduar.verifyActiveCheckbox();
        aduar.clickOnSave();

        aduar.verifyDialogueText(p.getProperty("dialogueText"));
        aduar.clickOnDialogueOk();
    }
}
