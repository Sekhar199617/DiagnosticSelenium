package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsPage;
import pageObjects.DashboardPage;
import pageObjects.AccountDetailsOrdersPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class AccountDetailsOrdersTest extends BaseClass {

    @Test
    public void verifyProvisionedOrdersTest() {

        logger.info("****** Starting Provisioned Orders Test ******");
        try {
            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);

            dp.searchForItem(p.getProperty("accountName"));
            dp.clickView();

            AccountDetailsPage ad = new AccountDetailsPage(driver);
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Orders");

            AccountDetailsOrdersPage po = new AccountDetailsOrdersPage(driver);
            commonUtils.selectRadioButton(commonUtils.findElementByXpath(po.provisionedOrdersRadioButton));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(po.addText), null);
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(po.purchasableBundleDropDown),
                    p.getProperty("purchasableBundle"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(po.availableShippingOptions),
                    p.getProperty("availableShippingOptions"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(po.availableVariationsOptions),
                    p.getProperty("availableVariationsOptions"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(po.noOfUnitsDropDown),
                    p.getProperty("noOfUnits"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(po.pricePerUnit),
                    p.getProperty("pricePerUnit"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(po.shippingAmountPerUnit),
                    p.getProperty("shippingAmountPerUnit"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(po.estimatedTaxField),
                    p.getProperty("estimatedTax"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(po.addOrderButton), null);
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Provisioned Orders Test ******");

    }
}
