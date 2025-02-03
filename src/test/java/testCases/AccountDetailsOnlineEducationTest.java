package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsOnlineEducationPage;
import pageObjects.AccountDetailsPage;
import pageObjects.AccountDetailsUsersAndRolesPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class AccountDetailsOnlineEducationTest extends BaseClass {

    @Test
    public void verifyOnlineEducationTest() {

        logger.info("****** Starting Online Education Test ******");
        try {
            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);

            dp.searchForItem(p.getProperty("accountName"));
            dp.clickView();

            AccountDetailsPage ad = new AccountDetailsPage(driver);
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Online Education");

            AccountDetailsOnlineEducationPage aoe = new AccountDetailsOnlineEducationPage(driver);
            commonUtils.selectRadioButton(commonUtils.findElementByXpath(
                    aoe.experiencesForProvisionedOrdersRadioButton));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(aoe.manageButton), "Manage");
            commonUtils.clickOnElement(commonUtils.findElementByXpath(aoe.addButton), "Add");
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(aoe.experienceNameField),
                    randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(aoe.attachBundleDropDown),
                    p.getProperty("attachBundle"));
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(aoe.saveButton));

            AccountDetailsUsersAndRolesPage au = new AccountDetailsUsersAndRolesPage(driver);
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(au.dialogueText),
                    p.getProperty("onlineEducationDialogueText"),
                    commonUtils.findElementByXpath(au.dialogueOkButton));
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Online Education Test ******");
    }
}
