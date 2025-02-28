package pageObjects.AdminAccount.AccountDetailsModules;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.BasePage;
import utilities.CommonUtils;

import java.util.List;

public class UsersAndRolesPage extends BasePage {

	CommonUtils commonUtils;

	public UsersAndRolesPage(WebDriver driver) {
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
	public String userTypeDropDownField = "roleFilter"; //id

	public void performActionOnUser(String tableId, String userName, String actionText) {
		int attempts = 0;
		boolean actionPerformed = false;

		while (attempts < 2) {
			try {
				List<WebElement> rows = driver.findElements(By.xpath("//table[@id='" + tableId + "']/tbody/tr"));

				for (WebElement row : rows) {
					WebElement nameCell = row.findElement(By.xpath("./td[1]"));

					if (nameCell.getText().trim().equals(userName)) {
						System.out.println("Found user: " + nameCell.getText());

						WebElement actionsButton = row.findElement(By.xpath(".//button[contains(text(),'Actions')]"));
						actionsButton.click();

						WebElement actionOption = row.findElement(By.xpath("//a[normalize-space()='" + actionText + "']"));
						commonUtils.waitForElementToBeClickable(actionOption, 10);
						actionOption.click();

						actionPerformed = true;
						break; // Exit the loop once the action is completed
					}
				}

				// If we exit the loop successfully, break out of retry attempts
				if (actionPerformed) {
					break;
				}
			} catch (StaleElementReferenceException e) {
				System.out.println("Caught StaleElementReferenceException. Retrying...");
				attempts++;
			} catch (Exception e) {
				System.out.println("Exception occurred: " + e.getMessage());
				break;
			}
		}

		if (!actionPerformed) {
			System.out.println("Action could not be performed after 2 attempts.");
		}
	}
}