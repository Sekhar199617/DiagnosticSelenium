package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsUsersAndRolesPage;
import pageObjects.DashboardPage;
import pageObjects.UsersPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class UsersTest extends BaseClass {

    @Test
    public void verifyCreateUserTest() {
        logger.info("****** Starting Create User Test ******");
        try{
            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);

            commonUtils.clickOnElement(dp.togglerIcon, null);
            commonUtils.selectTab(dp.hamburgerMenuList, "Users");

            UsersPage up = new UsersPage(driver);
            commonUtils.clickOnElement(up.createUserButton, "Create User");

            AccountDetailsUsersAndRolesPage au = new AccountDetailsUsersAndRolesPage(driver);
            commonUtils.createUser(
                    au.newUserNameField,
                    au.mobileCountryCodeDropDown,
                    au.countryList,
                    au.phoneNumberField,
                    au.emailField,
                    au.roleDropDown,
                    au.userTypeDropDown,
                    au.licenseIDField,
                    au.credentialsField,
                    au.unselectedBundlesList,
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
                    randomString(),  // randomUser
                    p.getProperty("mobileCountryCode"),
                    randomNumbers(), // randomPhoneNumber
                    p.getProperty("role"),
                    p.getProperty("usersUserType"),
                    p.getProperty("licenseID"),
                    p.getProperty("credentials"),
                    p.getProperty("unselectedBundle"),
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
