package testCases.AdminAccount.HamburgerMenuModulesTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.AccountDetailsModules.UsersAndRolesPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import pageObjects.AdminAccount.HamburgerMenuModules.SchedulesPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class SchedulesTest extends BaseClass {

    @Test
    public void verifyCreateObserver() {
        logger.info("****** Starting Create Observer Test ******");
        try{
            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dashboardPage = new DashboardPage(driver);
            SchedulesPage schedulesPage = new SchedulesPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            UsersAndRolesPage usersAndRolesPage = new UsersAndRolesPage(driver);

            dashboardPage.selectHamburgerTab("Schedules");

            commonUtils.clickOnElement(commonUtils.findElementByXpath(schedulesPage.editScheduleButton), null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(schedulesPage.newObserverButton), null);

            commonUtils.createUser(
                    usersAndRolesPage.newUserNameField,
                    randomString(),
                    usersAndRolesPage.mobileCountryCodeDropDown,
                    usersAndRolesPage.countryList,
                    usersAndRolesPage.phoneNumberField,
                    usersAndRolesPage.emailField,
                    usersAndRolesPage.roleDropDown,
                    usersAndRolesPage.userTypeDropDown,
                    usersAndRolesPage.licenseIDField,
                    usersAndRolesPage.credentialsField,
                    usersAndRolesPage.bundlesNotAttachedField,
                    usersAndRolesPage.rightArrow,
                    usersAndRolesPage.selectedBundleField,
                    usersAndRolesPage.additionalPriviligesCheckboxesList,
                    usersAndRolesPage.supportManagerPriviligesCheckboxesList,
                    usersAndRolesPage.userTimeZoneRadioButton,
                    usersAndRolesPage.userDefaultTimeZoneDropDown,
                    usersAndRolesPage.activeCheckbox,
                    usersAndRolesPage.saveButton,
                    usersAndRolesPage.dialogueText,
                    usersAndRolesPage.dialogueOkButton,
                    p.getProperty("mobileCountryCode"),
                    randomNumbers(10),
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