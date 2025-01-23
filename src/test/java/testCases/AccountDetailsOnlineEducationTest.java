package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsOnlineEducationPage;
import pageObjects.AccountDetailsPage;
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

            commonUtils.enterValueInTextField(dp.searchField, p.getProperty("accountName"));
            commonUtils.clickOnElement(dp.searchButton, "Search");
            commonUtils.clickOnElement(dp.actionsDropDown, null);
            commonUtils.clickOnElement(dp.view, "View");

            AccountDetailsPage ad = new AccountDetailsPage(driver);
            commonUtils.selectTab(ad.tabList, "Online Education");

            AccountDetailsOnlineEducationPage aoe = new AccountDetailsOnlineEducationPage(driver);
            commonUtils.selectRadioButton(aoe.experiencesForProvisionedOrdersRadioButton);
            commonUtils.clickOnElement(aoe.manageButton, "Manage");
            commonUtils.clickOnElement(aoe.addButton, "Add");
            commonUtils.enterValueInTextField(aoe.experienceNameField, randomString());
            commonUtils.selectDropDownValue(aoe.attachBundleDropDown, p.getProperty("attachBundle"));
            commonUtils.clickOnElement(aoe.saveButton, "Save");
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Online Education Test ******");
    }
}
