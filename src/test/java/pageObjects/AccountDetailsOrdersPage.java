package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.CommonUtils;

public class AccountDetailsOrdersPage extends BasePage {

    CommonUtils commonUtils;

    public AccountDetailsOrdersPage(WebDriver driver) {
        super(driver);
        this.commonUtils = new CommonUtils(driver);
    }

    @FindBy(xpath = "//input[@id='provisioned_orders']")
    public WebElement provisionedOrdersRadioButton;

    @FindBy(xpath = "//a[@data-bs-toggle='modal'][normalize-space()='Add']")
    public WebElement addText;

    @FindBy(xpath = "//select[@id='orderType']")
    public WebElement purchasableBundleDropDown;

    @FindBy(xpath = "//select[@id='shipping_options']")
    public WebElement availableShippingOptions;

    @FindBy(xpath = "//select[@id='variations']")
    public WebElement availableVariationsOptions;

    @FindBy(xpath = "//select[@id='noOfUnits']")
    public WebElement noOfUnitsDropDown;

    @FindBy(xpath = "//input[@id='pricePerUnit']")
    public WebElement pricePerUnit;

    @FindBy(xpath = "//input[@id='shipping_pricePerUnit']")
    public WebElement shippingAmountPerUnit;

    @FindBy(xpath = "//input[@id='estimated_tax']")
    public WebElement estimatedTaxField;

    @FindBy(xpath = "//button[@id='add_user_purchase']")
    public WebElement addOrderButton;

}
