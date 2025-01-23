package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.CommonUtils;

public class AccountDetailsUsersAndRolesPage extends BasePage {
	
	CommonUtils commonUtils;

	public AccountDetailsUsersAndRolesPage(WebDriver driver) {
		super(driver);
	    this.commonUtils = new CommonUtils(driver);
	}
	
	@FindBy(xpath = "//input[@placeholder='Enter Name']")
	public WebElement newUserNameField;
	
	@FindBy(xpath = "//div[@aria-owns='country-listbox']")
	public WebElement mobileCountryCodeDropDown;
	
	@FindBy(xpath = "//span[@class='iti__country-name']")
	public List<WebElement> countryList;
	
	@FindBy(xpath = "//input[@placeholder='Enter phone number']")
	public WebElement phoneNumberField;
	
	@FindBy(xpath = "//input[@placeholder='Enter email address']")
	public WebElement emailField;
	
	@FindBy(xpath = "//select[@id='userRole']")
	public WebElement roleDropDown;
	
	@FindBy(xpath = "//select[@id='role']")
	public WebElement userTypeDropDown;
	
	@FindBy(xpath = "(//input[@name='timezone_support'])[2]")
	public WebElement userTimeZoneRadioButton;
	
	@FindBy(xpath = "//select[@name='timezone_id']")
	public WebElement userDefaultTimeZoneDropDown;
	
	@FindBy(xpath = "//input[@id='immediately']")
	public WebElement immediatelyRadioButton;
	
	@FindBy(xpath = "//input[@id='activeAccount']")
	public WebElement activeCheckbox;
	
	@FindBy(css = "#addUserForm")
	public WebElement saveButton;
	
	@FindBy(xpath = "//input[@id='provider_npi']")
	public WebElement licenseIDField;
	
	@FindBy(xpath = "//textarea[@id='credentials']")
	public WebElement credentialsField;
	
	@FindBy(xpath = "//input[@value='Company Test 1']")
	public WebElement accountNameField;
	
	@FindBy(xpath = "//select[@id='unselected_bundles']")
	public WebElement unselectedBundlesList;
	
	@FindBy(xpath = "//button[@onclick='addProduct()']")
	public WebElement rightArrow;
	
	@FindBy(xpath = "//select[@id='selected_bundles']")
	public WebElement selectedBundleField;
	
	@FindBy(xpath = "//div[@class='manager_privilege_div']/div/input")
	public List<WebElement> additionalPriviligesCheckboxesList;
	
	@FindBy(xpath = "//div[@class='support_manager_privilege_div']/div/input")
	public List<WebElement> supportManagerPriviligesCheckboxesList;
	
	@FindBy(xpath = "//h2[@class='swal2-title']")
	public WebElement dialogueText;

	@FindBy(xpath = "//button[normalize-space()='Ok']")
	public WebElement dialogueOkButton;

}
