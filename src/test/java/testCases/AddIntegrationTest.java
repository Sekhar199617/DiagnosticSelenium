package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsPage;
import pageObjects.DashboardPage;
import pageObjects.SettingPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class AddIntegrationTest extends BaseClass {

    @Test(groups = { "Smoke" })
    public void verify_add_integration() {
        try {
            logger.info("****** Starting Add Integration Test Case ******");

            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);
            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            commonUtils.enterValueInTextField(dp.searchField, p.getProperty("accountName"));
            commonUtils.clickOnElement(dp.searchButton, "Search");
            commonUtils.clickOnElement(dp.actionsDropDown, null);
            commonUtils.clickOnElement(dp.view, "View");
            AccountDetailsPage ad = new AccountDetailsPage(driver);
            commonUtils.selectTab(ad.tabList, "Settings");
            SettingPage sp = new SettingPage(driver);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(sp.ssoButton),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(sp.editButton),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(sp.singleSignOnIntegrationAddButton),null);
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(sp.integrationTypeDropdown),p.getProperty("integrationType"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.integrationName),randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.samlSsoUrl),randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.singleSignOnUrl),randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.singleSignOutUrl),randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.certificateFingerprint),randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.remoteLogoutUrl),randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.iPRanges),randomString());
            commonUtils.clickOnElement(commonUtils.findElementByXpath(sp.saveSSOIntegrationButton),null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(sp.successfulMessage),p.getProperty("integrationSuccessfulMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(sp.successfulMessageOkButton),null);

        } catch (Exception e) {
            Assert.fail();
        }

        }
    }

