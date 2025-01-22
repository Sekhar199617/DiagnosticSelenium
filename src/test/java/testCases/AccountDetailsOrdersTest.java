package testCases;

import org.testng.annotations.Test;
import pageObjects.AccountDetailsPage;
import pageObjects.DashboardPage;
import pageObjects.AccountDetailsOrdersPage;
import testBase.BaseClass;

public class AccountDetailsOrdersTest extends BaseClass {

    @Test
    public void verifyProvisionedOrdersTest() {
        login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

        DashboardPage dp = new DashboardPage(driver);

        dp.searchForElement(p.getProperty("accountName"));
        dp.clickOnActionsDropDown();
        dp.clickOnView();

        AccountDetailsPage ad = new AccountDetailsPage(driver);
        ad.selectTab("Orders");

        AccountDetailsOrdersPage po = new AccountDetailsOrdersPage(driver);
        po.selectProvisionedOrdersRadioButton();
        po.clickOnAdd();
        po.selectPurchasableBundleValue(p.getProperty("purchasableBundle"));
        po.selectAvailableShippingOptionsValue(p.getProperty("availableShippingOptions"));
        po.selectAvailableVariationsOptionsValue(p.getProperty("availableVariationsOptions"));
        po.selectNoOfUnitsValue(p.getProperty("noOfUnits"));
        po.enterPricePerUnit(p.getProperty("pricePerUnit"));
        po.enterShippingAmountPerUnit(p.getProperty("shippingAmountPerUnit"));
        po.enterEstimatedTax(p.getProperty("estimatedTax"));
        po.clickOnAddOrderButton();
    }
}
