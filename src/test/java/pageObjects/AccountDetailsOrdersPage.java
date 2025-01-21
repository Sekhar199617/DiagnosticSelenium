package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.CommonUtils;

public class AccountDetailsOrdersPage extends BasePage {

    CommonUtils commonUtils;

    public AccountDetailsOrdersPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='provisioned_orders']")
    WebElement provisionedOrdersRadioButton;

    @FindBy(xpath = "//a[@data-bs-toggle='modal'][normalize-space()='Add']")
    WebElement addText;

    @FindBy(xpath = "//select[@id='orderType']")
    WebElement purchasableBundleDropDown;

    @FindBy(xpath = "//select[@id='shipping_options']")
    WebElement availableShippingOptions;

    @FindBy(xpath = "//select[@id='variations']")
    WebElement availableVariationsOptions;

    @FindBy(xpath = "//select[@id='noOfUnits']")
    WebElement noOfUnitsDropDown;

    @FindBy(xpath = "//input[@id='pricePerUnit']")
    WebElement pricePerUnit;

    @FindBy(xpath = "//input[@id='shipping_pricePerUnit']")
    WebElement shippingAmountPerUnit;

    @FindBy(xpath = "//input[@id='estimated_tax']")
    WebElement estimatedTaxField;

    @FindBy(xpath = "//button[@id='add_user_purchase']")
    WebElement addOrderButton;



    public void selectProvisionedOrdersRadioButton() {
        if (!provisionedOrdersRadioButton.isSelected()) {
            // If the radio button is not selected, click on the element
            provisionedOrdersRadioButton.click();
        }
    }

    public void clickOnAdd() {
        addText.click();
    }

    public void selectPurchasableBundleValue(String purchasableBundleValue) {
        commonUtils.selectDropDownValue(purchasableBundleDropDown, purchasableBundleValue);
    }

    public void selectAvailableShippingOptionsValue(String availableShippingOptionsValue) {
        commonUtils.selectDropDownValue(availableShippingOptions, availableShippingOptionsValue);
    }

    public void selectAvailableVariationsOptionsValue(String availableVariationsOptionsValue) {
        commonUtils.selectDropDownValue(availableVariationsOptions, availableVariationsOptionsValue);
    }

    public void selectNoOfUnitsValue(String noOfUnitsValue) {
        commonUtils.selectDropDownValue(noOfUnitsDropDown, noOfUnitsValue);
    }

    public void enterPricePerUnit(String pricePerUnitValue) {
        pricePerUnit.sendKeys(pricePerUnitValue);
    }

    public void enterShippingAmountPerUnit(String shippingAmountPerUnitValue) {
        shippingAmountPerUnit.sendKeys(shippingAmountPerUnitValue);
    }

    public void enterEstimatedTax(String estimatedTax) {
        estimatedTaxField.clear();
        estimatedTaxField.sendKeys(estimatedTax);
    }

    public void clickOnAddOrderButton() {
        addOrderButton.click();
    }
}
