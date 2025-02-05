package pageObjects;

import org.openqa.selenium.WebDriver;

public class ProductsAndBundlesPage extends BasePage {

    public ProductsAndBundlesPage(WebDriver driver) {
        super(driver);
    }

    public String addProductButton = "//a[normalize-space()='Add Product']";
    public String skuField = "product_sku"; //id
    public String productNameField = "(//input[@id='name'])[2]";
    public String typeDropdownField = "selectProductType"; //id
    public String gdtProductPriceField = "product_price"; //id
    public String accountProductPriceField = "own_account_product_price"; //id
    public String productPurchaseRequiredCheckbox = "prod_purc_req"; //id
    public String bulkShipmentAllowedCheckbox = "bulk_shipment"; //id
    public String individualShipmentAllowedCheckbox = "indiv_shipment"; //id
    public String educationReqWithTestPurchaseCheckbox = "obs_req_test_purc"; //id
    public String educationAllowedWithTestPurchaseCheckbox = "obs_alw_test_purc"; //id
    public String educationAllowedWithoutTestPurchaseCheckbox = "obs_alw_no_test_purc"; //id

    //Shipping Settings
    public String returnToLabCheckbox = "return_to_lab"; //id
    public String outboundShippingToAssigneeMaxTimeDropdown = "outbound_to_patient"; //id
    public String inboundShippingToLabMaxTime = "inbound_time"; //id
    public String vendorProductIdField = "vendor_product_id"; //id

    //Product Details
    public String collectionTypeDropdown = "collection_type"; //id
    public String labNameDropdown = "support_entity_id_lab"; //id
    public String warehouseNameDropdown = "support_entity_id"; //id
    public String warehouseSkuField = "support_entity_sku"; //id
    public String shippingWeightField = "weight"; //id
    public String weightUnitDropdown = "weight_unit"; //id
    public String startingUnitsOnHandField = "starting_units_on_hand"; //id
    public String reorderWhenBelowField = "reorder_when_below"; //id

    //Supplier Information
    public String kitNameField = "kit_name"; //id
    public String kitSupplierSkuField = "kit_sku"; //id
    public String kitCostPerUnitField = "kit_supplier_cost_per_unit"; //id
    public String kitIncludedInShipmentDropdown = "kit_shipment"; //id
    public String kitInboundShippingCostPaidByDropdown = "kit_inbound_shipment"; //id
    public String supplierProductNameField = "product_name"; //id
    public String productSupplierSkuField = "product_supplier_sku"; //id
    public String productCostPerUnitField = "product_supplier_cost_per_unit"; //id
    public String productIncludedInShipmentDropdown = "product_shipment"; //id
    public String productInboundShippingCostPaidByDropdown = "product_inbound_shipment"; //id
    public String activeCheckbox = "product_active"; //id
    public String activeDropdown = "active_value"; //id
    public String saveButton = "addProductForm"; //id
}
