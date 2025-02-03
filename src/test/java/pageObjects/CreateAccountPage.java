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

    public String clickCreateAccount= "//button[normalize-space()='Create Account']";
    public String  accountNameField  = "//input[@placeholder='Account Name']";
    public String  primaryContactNameField  = "//input[@placeholder='Account Admin Name']";
    public String  emailField = "//input[@placeholder='Email']";
    public String  phoneField = "//input[@placeholder='Phone']";
    public String accountTypeCompanyRadioButton = "//input[@id='company']";
    public String accountTypeIndividualRadioButton = "//input[@id='individual']";
    public String diagnosticMessagingSetDropdown = "//select[@id='messaging_id']";
    public String defaultIntakeFormDropdown = "//select[@id='default_intake_form']";
    public String billingCountryDropdown = "//select[@id='billing_country']";
    public String billingAddress_1Field = "//input[@name='billing_line_one']";
    public String billingAddress_2Field = "//input[@name='billing_line_two']";
    public String billingCityField = "//input[@name='city']";
    public String billingStateField = "//input[@name='state']";
    public String billingPostcodeField =  "//input[@name='postal_code']";
    public String demoAccountCheckbox = "//input[@name='demoUser']";
    public String saveNewAccount_button = "//*[@id='addAccountForm']";


}
