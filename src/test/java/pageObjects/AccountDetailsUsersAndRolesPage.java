package pageObjects;
import org.openqa.selenium.WebDriver;
import utilities.CommonUtils;

public class AccountDetailsUsersAndRolesPage extends BasePage {

	CommonUtils commonUtils;

	public AccountDetailsUsersAndRolesPage(WebDriver driver) {
		super(driver);
		this.commonUtils = new CommonUtils(driver);
	}

	public String newUserNameField = "//input[@placeholder='Enter Name']";
	public String mobileCountryCodeDropDown = "//div[@aria-owns='country-listbox']";
	public String countryList = "//span[@class='iti__country-name']";
	public String phoneNumberField = "//input[@placeholder='Enter phone number']";
	public String emailField = "//input[@placeholder='Enter email address']";
	public String roleDropDown = "//select[@id='userRole']";
	public String userTypeDropDown = "//select[@id='role']";
	public String userTimeZoneRadioButton = "(//input[@name='timezone_support'])[2]";
	public String userDefaultTimeZoneDropDown = "//select[@name='timezone_id']";
	public String immediatelyRadioButton = "//input[@id='immediately']";
	public String activeCheckbox = "//input[@id='activeAccount']";
	public String saveButton = "#addUserForm";
	public String licenseIDField = "//input[@id='provider_npi']";
	public String credentialsField = "//textarea[@id='credentials']";
	public String accountNameField = "//input[@value='Company Test 1']";
	public String bundlesNotAttachedField = "//select[@id='unselected_bundles']";
	public String rightArrow = "//button[@onclick='addProduct()']";
	public String selectedBundleField = "selected_bundles";
	public String additionalPriviligesCheckboxesList = "//div[@class='manager_privilege_div']/div/input";
	public String supportManagerPriviligesCheckboxesList = "//div[@class='support_manager_privilege_div']/div/input";
	public String dialogueText = "//h2[@class='swal2-title']";
	public String dialogueOkButton = "//button[normalize-space()='Ok']";

}