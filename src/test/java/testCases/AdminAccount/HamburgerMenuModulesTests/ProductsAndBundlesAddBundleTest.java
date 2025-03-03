package testCases.AdminAccount.HamburgerMenuModulesTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import pageObjects.AdminAccount.HamburgerMenuModules.ProductsAndBundlesPage;
import pageObjects.PurchaserAccount.AccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class ProductsAndBundlesAddBundleTest extends BaseClass {

    @Test
    public void verifyCreateBundleByAddingProduct() {

        logger.info("****** Starting Create Bundle By Adding Product Test ******");
        try{

            AccountPage ob = new AccountPage(driver);
            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            ProductsAndBundlesPage pb = new ProductsAndBundlesPage(driver);

            loadTestData(
                    "./testData/AdminAccountData/adminLoginData.json",
                    "./testData/AdminAccountData/dashboardData.json",
                    "./testData/AdminAccountData/hamburgerMenuModulesData.json"
            );

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            dp.selectHamburgerTab("Products & Bundles");
            //Click on bundle tab

            commonUtils.clickOnElement(commonUtils.findElementById(pb.bundlesTab),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pb.addBundleButton),null);
            commonUtils.enterValueInTextField(commonUtils.findElementById(pb.purchaseBundleNameField),randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementById(pb.bundleTypeDropdown),
                    getTestData("bundleTypeDropdown"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pb.addButton),null);
            //Select random product
            pb.selectMultipleRandomProducts("bundleProductListTable", 4);
            commonUtils.scrollToUp();
            //Fill all the information
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pb.addToBundleButton),null);
            commonUtils.enterValueInTextField(commonUtils.findElementById(pb.bundleSKUField),randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pb.vendorBundleId),randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pb.gdtBundlePrice),randomNumbers(3));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pb.accountBundlePrice),
                    randomNumbers(3    ));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pb.bundleCostGdt),randomNumbers(3));
            pb.scrollUntilNewContentLoads();
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pb.shippingWeight),randomNumbers(2));
            commonUtils.selectCheckbox(commonUtils.findElementByXpath(pb.prepareOutboundShippingLabelCheckbox));
            //Upload Image
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pb.uploadBundleImage),null);
            pb.uploadImage();
            pb.scrollUntilNewContentLoads();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pb.saveButton),null);

            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(ob.successfulConfirmationMessage),
                    getTestData("bundleUploadSuccessfulMessage"),
                    commonUtils.findElementByXpath(ob.successfulConfirmationOkButton));

        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Create Bundle By Adding Product Test ******");
    }
}