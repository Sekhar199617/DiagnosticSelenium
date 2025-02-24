package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DashboardPage;
import pageObjects.ProductsAndBundlesPage;
import pageObjects.PurchaseLevelAccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class CreateBundleByAddingProduct extends BaseClass {

    @Test
    public void verifyCreateBundleByAddingProduct() {

        logger.info("****** Starting Create Bundle By Adding Product Test ******");
        try{
            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            ProductsAndBundlesPage pb = new ProductsAndBundlesPage(driver);

            dp.selectHamburgerTab("Products & Bundles");
            //Click on bundle tab

            commonUtils.clickOnElement(commonUtils.findElementById(pb.bundlesTab),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pb.addBundleButton),null);
            commonUtils.enterValueInTextField(commonUtils.findElementById(pb.purchaseBundleNameField),randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementById(pb.bundleTypeDropdown),p.getProperty("bundleTypeDropdown"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pb.addButton),null);
            //Select random product
            pb.selectMultipleRandomProducts("bundleProductListTable", 4);
            commonUtils.scrollToUp();
            //Fill all the information
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pb.addToBundleButton),null);
            commonUtils.enterValueInTextField(commonUtils.findElementById(pb.bundleSKUField),randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pb.vendorBundleId),randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pb.gdtBundlePrice),randomNumbers(3));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pb.accountBundlePrice),randomNumbers(3    ));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pb.bundleCostGdt),randomNumbers(3    ));
            pb.scrollUntilNewContentLoads();
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pb.shippingWeight),randomNumbers(2  ));
            commonUtils.selectCheckbox(commonUtils.findElementByXpath(pb.prepareOutboundShippingLabelCheckbox));
            //Upload Image
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pb.uploadBundleImage),null);
            pb.uploadImage();
            pb.scrollUntilNewContentLoads();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pb.saveButton),null);
            PurchaseLevelAccountPage  ob = new PurchaseLevelAccountPage(driver);

            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(ob.successfulConfirmationMessage),p.getProperty("bundleUploadSuccessfulMessage"),commonUtils.findElementByXpath(ob.successfulConfirmationOkButton));

        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Create Bundle By Adding Product Test ******");
    }
}
