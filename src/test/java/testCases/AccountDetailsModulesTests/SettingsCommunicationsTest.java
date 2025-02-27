package testCases.AccountDetailsModulesTests;

import org.testng.annotations.Test;
import pageObjects.AccountDetailsPage;
import pageObjects.AccountDetailsSettingsPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class SettingsCommunicationsTest extends BaseClass {

    @Test
    public void verifyUpdateCommunicationsDetails() {

        login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

        DashboardPage dashboardPage = new DashboardPage(driver);
        CommonUtils commonUtils = new CommonUtils(driver);
        AccountDetailsPage detailsPage = new AccountDetailsPage(driver);
        AccountDetailsSettingsPage settingsPage = new AccountDetailsSettingsPage(driver);

        dashboardPage.searchForItem(p.getProperty("accountName"));
        dashboardPage.clickView();

        commonUtils.selectTab(commonUtils.findElementsByXpath(detailsPage.tabList), "Settings");

        commonUtils.selectTab(commonUtils.findElementsByXpath(settingsPage.settingsTabsList),
                "Communications");
        commonUtils.clickOnElement(commonUtils.findElementByXpath(settingsPage.editButton), "Edit");

        commonUtils.validateGetText(commonUtils.findElementById(settingsPage.accountNameField),
                p.getProperty("accountName"));
        commonUtils.validateCheckbox(commonUtils.findElementByCssSelector(settingsPage.activeCheckbox));
        commonUtils.validateCheckbox(commonUtils.findElementByCssSelector(settingsPage.demoAccountCheckbox));

    }
}
