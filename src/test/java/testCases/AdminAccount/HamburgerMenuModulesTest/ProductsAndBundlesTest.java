package testCases.AdminAccount.HamburgerMenuModulesTest;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.AccountDetailsModules.UsersAndRolesPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import pageObjects.AdminAccount.HamburgerMenuModules.ProductsAndBundlesPage;
import testBase.BaseClass;
import utilities.CommonUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProductsAndBundlesTest extends BaseClass {

    @Test
    public void verifyAddProduct() {

        logger.info("****** Starting Add Product Test ******");
        try {
            DashboardPage dashboardPage = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            ProductsAndBundlesPage productsAndBundlesPage = new ProductsAndBundlesPage(driver);

            String loginJsonPath = "./testData//adminLoginData.json";
            loadTestData(loginJsonPath);

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            String hamburgerMenuModulesDataJsonPath = "./testData//hamburgerMenuModulesData.json";
            loadTestData(hamburgerMenuModulesDataJsonPath);

            dashboardPage.selectHamburgerTab("Products & Bundles");

            commonUtils.clickOnElement(commonUtils.findElementByXpath(productsAndBundlesPage.addProductButton),
                    null);
            commonUtils.enterValueInTextField(commonUtils.findElementById(productsAndBundlesPage.skuField),
                    randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(productsAndBundlesPage.productNameField),
                    randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementById(productsAndBundlesPage.typeDropdownField),
                    "Product");
            commonUtils.enterValueInTextField(commonUtils.findElementById(productsAndBundlesPage.gdtProductPriceField),
                    randomNumbers(3));
            commonUtils.enterValueInTextField(commonUtils.findElementById(productsAndBundlesPage.accountProductPriceField),
                    randomNumbers(3));

            //PRODUCT OPTIONS CHECKBOXES :
            WebElement productPurchaseRequiredCheckbox = commonUtils.findElementById(
                    productsAndBundlesPage.productPurchaseRequiredCheckbox);
            WebElement bulkShipmentAllowedCheckbox = commonUtils.findElementById(
                    productsAndBundlesPage.bulkShipmentAllowedCheckbox);
            WebElement individualShipmentAllowedCheckbox = commonUtils.findElementById(
                    productsAndBundlesPage.individualShipmentAllowedCheckbox);

            commonUtils.scrollToElement(productPurchaseRequiredCheckbox);
            commonUtils.selectCheckbox(productPurchaseRequiredCheckbox);

            //EDUCATION / OBSERVATION OPTIONS CHECKBOXES :
            WebElement educationReqWithTestPurchaseCheckbox = commonUtils.findElementById(
                    productsAndBundlesPage.educationReqWithTestPurchaseCheckbox );
            WebElement educationAllowedWithTestPurchaseCheckbox = commonUtils.findElementById(
                    productsAndBundlesPage.educationAllowedWithTestPurchaseCheckbox );
            WebElement educationAllowedWithoutTestPurchaseCheckbox = commonUtils.findElementById(
                    productsAndBundlesPage.educationAllowedWithoutTestPurchaseCheckbox);

            //SCENARIO 1 :
            /* 1. When Product Purchase Required checkbox is enabled then anyone one of bulk or individual checkbox
                  should select. Education/Observation allowed without test purchase checkbox should disable
               2. When Education/Observation required with test purchase checkbox is selected then other two
                  checkboxes should disable.
             */
            boolean isProductPurchaseRequiredCheckboxEnabled = productPurchaseRequiredCheckbox.isEnabled();

            if(isProductPurchaseRequiredCheckboxEnabled) {
                String validationText = commonUtils.getTextFromElement(commonUtils.findElementById(
                        productsAndBundlesPage.productOptionsValidationText));
                Assert.assertEquals(validationText, getTestData("productOptionsValidationText"));

                boolean isEducationAllowedWithoutTestPurchaseCheckboxEnabled =
                        educationAllowedWithoutTestPurchaseCheckbox.isEnabled();
                Assert.assertFalse(isEducationAllowedWithoutTestPurchaseCheckboxEnabled,
                        "Education Allowed Without Test Purchase Checkbox is Disabled");
            }

            commonUtils.selectCheckbox(bulkShipmentAllowedCheckbox);
            commonUtils.selectCheckbox(individualShipmentAllowedCheckbox);
            commonUtils.selectCheckbox(educationReqWithTestPurchaseCheckbox);

            boolean isEducationReqWithTestPurchaseCheckboxEnabled = educationReqWithTestPurchaseCheckbox.isEnabled();

            if(isEducationReqWithTestPurchaseCheckboxEnabled) {
                boolean isEducationAllowedWithTestPurchaseCheckboxEnabled =
                        educationAllowedWithTestPurchaseCheckbox.isEnabled();
                Assert.assertFalse(isEducationAllowedWithTestPurchaseCheckboxEnabled,
                        "Education Allowed With Test Purchase Checkbox Disabled");

                boolean isEducationAllowedWithoutTestPurchaseCheckboxEnabled =
                        educationAllowedWithoutTestPurchaseCheckbox.isEnabled();
                Assert.assertFalse(isEducationAllowedWithoutTestPurchaseCheckboxEnabled,
                        "Education Allowed Without Test Purchase Checkbox is Disabled");
            }

            //AVAILABILITY
            commonUtils.clickOnElement(commonUtils.findElementByCssSelector(productsAndBundlesPage.setExclusionsButton),
                    "Set Exclusions");
            commonUtils.enterValueInTextField(commonUtils.findElementByCssSelector(
                    productsAndBundlesPage.exclusionsSearchField), getTestData("exclusionCountry"));
            Thread.sleep(2000);
            commonUtils.selectDropDownValueWithClick(commonUtils.findElementsByCssSelector(
                    productsAndBundlesPage.countriesList), getTestData("exclusionCountry"));
            commonUtils.clickOnElement(commonUtils.findElementById(productsAndBundlesPage.exclusionsSaveButton),
                    "Save");

            //SHIPPING SETTINGS
            commonUtils.selectDropDownValue(commonUtils.findElementById(
                    productsAndBundlesPage.outboundShippingToAssigneeMaxTimeDropdown),
                    getTestData("outboundShippingToAssigneeMaximumTime")
            );

            //PRODUCT DETAILS
            commonUtils.enterValueInTextField(commonUtils.findElementById(productsAndBundlesPage.vendorProductIdField),
                    "Product" + randomNumbers(5));
            commonUtils.selectDropDownValue(commonUtils.findElementById(productsAndBundlesPage.warehouseNameDropdown),
                    getTestData("warehouseName"));
            commonUtils.enterValueInTextField(commonUtils.findElementById(productsAndBundlesPage.warehouseSkuField),
                    "Warehouse SKU" + randomNumbers(3));
            commonUtils.enterValueInTextField(commonUtils.findElementById(productsAndBundlesPage.shippingWeightField),
                    randomNumbers(3));
            commonUtils.selectDropDownValue(commonUtils.findElementById(productsAndBundlesPage.weightUnitDropdown),
                    getTestData("weightUnit"));
            WebElement yesChangeButton = commonUtils.findElementByXpath(productsAndBundlesPage.yesChangeButton);
            try {
                yesChangeButton.click();
            } catch (TimeoutException e) {
                System.out.println("Button not found or not clickable.");
            }
            commonUtils.enterValueInTextField(commonUtils.findElementById(productsAndBundlesPage.startingUnitsOnHandField),
                    randomNumbers(3));
            commonUtils.enterValueInTextField(commonUtils.findElementById(productsAndBundlesPage.reorderWhenBelowField),
                    randomNumbers(4));

            //SUPPLIER INFORMATION
            commonUtils.scrollToBottomAndClick(commonUtils.findElementById(productsAndBundlesPage.supplierProductNameField));
            commonUtils.enterValueInTextField(commonUtils.findElementById(productsAndBundlesPage.supplierProductNameField),
                    "Product " + randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementById(productsAndBundlesPage.productSupplierSkuField),
                    "Product SKU" + randomNumbers(3));
            commonUtils.enterValueInTextField(commonUtils.findElementById(productsAndBundlesPage.productCostPerUnitField),
                    randomNumbers(3));
            commonUtils.selectDropDownValue(commonUtils.findElementById(
                    productsAndBundlesPage.productIncludedInShipmentDropdown),
                    getTestData("productIncludedInShipment"));
            commonUtils.selectDropDownValue(commonUtils.findElementById(
                    productsAndBundlesPage.productInboundShippingCostPaidByDropdown),
                    getTestData("productInboundShippingCostPaidBy"));
            commonUtils.validateCheckbox(commonUtils.findElementById(productsAndBundlesPage.activeCheckbox));
            commonUtils.selectDropDownValue(commonUtils.findElementById(productsAndBundlesPage.activeDropdown),
                    getTestData("active"));

            LocalDate today = LocalDate.now();
            LocalDate tomorrow = today.plusDays(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String formattedToday = today.format(formatter);
            String formattedTomorrow = tomorrow.format(formatter);

            if (getTestData("active").equalsIgnoreCase("Beginning on")) {
                commonUtils.enterValueInTextField(commonUtils.findElementById(productsAndBundlesPage.dateField),
                        formattedToday);
                System.out.println("Validating with today's date: " + formattedToday);
            } else if (getTestData("active").equalsIgnoreCase("Until")) {
                commonUtils.enterValueInTextField(commonUtils.findElementById(productsAndBundlesPage.dateField),
                        formattedTomorrow);
                System.out.println("Validating with tomorrow's date: " + formattedTomorrow);
            } else if (getTestData("active").equalsIgnoreCase("Immediately")) {
                System.out.println("This option doesn't have a Date field");
            }

            commonUtils.clickOnElement(commonUtils.findElementById(productsAndBundlesPage.saveButton), "Save");

            UsersAndRolesPage au = new UsersAndRolesPage(driver);
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(au.dialogueText),
                    getTestData("addProductPromptWindowText"),
                    commonUtils.findElementByXpath(au.dialogueOkButton));
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Add Product Test ******");
    }
}