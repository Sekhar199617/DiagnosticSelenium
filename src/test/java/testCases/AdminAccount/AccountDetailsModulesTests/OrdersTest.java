package testCases.AdminAccount.AccountDetailsModulesTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import pageObjects.AdminAccount.AccountDetailsModules.OrdersPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class OrdersTest extends BaseClass {

    @Test
    public void verifyProvisionedOrdersTest() {

        logger.info("****** Starting Provisioned Orders Test ******");
        try {

            OrdersPage ordersPage = new OrdersPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            AccountDetailsPage ad = new AccountDetailsPage(driver);
            DashboardPage dashboardPage = new DashboardPage(driver);

            loadTestData(
                    "./testData/AdminAccountData/adminLoginData.json",
                    "./testData/AdminAccountData/dashboardData.json",
                    "./testData/AdminAccountData/accountDetailsData.json"
            );

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            dashboardPage.searchForItem(getTestData("accountName"));
            dashboardPage.clickView();

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Orders");
            commonUtils.selectRadioButton(commonUtils.findElementByXpath(
                    ordersPage.provisionedOrdersRadioButton));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ordersPage.addText), null);
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ordersPage.purchasableBundleDropDown),
                    getTestData("purchasableBundle"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ordersPage.availableShippingOptions),
                    getTestData("availableShippingOptions"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ordersPage.availableVariationsOptions),
                    getTestData("availableVariationsOptions"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ordersPage.noOfUnitsDropDown),
                    getTestData("noOfUnits"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ordersPage.pricePerUnit),
                    randomNumbers(3));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ordersPage.shippingAmountPerUnit),
                    randomNumbers(3));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ordersPage.estimatedTaxField),
                    randomNumbers(3));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ordersPage.addOrderButton), null);
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Provisioned Orders Test ******");

    }
}
