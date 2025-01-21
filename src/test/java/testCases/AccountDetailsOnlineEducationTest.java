package testCases;

import org.testng.annotations.Test;
import pageObjects.AccountDetailsOnlineEducationPage;
import pageObjects.AccountDetailsPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class AccountDetailsOnlineEducationTest extends BaseClass {

    @Test
    public void verifyOnlineEducationTest() {
        login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

        MyAccountPage macc = new MyAccountPage(driver);

        macc.searchForElement(p.getProperty("accountName"));
        macc.clickOnActionsDropDown();
        macc.clickOnView();

        AccountDetailsPage ad = new AccountDetailsPage(driver);
        ad.selectTab("Online Education");

        AccountDetailsOnlineEducationPage aoe = new AccountDetailsOnlineEducationPage(driver);
        aoe.selectExperiencesForProvisionedOrdersRadioButton();
        aoe.clickOnManage();
        aoe.clickOnAdd();
        aoe.enterExperienceName(randomString());
        aoe.selectAttachBundleValue(p.getProperty("attachBundle"));
        aoe.clickOnSave();
    }
}
