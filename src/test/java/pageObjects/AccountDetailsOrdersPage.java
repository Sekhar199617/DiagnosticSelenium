package pageObjects;
import org.openqa.selenium.WebDriver;
import utilities.CommonUtils;

public class AccountDetailsOrdersPage extends BasePage {

    CommonUtils commonUtils;

    public AccountDetailsOrdersPage(WebDriver driver) {
        super(driver);
        this.commonUtils = new CommonUtils(driver);
    }

    public String provisionedOrdersRadioButton = "//input[@id='provisioned_orders']";
    public String addText = "//a[@data-bs-toggle='modal'][normalize-space()='Add']";
    public String purchasableBundleDropDown = "//select[@id='orderType']";
    public String availableShippingOptions = "//select[@id='shipping_options']";
    public String availableVariationsOptions = "//select[@id='variations']";
    public String noOfUnitsDropDown = "//select[@id='noOfUnits']";
    public String pricePerUnit = "//input[@id='pricePerUnit']";
    public String shippingAmountPerUnit = "//input[@id='shipping_pricePerUnit']";
    public String estimatedTaxField = "//input[@id='estimated_tax']";
    public String addOrderButton = "//button[@id='add_user_purchase']";

}
