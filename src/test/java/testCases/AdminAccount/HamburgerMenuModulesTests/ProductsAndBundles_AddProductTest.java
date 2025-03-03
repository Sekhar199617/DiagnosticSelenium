package testCases.AdminAccount.HamburgerMenuModulesTests;

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

public class ProductsAndBundles_AddProductTest extends BaseClass {

    @Test
    public void verifyAddProduct() throws InterruptedException {

        try {
            logger.info("****** Starting Add Product Test ******");

            DashboardPage dashboardPage = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            UsersAndRolesPage usersAndRolesPage = new UsersAndRolesPage(driver);
            ProductsAndBundlesPage pb = new ProductsAndBundlesPage(driver);

            loadTestData(
                    "./testData/adminLoginData.json",
                    "./testData/hamburgerMenuModulesData.json"
            );

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            dashboardPage.selectHamburgerTab("Products & Bundles");

            commonUtils.clickOnElement(commonUtils.findElementByXpath(pb.addProductButton), null);
            commonUtils.enterValueInTextField(commonUtils.findElementById(pb.skuField), randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(pb.productNameField), randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementById(pb.typeDropdownField), "Product");
            commonUtils.enterValueInTextField(commonUtils.findElementById(pb.gdtProductPriceField), randomNumbers(3));
            commonUtils.enterValueInTextField(commonUtils.findElementById(pb.accountProductPriceField),
                    randomNumbers(3));

            //PRODUCT OPTIONS CHECKBOXES :
            WebElement productPurchaseRequiredCheckbox = commonUtils.findElementById(
                    pb.productPurchaseRequiredCheckbox);
            WebElement bulkShipmentAllowedCheckbox = commonUtils.findElementById(pb.bulkShipmentAllowedCheckbox);
            WebElement individualShipmentAllowedCheckbox = commonUtils.findElementById(
                    pb.individualShipmentAllowedCheckbox );

            commonUtils.scrollToElement(productPurchaseRequiredCheckbox);
            commonUtils.selectCheckbox(productPurchaseRequiredCheckbox);

            //EDUCATION / OBSERVATION OPTIONS CHECKBOXES :
            WebElement educationReqWithTestPurchaseCheckbox = commonUtils.findElementById(
                    pb.educationReqWithTestPurchaseCheckbox );
            WebElement educationAllowedWithTestPurchaseCheckbox = commonUtils.findElementById(
                    pb.educationAllowedWithTestPurchaseCheckbox );
            WebElement educationAllowedWithoutTestPurchaseCheckbox = commonUtils.findElementById(
                    pb.educationAllowedWithoutTestPurchaseCheckbox);

            //SCENARIO 1 :
            /* 1. When Product Purchase Required checkbox is enabled then anyone one of bulk or individual checkbox
                  should select. Education/Observation allowed without test purchase checkbox should disable
               2. When Education/Observation required with test purchase checkbox is selected then other two
                  checkboxes should disable.
             */
            boolean isProductPurchaseRequiredCheckboxEnabled = productPurchaseRequiredCheckbox.isEnabled();

            if(isProductPurchaseRequiredCheckboxEnabled) {
                String validationText = commonUtils.getTextFromElement(commonUtils.findElementById(
                        pb.productOptionsValidationText));
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
            commonUtils.clickOnElement(commonUtils.findElementByCssSelector(pb.setExclusionsButton),
                    "Set Exclusions");
            commonUtils.enterValueInTextField(commonUtils.findElementByCssSelector(
                    pb.exclusionsSearchField), getTestData("exclusionCountry"));
            Thread.sleep(2000);
            commonUtils.selectDropDownValueWithClick(commonUtils.findElementsByCssSelector(
                    pb.countriesList), getTestData("exclusionCountry"));
            commonUtils.clickOnElement(commonUtils.findElementById(pb.exclusionsSaveButton), "Save");

            //SHIPPING SETTINGS
            commonUtils.selectDropDownValue(commonUtils.findElementById(pb.outboundShippingToAssigneeMaxTimeDropdown),
                    getTestData("outboundShippingToAssigneeMaximumTime"));

            //PRODUCT DETAILS
            commonUtils.enterValueInTextField(commonUtils.findElementById(pb.vendorProductIdField),
                    "Product" + randomNumbers(5));
            commonUtils.selectDropDownValue(commonUtils.findElementById(pb.warehouseNameDropdown),
                    getTestData("warehouseName"));
            commonUtils.enterValueInTextField(commonUtils.findElementById(pb.warehouseSkuField),
                    "Warehouse SKU" + randomNumbers(3));
            commonUtils.enterValueInTextField(commonUtils.findElementById(pb.shippingWeightField),
                    randomNumbers(3));
            commonUtils.selectDropDownValue(commonUtils.findElementById(pb.weightUnitDropdown),
                    getTestData("weightUnit"));
            WebElement yesChangeButton = commonUtils.findElementByXpath(pb.yesChangeButton);
            try {
                yesChangeButton.click();
            } catch (TimeoutException e) {
                System.out.println("Button not found or not clickable.");
            }
            commonUtils.enterValueInTextField(commonUtils.findElementById(pb.startingUnitsOnHandField),
                    randomNumbers(3));
            commonUtils.enterValueInTextField(commonUtils.findElementById(pb.reorderWhenBelowField),
                    randomNumbers(4));

            //SUPPLIER INFORMATION
            commonUtils.scrollToBottomAndClick(commonUtils.findElementById(pb.supplierProductNameField));
            commonUtils.enterValueInTextField(commonUtils.findElementById(pb.supplierProductNameField),
                    "Product " + randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementById(pb.productSupplierSkuField),
                    "Product SKU" + randomNumbers(3));
            commonUtils.enterValueInTextField(commonUtils.findElementById(pb.productCostPerUnitField),
                    randomNumbers(3));
            commonUtils.selectDropDownValue(commonUtils.findElementById(pb.productIncludedInShipmentDropdown),
                    getTestData("productIncludedInShipment"));
            commonUtils.selectDropDownValue(commonUtils.findElementById(pb.productInboundShippingCostPaidByDropdown),
                    getTestData("productInboundShippingCostPaidBy"));
            commonUtils.validateCheckbox(commonUtils.findElementById(pb.activeCheckbox));
            commonUtils.selectDropDownValue(commonUtils.findElementById(pb.activeDropdown), getTestData("active"));

            LocalDate today = LocalDate.now();
            LocalDate tomorrow = today.plusDays(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String formattedToday = today.format(formatter);
            String formattedTomorrow = tomorrow.format(formatter);

            if (getTestData("active").equalsIgnoreCase("Beginning on")) {
                commonUtils.enterValueInTextField(commonUtils.findElementById(pb.dateField), formattedToday);
                System.out.println("Validating with today's date: " + formattedToday);
            } else if (getTestData("active").equalsIgnoreCase("Until")) {
                commonUtils.enterValueInTextField(commonUtils.findElementById(pb.dateField), formattedTomorrow);
                System.out.println("Validating with tomorrow's date: " + formattedTomorrow);
            } else if (getTestData("active").equalsIgnoreCase("Immediately")) {
                System.out.println("This option doesn't have a Date field");
            }

            commonUtils.clickOnElement(commonUtils.findElementById(pb.saveButton), "Save");

            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(
                            usersAndRolesPage.dialogueText),
                    getTestData("addProductPromptWindowText"),
                    commonUtils.findElementByXpath(usersAndRolesPage.dialogueOkButton));
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Add Product Test ******");

    }
}
