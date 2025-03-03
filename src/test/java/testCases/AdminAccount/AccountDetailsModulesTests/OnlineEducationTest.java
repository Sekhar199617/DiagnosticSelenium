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
            loadTestData(
                    "./testData/adminLoginData.json",
                    "./testData/accountDetailsData.json",
                    "./testData/dashboardData.json"
            );

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);

            dp.searchForItem(getTestData("accountName"));
            dp.clickView();

            AccountDetailsPage ad = new AccountDetailsPage(driver);
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Online Education");

            OnlineEducationPage aoe = new OnlineEducationPage(driver);
            commonUtils.selectRadioButton(commonUtils.findElementByXpath(
                    aoe.experiencesForProvisionedOrdersRadioButton));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(aoe.manageButton), "Manage");
            commonUtils.clickOnElement(commonUtils.findElementByXpath(aoe.addButton), "Add");
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(aoe.experienceNameField),
                    randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(aoe.attachBundleDropDown),
                    getTestData("attachBundle"));
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(aoe.saveButton));

            UsersAndRolesPage au = new UsersAndRolesPage(driver);
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(au.dialogueText),
                    getTestData("onlineEducationDialogueText"),
                    commonUtils.findElementByXpath(au.dialogueOkButton));
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Online Education Test ******");
    }
}
