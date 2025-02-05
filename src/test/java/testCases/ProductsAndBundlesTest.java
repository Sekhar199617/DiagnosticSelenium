package testCases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DashboardPage;
import pageObjects.ProductsAndBundlesPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class ProductsAndBundlesTest extends BaseClass {

    @Test
    public void verifyAddProduct() {
        login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

        DashboardPage dp = new DashboardPage(driver);
        CommonUtils commonUtils = new CommonUtils(driver);

        dp.selectHamburgerTab("Products & Bundles");

        ProductsAndBundlesPage pb = new ProductsAndBundlesPage(driver);
        commonUtils.clickOnElement(commonUtils.findElementByXpath(pb.addProductButton), null);

        commonUtils.enterValueInTextField(commonUtils.findElementById(pb.skuField), randomString());
        commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pb.productNameField), randomString());
        commonUtils.selectDropDownValue(commonUtils.findElementById(pb.typeDropdownField), "Product");
        commonUtils.enterValueInTextField(commonUtils.findElementById(pb.gdtProductPriceField), randomNumbers(3));
        commonUtils.enterValueInTextField(commonUtils.findElementById(pb.accountProductPriceField),
                randomNumbers(3));

        WebElement productPurchaseRequiredCheckbox = commonUtils.findElementById(pb.productPurchaseRequiredCheckbox);
        WebElement educationAllowedWithoutTestPurchaseCheckbox = commonUtils.findElementById(
                pb.educationAllowedWithoutTestPurchaseCheckbox);

        commonUtils.selectCheckbox(productPurchaseRequiredCheckbox);
        boolean isCheckboxEnabled = educationAllowedWithoutTestPurchaseCheckbox.isEnabled();

        if(productPurchaseRequiredCheckbox.isSelected()) {
            if (!isCheckboxEnabled) {
                System.out.println("The checkbox is disabled.");
            } else {
                System.out.println("The checkbox is enabled.");
            }
            Assert.assertFalse(isCheckboxEnabled, "The checkbox is disabled.");
            commonUtils.selectCheckbox(commonUtils.findElementById(pb.educationReqWithTestPurchaseCheckbox));
            commonUtils.selectCheckbox(commonUtils.findElementById(pb.educationAllowedWithTestPurchaseCheckbox));
        }

        WebElement bulkShipmentAllowedCheckbox = commonUtils.findElementById(pb.bulkShipmentAllowedCheckbox);
        WebElement individualShipmentAllowedCheckbox = commonUtils.findElementById(pb.individualShipmentAllowedCheckbox);

        commonUtils.clickOnElement(bulkShipmentAllowedCheckbox, null);
        commonUtils.clickOnElement(individualShipmentAllowedCheckbox, null);

        if (bulkShipmentAllowedCheckbox.isSelected() || individualShipmentAllowedCheckbox.isSelected()) {
            commonUtils.selectCheckbox(commonUtils.findElementById(pb.educationReqWithTestPurchaseCheckbox));
            commonUtils.selectCheckbox(commonUtils.findElementById(pb.educationAllowedWithTestPurchaseCheckbox));
            commonUtils.selectCheckbox(commonUtils.findElementById(pb.productPurchaseRequiredCheckbox));
        }

        //Availability automation is pending

        //Shipping Settings
        commonUtils.validateCheckbox(commonUtils.findElementById(pb.returnToLabCheckbox));
        commonUtils.selectDropDownValue(commonUtils.findElementById(pb.outboundShippingToAssigneeMaxTimeDropdown),
                p.getProperty("outboundShippingToAssigneeMaximumTime"));
        commonUtils.selectDropDownValue(commonUtils.findElementById(pb.inboundShippingToLabMaxTime),
                p.getProperty("inboundShippingToLabMaximumTime"));

        //Product Details
        commonUtils.enterValueInTextField(commonUtils.findElementById(pb.vendorProductIdField),
                "Product" + randomNumbers(5));
        commonUtils.selectDropDownValue(commonUtils.findElementById(pb.collectionTypeDropdown),
                p.getProperty("collectionType"));
        commonUtils.selectDropDownValue(commonUtils.findElementById(pb.labNameDropdown),
                p.getProperty("labName"));
        commonUtils.selectDropDownValue(commonUtils.findElementById(pb.warehouseNameDropdown),
                p.getProperty("warehouseName"));
        commonUtils.enterValueInTextField(commonUtils.findElementById(pb.warehouseSkuField),
                "Warehouse SKU" + randomNumbers(3));
        commonUtils.enterValueInTextField(commonUtils.findElementById(pb.shippingWeightField), randomNumbers(3));
        commonUtils.selectDropDownValue(commonUtils.findElementById(pb.weightUnitDropdown),
                p.getProperty("weightUnit"));
        commonUtils.enterValueInTextField(commonUtils.findElementById(pb.startingUnitsOnHandField),
                randomNumbers(3));
        commonUtils.enterValueInTextField(commonUtils.findElementById(pb.reorderWhenBelowField),
                randomNumbers(4));

        //Supplier Information
        commonUtils.enterValueInTextField(commonUtils.findElementById(pb.kitNameField), "Kit" + randomString());
        commonUtils.enterValueInTextField(commonUtils.findElementById(pb.kitSupplierSkuField),
                "SKU" + randomNumbers(3));
        commonUtils.enterValueInTextField(commonUtils.findElementById(pb.kitCostPerUnitField),
                randomNumbers(3));
        commonUtils.selectDropDownValue(commonUtils.findElementById(pb.kitIncludedInShipmentDropdown),
                p.getProperty("kitIncludedInShipment"));
        commonUtils.selectDropDownValue(commonUtils.findElementById(pb.kitInboundShippingCostPaidByDropdown),
                p.getProperty("kitInboundShippingCostPaidBy"));
        commonUtils.enterValueInTextField(commonUtils.findElementById(pb.supplierProductNameField),
                "Supplier Product" + randomString());
        commonUtils.enterValueInTextField(commonUtils.findElementById(pb.productSupplierSkuField),
                "Product SKU" + randomNumbers(3));
        commonUtils.enterValueInTextField(commonUtils.findElementById(pb.productCostPerUnitField),
                randomNumbers(3));
        commonUtils.selectDropDownValue(commonUtils.findElementById(pb.productIncludedInShipmentDropdown),
                p.getProperty("productIncludedInShipment"));
        commonUtils.selectDropDownValue(commonUtils.findElementById(pb.productInboundShippingCostPaidByDropdown),
                p.getProperty("productInboundShippingCostPaidBy"));
        commonUtils.validateCheckbox(commonUtils.findElementById(pb.activeCheckbox));
        commonUtils.selectDropDownValue(commonUtils.findElementById(pb.activeDropdown), p.getProperty("active"));
        commonUtils.clickOnElement(commonUtils.findElementById(pb.saveButton), null);
    }
}
