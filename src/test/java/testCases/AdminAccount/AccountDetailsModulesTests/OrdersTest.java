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
            loadTestData(
                    "./testData/adminLoginData.json",
                    "./testData/accountDetailsData.json",
                    "./testData/dashboardData.json"
            );

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);

            dp.searchForItem(getTestData("accountName"));
            dp.clickView();

            AccountDetailsPage ad = new AccountDetailsPage(driver);
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Orders");

            OrdersPage po = new OrdersPage(driver);
            commonUtils.selectRadioButton(commonUtils.findElementByXpath(po.provisionedOrdersRadioButton));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(po.addText), null);
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(po.purchasableBundleDropDown),
                    getTestData("purchasableBundle"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(po.availableShippingOptions),
                    getTestData("availableShippingOptions"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(po.availableVariationsOptions),
                    getTestData("availableVariationsOptions"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(po.noOfUnitsDropDown),
                    getTestData("noOfUnits"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(po.pricePerUnit),
                    randomNumbers(3));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(po.shippingAmountPerUnit),
                    randomNumbers(3));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(po.estimatedTaxField),
                    randomNumbers(3));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(po.addOrderButton), null);
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Provisioned Orders Test ******");

    }
}
