package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.CommonUtils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ProductsAndBundlesPage extends BasePage {

    CommonUtils commonUtils;

    public ProductsAndBundlesPage(WebDriver driver) {
        super(driver);
        this.commonUtils = new CommonUtils(driver);
    }

    public String bundlesTab = "bundle-tab"; //id
    public String addBundleButton = "//a[normalize-space()='Add Bundle']";
    public String purchaseBundleNameField = "bundle_name"; //id
    public String bundleTypeDropdown = "bundle_type"; //id
    public String addButton = "//button[normalize-space()='Add']";
    public String addToBundleButton = "//button[normalize-space()='Add to Bundle']";
    public String bundleSKUField = "bundle_sku"; //id
    public String vendorBundleId = "//input[@id='vendor_bundle_id']";
    public String gdtBundlePrice = "//input[@id='standard_price']";
    public String accountBundlePrice = "//input[@id='own_account_bundle_price']";
    public String bundleCostGdt = "//input[@id='bundle_cost_to_gdt']";
    public String shippingWeight = "//input[@id='shipping_weight']";
    public String prepareOutboundShippingLabelCheckbox = "//input[@name='prepare_outbound_shipping_label']";
    public String uploadBundleImage = "//button[normalize-space()='Upload']";
    public String uploadDragButton = "//button[@id='upload_images']";
    public String saveButton = "//button[@id='addProductForm']";

    public void selectMultipleRandomProducts(String tableId, int productCount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='" + tableId + "']/tbody/tr")));

        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='" + tableId + "']/tbody/tr"));

        if (rows.isEmpty()) {
            System.out.println("No products found in the table.");
            return;
        }

        Random random = new Random();
        int count = Math.min(productCount, rows.size());
        Set<Integer> selectedIndices = new HashSet<>();

        while (selectedIndices.size() < count) {
            int randomIndex = random.nextInt(rows.size());

            if (!selectedIndices.contains(randomIndex)) {
                selectedIndices.add(randomIndex);
                WebElement selectedRow;

                try {
                    selectedRow = rows.get(randomIndex);
                    WebElement productCell = selectedRow.findElement(By.xpath("./td[1]"));

                    js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", productCell);
                    wait.until(ExpectedConditions.elementToBeClickable(productCell));

                    System.out.println("Selected product: " + productCell.getText());
                    productCell.click();

                    Thread.sleep(2000); // Adding a short delay to allow page stability
                } catch (StaleElementReferenceException e) {
                    System.out.println("Stale element encountered, retrying selection.");
                    rows = driver.findElements(By.xpath("//table[@id='" + tableId + "']/tbody/tr")); // Refresh the list
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
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
    public String productOptionsValidationText = "error_msg_prod"; //id
    public void scrollUntilNewContentLoads() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            long lastHeight = (long) js.executeScript("return document.body.scrollHeight");

            while (true) {
                js.executeScript("window.scrollBy(0, window.innerHeight);"); // Scroll down one viewport height
                Thread.sleep(1000); // Allow time for content to load

                long newHeight = (long) js.executeScript("return document.body.scrollHeight");

    //Availability
    public String setExclusionsButton = "button[id='openExclusionsBtn']"; //css
    public String exclusionsSearchField = "input[id='geoSearchInput']";
    public String countriesList = "[id='geoResults'] strong";
    public String exclusionsSaveButton = "saveExclusionsBtn"; //id
                // Stop scrolling if no new content is loaded
                if (newHeight == lastHeight) {
                    break;
                }
                lastHeight = newHeight;
            }
        } catch (Exception e) {
            System.err.println("Error while scrolling: " + e.getMessage());
        }
    }

    //Shipping Settings
    public String returnToLabCheckbox = "return_to_lab"; //id
    public String outboundShippingToAssigneeMaxTimeDropdown = "outbound_to_patient"; //id
    public String inboundShippingToLabMaxTime = "inbound_time"; //id
    public String vendorProductIdField = "vendor_product_id"; //id
    public void uploadImage() throws AWTException, InterruptedException {
        // Click on the "Upload" button to open the file dialog
        WebElement uploadButton = commonUtils.findElementByXpath(uploadDragButton);
        uploadButton.click();
        Thread.sleep(2000); // Wait for the dialog to open

    //Product Details
    public String collectionTypeDropdown = "collection_type"; //id
    public String labNameDropdown = "support_entity_id_lab"; //id
    public String warehouseNameDropdown = "support_entity_id"; //id
    public String warehouseSkuField = "support_entity_sku"; //id
    public String shippingWeightField = "weight"; //id
    public String weightUnitDropdown = "weight_unit"; //id
    public String yesChangeButton = "//button[contains(text(),'Yes, Change it!')]";
    public String startingUnitsOnHandField = "starting_units_on_hand"; //id
    public String reorderWhenBelowField = "reorder_when_below"; //id
        String imageFilePath = Paths.get("src/test/resources/backImage.png").toAbsolutePath().toString();
        System.out.println("Resolved file path: " + imageFilePath);

        // Use Robot to paste the file path and press Enter
        Robot robot = new Robot();
        robot.delay(1000);

        // Copy the file path to clipboard
        StringSelection selection = new StringSelection(imageFilePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_L);
        robot.keyRelease(KeyEvent.VK_L);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(500);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(500);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Thread.sleep(2000);
    }

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
    public String dateField = "datetimepicker-input"; //id
    public String activeDropdownOptions = "select[id='active_value'] options"; //css
    public String saveButton = "addProductForm"; //id
}
