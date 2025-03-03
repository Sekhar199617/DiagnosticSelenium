package testCases.AdminAccount.AccountDetailsModulesTests.Settings;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.AccountDetailsModules.Settings.SettingsPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class SSOTest extends BaseClass {

    @Test(groups = { "Smoke" })
    public void verify_add_integration() {

        logger.info("****** Starting Add Integration Test Case ******");
        try {

            SettingsPage sp = new SettingsPage(driver);
            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            AccountDetailsPage ad = new AccountDetailsPage(driver);

            loadTestData(
                    "./testData/AdminAccountData/adminLoginData.json",
                    "./testData/AdminAccountData/dashboardData.json",
                    "./testData/AdminAccountData/accountDetailsData.json"
            );

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            dp.searchForItem(getTestData("accountName"));
            dp.clickView();

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Settings");
            commonUtils.clickOnElement(commonUtils.findElementByXpath(sp.ssoButton),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(sp.editButton),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(sp.singleSignOnIntegrationAddButton),null);
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(sp.integrationTypeDropdown),
                    getTestData("integrationType"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.integrationName),randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.samlSsoUrl),
                    getTestData("samlSsoUrlAddIntegration"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.singleSignOnUrl),
                    getTestData("singleSignOnUrlAddIntegration"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.singleSignOutUrl),
                    getTestData("singleSignOutUrlAddIntegration"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.certificateFingerprint),
                    getTestData("certificateFingerprintAddIntegration"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.remoteLogoutUrl),
                    getTestData("remoteLogoutUrlAddIntegration"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.iPRanges),
                    getTestData("iPRangesAddIntegration"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(sp.saveSSOIntegrationButton),null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(sp.successfulMessage),
                    getTestData("integrationSuccessfulMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(sp.successfulMessageOkButton),null);

        } catch (Exception e) {
            Assert.fail();
        }

        }
    }

