package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsUsersAndRolesPage;
import pageObjects.DashboardPage;
import pageObjects.SchedulesPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class SchedulesTest extends BaseClass {

    @Test
    public void verifyCreateObserver() {
        logger.info("****** Starting Create Observer Test ******");
        try{
            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);

            dp.selectHamburgerTab("Schedules");

            SchedulesPage sp = new SchedulesPage(driver);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(sp.editScheduleButton), null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(sp.newObserverButton), null);

            AccountDetailsUsersAndRolesPage au = new AccountDetailsUsersAndRolesPage(driver);
            commonUtils.createUser(
                    au.newUserNameField,
                    randomString(),
                    au.mobileCountryCodeDropDown,
                    au.countryList,
                    au.phoneNumberField,
                    au.emailField,
                    au.roleDropDown,
                    au.userTypeDropDown,
                    au.licenseIDField,
                    au.credentialsField,
                    au.bundlesNotAttachedField,
                    au.rightArrow,
                    au.selectedBundleField,
                    au.additionalPriviligesCheckboxesList,
                    au.supportManagerPriviligesCheckboxesList,
                    au.userTimeZoneRadioButton,
                    au.userDefaultTimeZoneDropDown,
                    au.activeCheckbox,
                    au.saveButton,
                    au.dialogueText,
                    au.dialogueOkButton,
                    p.getProperty("mobileCountryCode"),
                    randomNumbers(),
                    p.getProperty("role"),
                    p.getProperty("observerUserType"),
                    p.getProperty("licenseID"),
                    p.getProperty("credentials"),
                    p.getProperty("defaultTimeZone"),
                    p.getProperty("dialogueText")
            );
        }catch (Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Create Observer Test ******");
    }
}
