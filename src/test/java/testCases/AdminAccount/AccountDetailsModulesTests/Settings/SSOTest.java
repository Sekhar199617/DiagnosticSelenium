package testCases.AdminAccount.AccountDetailsModulesTests.Settings;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.AccountDetailsModules.Settings.SettingsPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class SSOTest extends BaseClass {

    @Test(groups = { "Smoke" })
    public void verify_add_integration() {
        try {
            logger.info("****** Starting Add Integration Test Case ******");

            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);
            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            dp.searchForItem(p.getProperty("accountName"));
            dp.clickView();
            AccountDetailsPage ad = new AccountDetailsPage(driver);
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Settings");
            SettingsPage sp = new SettingsPage(driver);

            commonUtils.clickOnElement(commonUtils.findElementByXpath(sp.ssoButton),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(sp.editButton),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(sp.singleSignOnIntegrationAddButton),null);
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(sp.integrationTypeDropdown),p.getProperty("integrationType"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.integrationName),randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.samlSsoUrl),p.getProperty("samlSsoUrlAddIntegration"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.singleSignOnUrl),p.getProperty("singleSignOnUrlAddIntegration"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.singleSignOutUrl),p.getProperty("singleSignOutUrlAddIntegration"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.certificateFingerprint),p.getProperty("certificateFingerprintAddIntegration"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.remoteLogoutUrl),p.getProperty("remoteLogoutUrlAddIntegration"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.iPRanges),p.getProperty("iPRangesAddIntegration"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(sp.saveSSOIntegrationButton),null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(sp.successfulMessage),p.getProperty("integrationSuccessfulMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(sp.successfulMessageOkButton),null);

        } catch (Exception e) {
            Assert.fail();
        }

        }
    }

