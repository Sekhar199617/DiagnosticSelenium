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

            DashboardPage dashboardPage = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            UsersAndRolesPage usersAndRolesPage = new UsersAndRolesPage(driver);
            AccountDetailsPage accountDetailsPage = new AccountDetailsPage(driver);
            OnlineEducationPage onlineEducationPage = new OnlineEducationPage(driver);

            String loginJsonPath = "./testData//adminLoginData.json";
            loadTestData(loginJsonPath);

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            String dashboardDataJsonPath = "./testData//dashboardData.json";
            loadTestData(dashboardDataJsonPath);

            dashboardPage.searchForItem(p.getProperty("accountName"));
            dashboardPage.clickView();

            String accountDetailsJsonPath = "./testData//accountDetailsData.json";
            loadTestData(accountDetailsJsonPath);

            commonUtils.selectTab(commonUtils.findElementsByXpath(accountDetailsPage.tabList),
                    "Online Education");

            commonUtils.selectRadioButton(commonUtils.findElementByXpath(
                    onlineEducationPage.experiencesForProvisionedOrdersRadioButton));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(onlineEducationPage.manageButton),
                    "Manage");
            commonUtils.clickOnElement(commonUtils.findElementByXpath(onlineEducationPage.addButton), "Add");
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(onlineEducationPage.experienceNameField),
                    randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(onlineEducationPage.attachBundleDropDown),
                    getTestData("attachBundle"));
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(onlineEducationPage.saveButton));
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(
                    usersAndRolesPage.dialogueText),
                    getTestData("onlineEducationDialogueText"),
                    commonUtils.findElementByXpath(usersAndRolesPage.dialogueOkButton)
            );
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Online Education Test ******");
    }
}
