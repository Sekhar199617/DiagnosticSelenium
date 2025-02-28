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
            DashboardPage dashboardPage = new DashboardPage(driver);
            SchedulesPage schedulesPage = new SchedulesPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            UsersAndRolesPage usersAndRolesPage = new UsersAndRolesPage(driver);

            String loginJsonPath = "./testData//adminLoginData.json";
            loadTestData(loginJsonPath);

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            String accountDetailsJsonPath = "./testData//accountDetailsData.json";
            loadTestData(accountDetailsJsonPath);

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
                    getTestData("mobileCountryCode"),
                    randomNumbers(10),
                    getTestData("role"),
                    getTestData("observerUserType"),
                    randomNumbers(6),
                    randomAlphaNumeric(),
                    getTestData("defaultTimeZone"),
                    getTestData("newUsersDialogueText")
            );
        }catch (Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Create Observer Test ******");
    }
}