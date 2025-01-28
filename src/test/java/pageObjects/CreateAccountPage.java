package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.CommonUtils;

public class CreateAccountPage extends  BasePage{

    CommonUtils commonUtils;
    public  CreateAccountPage(WebDriver driver) {
        super(driver);
        commonUtils = new CommonUtils(driver);
    }
    @FindBy(xpath = "//button[normalize-space()='Create Account']")
    public WebElement clickCreateAccount;

    @FindBy (xpath = "//input[@placeholder='Account Name']")
    public WebElement  accountNameField;

    @FindBy (xpath = "//input[@placeholder='Account Admin Name']")
    public WebElement  primaryContactNameField;

    @FindBy (xpath = "//input[@placeholder='Email']")
    public WebElement  emailField;

    @FindBy (xpath = "//input[@placeholder='Phone']")
    public WebElement  phoneField;

    @FindBy (xpath = "//input[@id='company']")
    public WebElement accountTypeCompanyRadioButton;

    @FindBy (xpath = "//input[@id='individual']")
    public WebElement accountTypeIndividualRadioButton;

    @FindBy (xpath = "//select[@id='messaging_id']")
    public WebElement diagnosticMessagingSetDropdown;

    @FindBy (xpath = "//select[@id='default_intake_form']")
    public WebElement defaultIntakeFormDropdown;

    @FindBy (xpath = "//select[@id='billing_country']")
    public WebElement billingCountryDropdown;

    @FindBy (xpath = "//input[@name='billing_line_one']")
    public WebElement billingAddress_1Field;

    @FindBy (xpath = "//input[@name='billing_line_two']")
    public WebElement billingAddress_2Field;

    @FindBy (xpath = "//input[@name='city']")
    public WebElement billingCityField;

    @FindBy (xpath = "//input[@name='state']")
    public WebElement billingStateField;

    @FindBy (xpath =  "//input[@name='postal_code']")
    public WebElement billingPostcodeField;

    @FindBy (xpath = "//input[@name='demoUser']")
    public WebElement demoAccountCheckbox;

    @FindBy (xpath = "//*[@id='addAccountForm']")
    public WebElement saveNewAccount_button;


}
