package pageObjects.AccountDetailsModules.AdminAccount.Locations;

import org.openqa.selenium.WebDriver;

import pageObjects.BasePage;
import utilities.CommonUtils;

public class LocationsPage extends BasePage {

    CommonUtils commonUtils;

    public LocationsPage(WebDriver driver) {
        super(driver);
        commonUtils = new CommonUtils(driver);
    }

    public String addLocationButton = "//button[@onclick='addAccountLocation(48)']";
    public String validateAccountName = "(//div[@class='col-md'])[1]";
    public String locationNameField = "//input[@id='location_name']";
    public String locationTypDropdown = "//select[@name='location_type']";
    public String addressField = "//input[@id='address']";
    public String address2Field = "//input[@id='address_2']";
    public String countryDropdown = "//select[@name='country_id']";
    public String cityDropdown = "//select[@id='city_id']";
    public String stateDropdown = "//select[@id='state_id']";
    public String cityInputField = "//input[@id='cityInput']";
    public String stateInputField = "//input[@id='stateInput']";
    public String postalCodeField = "//input[@id='postal_code']";
    public String submitLocation = "//a[@id='addLocationSubmit']";
    public String mondayStartTimeDropdown = "//select[@class='form-select week-days mon-1 mon-select']";
    public String mondayEndTimeDropdown = "//select[@class='form-select week-days mon-2 mon-select']";
    public String toMFButton = "//button[@class='btn mon-select all-weekdays btn-primary h-auto']";
    public String samplePickupTab = "//button[@id='nav-samplePickup1-tab']";
    public String pickupPersonNameField= "//input[@name='pickup_person_name']";
    public String pickPersonPhoneField = "//input[@name='pickup_person_phone']";
    public String desiredInitialPickupDate = "//input[@name='desired_initial_pickup_date']";
    public String getDesiredInitialPickupTime = "//input[@name='desired_initial_pickup_time']";
    public String pickupLocationField = "//input[@name='pickup_location']";
    public String extraHoursPickup = "//input[@name='extra_hours_pickup']";
    public String recurrenceScheduleMondayStartTime = "//select[@class='form-select week-days-rec mon-1-rec mon-select-rec']";
    public String recurrenceScheduleMondayEndTime = "//select[@class='form-select week-days-rec mon-2-rec mon-select-rec']";
    public String recurrenceScheduleMFButton = "//button[@class='btn mon-select-rec all-weekdays-rec btn-primary h-auto']";
    public String successfulMessage = "//h2[@id='swal2-title']";
    public String okButton = "//button[normalize-space()='Ok']";
}