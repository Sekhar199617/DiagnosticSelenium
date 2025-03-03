package testCases.AdminAccount.HamburgerMenuModulesTests;
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

            UsersPage usersPage = new UsersPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            DashboardPage dashboardPage = new DashboardPage(driver);
            UsersAndRolesPage usersAndRolesPage = new UsersAndRolesPage(driver);

            loadTestData(
                    "./testData/AdminAccountData/adminLoginData.json",
                    "./testData/AdminAccountData/hamburgerMenuModulesData.json"
            );

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

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
                    getTestData("mobileCountryCode"),
                    randomNumbers(10),
                    getTestData("role"),
                    getTestData("usersUserType"),
                    randomNumbers(6), //licenseID
                    randomAlphaNumeric(), //credentials
                    getTestData("defaultTimeZone"),
                    getTestData("newUsersDialogueText")
            );
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Create User Test ******");
    }
}
