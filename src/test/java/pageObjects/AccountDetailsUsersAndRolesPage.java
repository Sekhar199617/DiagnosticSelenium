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
	}
	
	@FindBy(xpath = "//input[@placeholder='Enter Name']")
	WebElement newUserNameField;
	
	@FindBy(xpath = "//div[@aria-owns='country-listbox']")
	WebElement mobileCountryCodeDropDown;
	
	@FindBy(xpath = "//span[@class='iti__country-name']")
	List<WebElement> countryList;
	
	@FindBy(xpath = "//input[@placeholder='Enter phone number']")
	WebElement phoneNumberField;
	
	@FindBy(xpath = "//input[@placeholder='Enter email address']")
	WebElement emailField;
	
	@FindBy(xpath = "//select[@id='userRole']")
	WebElement roleDropDown;
	
	@FindBy(xpath = "//select[@id='role']")
	WebElement userTypeDropDown;
	
	@FindBy(xpath = "(//input[@name='timezone_support'])[2]")
	WebElement userTimeZoneRadioButton;
	
	@FindBy(xpath = "//select[@name='timezone_id']")
	WebElement userDefaultTimeZoneDropDown;
	
	@FindBy(xpath = "//input[@id='immediately']")
	WebElement immediatelyRadioButton;
	
	@FindBy(xpath = "//input[@id='activeAccount']")
	WebElement activeCheckbox;
	
	@FindBy(xpath = "//button[@id='addUserForm']")
	WebElement saveButton;
	
	@FindBy(xpath = "//input[@id='provider_npi']")
	WebElement licenseIDField;
	
	@FindBy(xpath = "//textarea[@id='credentials']")
	WebElement credentialsField;
	
	@FindBy(xpath = "//input[@value='Company Test 1']")
	WebElement accountNameField;
	
	@FindBy(xpath = "//select[@id='unselected_bundles']")
	WebElement unselectedBundlesList;
	
	@FindBy(xpath = "//button[@onclick='addProduct()']")
	WebElement rightArrow;
	
	@FindBy(xpath = "//select[@id='selected_bundles']")
	WebElement selectedBundleField;
	
	@FindBy(xpath = "//div[@class='manager_privilege_div']/div/input")
	List<WebElement> additionalPriviligesCheckboxesList;
	
	@FindBy(xpath = "//div[@class='support_manager_privilege_div']/div/input")
	List<WebElement> supportManagerPriviligesCheckboxesList;
	
	@FindBy(xpath = "//h2[@class='swal2-title']")
	WebElement dialogueText;
	
	
	public void enterNewUserName(String userName) {
		newUserNameField.sendKeys(userName);
	}
	
	public void clickOnCountryDropDown() {
		mobileCountryCodeDropDown.click();
	}
	
	public void clickOnMobileCountryCode(String countryName) {
		commonUtils.clickOnMobileCountryCode(countryList, countryName);
	}
	
	public void enterPhoneNumber(String phoneNumber) {
		phoneNumberField.sendKeys(phoneNumber);
	}
	
	public void enterEmail(String email) {
		emailField.sendKeys(email);
	}
	
	public void selectRole(String role) {
		commonUtils.selectDropDownValue(roleDropDown, role);
	}
	
	public void selectUserType(String userType) {
		commonUtils.selectDropDownValue(userTypeDropDown, userType);
	}
	
	public void verifyShowAssigneeSessionTimeRadioButton() {
		commonUtils.validateRadioButton(userTimeZoneRadioButton);
	}
	
	public void selectUserDefaultTimeZone(String defaultTimeZone) {
		commonUtils.selectDropDownValue(userDefaultTimeZoneDropDown, defaultTimeZone);
	}
	
	public void verifySendLoginInformationRadioButton() {
		commonUtils.validateRadioButton(immediatelyRadioButton);
	}
	
	public void verifyActiveCheckbox() {
		commonUtils.validateCheckbox(activeCheckbox);
	}
	
	public void clickOnSave() {
		saveButton.click();
	}
	
	public void validateInputText(String accountName) {
		commonUtils.validateInputText(accountNameField, accountName);
	}
	
	public void enterLicenceID(String licenseID) {
		licenseIDField.sendKeys(licenseID);
	}
	
	public void enterCredentials(String credentials) {
		credentialsField.sendKeys(credentials);
	}
	
	public void selectUnselectedBundle(String unselectedBundle) {
		commonUtils.selectDropDownValue(unselectedBundlesList, unselectedBundle);
	}
	
	public void clickOnRightArrow() {
		rightArrow.click();
	}
	
	public void verifySelectedBundleText(String selectedBundle) {
		commonUtils.validateGetText(selectedBundleField, selectedBundle);
	}
	
	public void selectRandomAdditionalPriviligesCheckboxes() {
		commonUtils.selectRandomCheckboxes(additionalPriviligesCheckboxesList);
	}
	
	public void selectRandomSupportManagerPriviligesCheckboxes() {
		commonUtils.selectRandomCheckboxes(supportManagerPriviligesCheckboxesList);
	}
	
	public void verifyDialogueText(String expectedMessage) {
		commonUtils.validateGetText(dialogueText, expectedMessage);
	}

}
