package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.CommonUtils;

import java.util.List;

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
	public String userTypeDropdown = "//select[@id='roleFilter']";
	public String chooseFileButton = "//input[@name='csvfile']";
	public String uploadCsvExcelButton = "//button[normalize-space()='Upload CSV/Excel']";
	public String uploadCsvButton = "//button[@name='submit1']";
	public String assignBundleButton = "//button[@id='assign_bundle_btn']";
	public String assigneeType = "//select[@id='assignment_type']";
	public String experienceAssigneeUploadDropdown = "//select[@id='observation_experience']";
	public String numberToAssignCompleteUploadButton = "//button[@id='complete_upload']";

	public void performActionOnUser(String tableId, String userName, String actionText) {
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='" + tableId + "']/tbody/tr"));

		for (WebElement row : rows) {
			WebElement nameCell = row.findElement(By.xpath("./td[1]"));

			if (nameCell.getText().trim().equals(userName)) {
				System.out.println("Found user: " + nameCell.getText());

				WebElement actionsButton = row.findElement(By.xpath(".//button[contains(text(),'Actions')]"));
				actionsButton.click();

				WebElement actionOption = row.findElement(By.xpath(".//a[contains(text(),'" + actionText + "')]"));
				actionOption.click();
				break;
			}
		}

	}

	public String userTypeDropdown = "//select[@id='roleFilter']";
	public String chooseFileButton = "//input[@name='csvfile']";
	public String uploadCsvExcelButton = "//button[normalize-space()='Upload CSV/Excel']";
	public String uploadCsvButton = "//button[@name='submit1']";
	public String assignBundleButton = "//button[@id='assign_bundle_btn']";
	public String assigneeType = "//select[@id='assignment_type']";
	public String experienceAssigneeUploadDropdown = "//select[@id='observation_experience']";
	public String numberToAssignCompleteUploadButton = "//button[@id='complete_upload']";
	public String userUploadValidationMessage = "//h2[@class='swal2-title']";
	public String userUploadOkButton = "//button[normalize-space()='Ok']";

}