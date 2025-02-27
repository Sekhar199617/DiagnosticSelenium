package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsPage;
import pageObjects.AccountDetailsSettingsPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class AccountDetailsSettingsAddIntegrationTest extends BaseClass {

    public DashboardPage dp;
    public CommonUtils commonUtils;
    public AccountDetailsPage ad;
    public AccountDetailsSettingsPage sp;
    public  String jsonPath;


    @Test(groups = { "Smoke" })
    public void verify_add_integration() {
        try {
            logger.info("****** Starting Add Integration Test Case ******");

            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            dp = new DashboardPage(driver);
            commonUtils = new CommonUtils(driver);
            ad = new AccountDetailsPage(driver);
            sp = new AccountDetailsSettingsPage(driver);
            jsonPath = "./testData/adminAccountData/accountDetailsData.json";
            loadTestData(jsonPath);

            dp.searchForItem(getTestData("accountName"));
            dp.clickView();

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Settings");

            commonUtils.clickOnElement(commonUtils.findElementByXpath(sp.ssoButton),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(sp.editButton),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(sp.singleSignOnIntegrationAddButton),null);
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(sp.integrationTypeDropdown),getTestData("integrationType"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.integrationName),randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.samlSsoUrl),getTestData("samlSsoUrlAddIntegration"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.singleSignOnUrl),getTestData("singleSignOnUrlAddIntegration"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.singleSignOutUrl),getTestData("singleSignOutUrlAddIntegration"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.certificateFingerprint),getTestData("certificateFingerprintAddIntegration"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.remoteLogoutUrl),getTestData("remoteLogoutUrlAddIntegration"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(sp.iPRanges),getTestData("iPRangesAddIntegration"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(sp.saveSSOIntegrationButton),null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(sp.successfulMessage),getTestData("integrationSuccessfulMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(sp.successfulMessageOkButton),null);

        } catch (Exception e) {
            Assert.fail();
        }

        }
    }

