package testCases.AccountDetailsModulesTest.Orders;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsModules.AdminAccount.AccountDetailsPage;
import pageObjects.AccountDetailsModules.AdminAccount.Dashboard.DashboardPage;
import pageObjects.AccountDetailsModules.AdminAccount.Orders.OrdersPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class OrdersTest extends BaseClass {

    @Test
    public void verifyProvisionedOrdersTest() {

        logger.info("****** Starting Provisioned Orders Test ******");
        try {
            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            OrdersPage ordersPage = new OrdersPage();
            CommonUtils commonUtils = new CommonUtils(driver);
            AccountDetailsPage accountDetailsPage = new AccountDetailsPage(driver);
            DashboardPage dashboardPage = new DashboardPage(driver);

            dashboardPage.searchForItem(p.getProperty("accountName"));
            dashboardPage.clickView();

            commonUtils.selectTab(commonUtils.findElementsByXpath(accountDetailsPage.tabList), "Orders");

            commonUtils.selectRadioButton(commonUtils.findElementByXpath(ordersPage.provisionedOrdersRadioButton));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ordersPage.addText), null);
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ordersPage.purchasableBundleDropDown),
                    p.getProperty("purchasableBundle"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ordersPage.availableShippingOptions),
                    p.getProperty("availableShippingOptions"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ordersPage.availableVariationsOptions),
                    p.getProperty("availableVariationsOptions"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ordersPage.noOfUnitsDropDown),
                    p.getProperty("noOfUnits"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ordersPage.pricePerUnit),
                    p.getProperty("pricePerUnit"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ordersPage.shippingAmountPerUnit),
                    p.getProperty("shippingAmountPerUnit"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ordersPage.estimatedTaxField),
                    p.getProperty("estimatedTax"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ordersPage.addOrderButton), null);
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Provisioned Orders Test ******");

    }
}
