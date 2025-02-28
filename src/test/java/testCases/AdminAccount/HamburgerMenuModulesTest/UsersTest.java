package testCases.AdminAccount.HamburgerMenuModulesTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.AccountDetailsModules.UsersAndRolesPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import pageObjects.AdminAccount.HamburgerMenuModules.UsersPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class UsersTest extends BaseClass {

    @Test
    public void verifyCreateUserTest() {
        logger.info("****** Starting Create User Test ******");
        try{
            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            UsersPage usersPage = new UsersPage(driver);
            DashboardPage dashboardPage = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            UsersAndRolesPage usersAndRolesPage = new UsersAndRolesPage(driver);

            dashboardPage.selectHamburgerTab("Users");

            commonUtils.clickOnElement(commonUtils.findElementByXpath(usersPage.createUserButton),
                    "Create User");

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
                    p.getProperty("usersUserType"),
                    p.getProperty("licenseID"),
                    p.getProperty("credentials"),
                    p.getProperty("defaultTimeZone"),
                    p.getProperty("dialogueText")
            );
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Create User Test ******");
    }
}
