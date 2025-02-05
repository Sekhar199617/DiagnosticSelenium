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

            dp.selectHamburgerTab("Users");

            UsersPage up = new UsersPage(driver);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(up.createUserButton), "Create User");

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
