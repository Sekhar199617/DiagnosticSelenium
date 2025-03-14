package pageObjects.AdminAccount.AccountDetailsModules;
import org.openqa.selenium.WebDriver;
import pageObjects.BasePage;
import utilities.CommonUtils;

public class OrdersPage extends BasePage {

    CommonUtils commonUtils;

    public OrdersPage(WebDriver driver) {
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
    public String editButton = "//table[@id='orderTable']//tbody//tr//a[normalize-space()='Edit']";

}
