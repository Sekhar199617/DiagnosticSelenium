package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.CommonUtils;

public class AddLocationPage extends BasePage {

	CommonUtils commonUtils;

	public AddLocationPage(WebDriver driver) {
		super(driver);
		commonUtils = new CommonUtils(driver);
	}
	
	@FindBy (xpath = "//button[@onclick='addAccountLocation(48)']")
	public WebElement addLocationButton;
	
	@FindBy (xpath = "//input[@id='location_name']")
	public WebElement locationNameField;

	@FindBy (xpath = "//select[@name='location_type']")
	public WebElement locationTypDropdown;
	
	@FindBy(xpath = "//input[@id='address']")
	public WebElement addressField;
	
	@FindBy(xpath = "//input[@id='address_2']")
	public WebElement address2Field;
	
	@FindBy(id = "country_id")
	public WebElement countryDropdown;

	@FindBy(xpath = "//select[@id='city_id']")
	public WebElement cityDropdown;

	@FindBy(xpath = "//select[@id='state_id']")
	public  WebElement stateDropdown;

	@FindBy(xpath = "//input[@id='cityInput']")
	public  WebElement cityInputField;
	
	@FindBy(xpath = "//input[@id='stateInput']")
	public WebElement stateInputField;

	@FindBy(xpath = "//input[@id='postal_code']")
	public WebElement postalCodeField;
	
	@FindBy(xpath = "//a[@id='addLocationSubmit']")
	public WebElement submitLocation;
	
	@FindBy(id = "(//select[contains(@class, 'week-days') and contains(@class, 'mon-1')])[1]")
	public WebElement mondayStartTimeDropdown;

	@FindBy(id = "(//select[contains(@class, 'week-days') and contains(@class, 'mon-2')])[1]")
	public WebElement mondayEndTimeDropdown;

	@FindBy(id = "//input[@class='form-check-input mon-checked']")
	public List<WebElement> checkboxes;

    @FindBy(xpath = "//input[@type='checkbox' and contains(@class, 'week-days')]")
    public List<WebElement> dayCheckboxes;

    @FindBy(xpath = "//button[contains(text(),'to M-F')]")
    public WebElement toMFButton;

    // Methods to interact with Hours of Operation
    public void selectHoursOfOperation(int dayIndex, String startTime, String endTime) {
        // Select start and end times based on index (0 = Monday, 1 = Tuesday, etc.)
        commonUtils.selectDropDownValue(startTimeDropdowns.get(dayIndex), startTime);
        commonUtils.selectDropDownValue(endTimeDropdowns.get(dayIndex), endTime);
    }

    public void applyToAllWeekdays() {
        // Click "to M-F" button
        toMFButton.click();
    }

	// Method to select a country
	public void selectCountry(String countryName) {
		if (countryName != null && !countryName.isEmpty()) {
			commonUtils.selectDropDownValue(countryDropdown, countryName);
		} else {
			throw new IllegalArgumentException("Country name cannot be null or empty.");
		}
	}

	// Method to handle City and State fields based on country
	public void handleCityAndState(String countryName, String cityName, String stateName) {
		if (countryName.equals("United States")) {
			// Select options from dropdowns for city and state
			commonUtils.selectDropDownValue(cityDropdown, cityName);
			commonUtils.selectDropDownValue(stateDropdown, stateName);
		} else {
			// Enter values into input fields for other countries
			cityInputField.clear();
			cityInputField.sendKeys(cityName);

			stateInputField.clear();
			stateInputField.sendKeys(stateName);
		}
	}
Select
	//input[@class='form-check-input mon-checked

}
