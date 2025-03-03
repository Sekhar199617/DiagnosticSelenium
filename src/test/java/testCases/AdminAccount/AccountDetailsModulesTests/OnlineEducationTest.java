package testCases.AdminAccount.AccountDetailsModulesTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.AccountDetailsModules.OnlineEducationPage;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.AccountDetailsModules.UsersAndRolesPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class OnlineEducationTest extends BaseClass {

    @Test
    public void verifyOnlineEducationTest() {

        logger.info("****** Starting Online Education Test ******");
        try {

            CommonUtils commonUtils = new CommonUtils(driver);
            DashboardPage dashboardPage = new DashboardPage(driver);
            UsersAndRolesPage usersAndRolesPage = new UsersAndRolesPage(driver);
            AccountDetailsPage accountDetailsPage = new AccountDetailsPage(driver);
            OnlineEducationPage onlineEducationPage = new OnlineEducationPage(driver);

            loadTestData(
                    "./testData/adminLoginData.json",
                    "./testData/accountDetailsData.json",
                    "./testData/dashboardData.json"
            );

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            dashboardPage.searchForItem(getTestData("accountName"));
            dashboardPage.clickView();

            commonUtils.selectTab(commonUtils.findElementsByXpath(accountDetailsPage.tabList),
                    "Online Education");
            commonUtils.selectRadioButton(commonUtils.findElementByXpath(
                    onlineEducationPage.experiencesForProvisionedOrdersRadioButton));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(onlineEducationPage.manageButton),
                    "Manage");
            commonUtils.clickOnElement(commonUtils.findElementByXpath(onlineEducationPage.addButton),
                    "Add");
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(
                    onlineEducationPage.experienceNameField), randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(
                    onlineEducationPage.attachBundleDropDown), getTestData("attachBundle"));
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(
                    onlineEducationPage.saveButton));

            commonUtils.validateDialogueTextAndClickConfirm(
                    commonUtils.findElementByXpath(usersAndRolesPage.dialogueText),
                    getTestData("onlineEducationDialogueText"),
                    commonUtils.findElementByXpath(usersAndRolesPage.dialogueOkButton));
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Online Education Test ******");
    }
}
