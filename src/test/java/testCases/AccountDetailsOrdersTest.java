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

            commonUtils.enterValueInTextField(dp.searchField, p.getProperty("accountName"));
            commonUtils.clickOnElement(dp.searchButton, "Search");
            commonUtils.clickOnElement(dp.actionsDropDown, null);
            commonUtils.clickOnElement(dp.view, "View");

            AccountDetailsPage ad = new AccountDetailsPage(driver);
            commonUtils.selectTab(ad.tabList, "Orders");

            AccountDetailsOrdersPage po = new AccountDetailsOrdersPage(driver);
            commonUtils.selectRadioButton(po.provisionedOrdersRadioButton);
            commonUtils.clickOnElement(po.addText, null);
            commonUtils.selectDropDownValue(po.purchasableBundleDropDown, p.getProperty("purchasableBundle"));
            commonUtils.selectDropDownValue(po.availableShippingOptions, p.getProperty("availableShippingOptions"));
            commonUtils.selectDropDownValue(po.availableVariationsOptions, p.getProperty("availableVariationsOptions"));
            commonUtils.selectDropDownValue(po.noOfUnitsDropDown, p.getProperty("noOfUnits"));
            commonUtils.enterValueInTextField(po.pricePerUnit, p.getProperty("pricePerUnit"));
            commonUtils.enterValueInTextField(po.shippingAmountPerUnit, p.getProperty("shippingAmountPerUnit"));
            commonUtils.enterValueInTextField(po.estimatedTaxField, p.getProperty("estimatedTax"));
            commonUtils.clickOnElement(po.addOrderButton, null);
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Provisioned Orders Test ******");

    }
}
