package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.CommonUtils;

import java.time.Duration;
import java.util.List;

public class PurchaseLevelAccountPage extends BasePage{
    CommonUtils commonUtils;

    public PurchaseLevelAccountPage(WebDriver driver) {
        super(driver);
        this.commonUtils = new CommonUtils(driver);
    }

    public String userTypeDropdown = "//select[@id='roleFilter']";
    public String chooseFileButton = "//input[@name='csvfile']";
    public String uploadCsvExcelButton = "//button[normalize-space()='upload_csv/excel']";
    public String uploadCsvButton = "//button[@name='submit1']";
    public String assignBundleButton = "//button[@id='assign_bundle_btn']";
    public String assigneeType = "//select[@id='assignment_type']";
    public String experienceAssigneeUploadDropdown = "//select[@id='observation_experience']";
    public String numberToAssignCompleteUploadButton = "//button[@id='complete_upload']";
    public String userUploadValidationMessage = "//h2[@class='swal2-title']";
    public String userUploadOkButton = "//button[normalize-space()='ok']";
    public String accountLevelHeading = "//span[@id='header_label']";
    public String accountLevelCancelButton = "//a[@onclick='detectChanges()']";
    public String newOrderButton = "//button[normalize-space()='New Order']";
    public String newPatientLink = "//a[@id='newPatientBtn']";
    public String firstNameNewOrderField = "//input[@id='first-name']";
    public String lastNameNewOrderField = "//input[@id='last-name']";
    public String emailNewOrderField = "//input[@id='order-email']";
    // public String countryCodeNewOrderDropdown = "//div[@class='iti__selected-flag']";
    // public String countryNameNewOrderLocator = "//span[@class='iti__country-name' and text()='%s']";
    public String countryListNewOrder = "//span[@class='iti__country-name']";
    public String mobileNumberNewOrderField = "//input[@name='phone_number']";
    public String demoUserCheckboxNewOrder = "//input[@id='demo-user']";
    public String continueNewOrderButton = "//button[@id='continue-btn']";
    public String countryNewOrderDropdown = "//select[@id='country']";
    public String regionNewOrderDropdown = "//select[@id='state']";
    public String regionInputNewOrderField = "//input[@id='state']";
    public String shippingAddressNewOrder = "//input[@id='shipping_address']";
    public String shippingAddress2NewOrder = "//input[@id='shipping_address_2']";
    public String cityNewOrderField = "//input[@id='cityOrder']";
    public String postalCodeNewOrderField = "//input[@id='zipOrder']";
    public String itemTypeNewOrderDropdown = "//select[@id='itemType']";
    public String addBundleNewOrderDropdown = "//select[@id='bundle_id']";
    public String flavorNewOrderDropdown = "//select[@id='education_observation_options']";
    public String addQuantityDropdown = "//select[@id='assignment_count_select']";
    public String addToOrderButton = "//button[@id='addToOrder']";
    public String selectShipmentScheduleDropdown = "//select[@id='shipping_schedule_select']";
    public String eventNameNewOrderDropdown = "//select[@id='event_tag_select']";
    public String shippingSpeedNewOrderDropdown = "//select[@id='shippingSpeed']";
    public String orderingPhysicianNameField = "//input[@id='physicianName']";
    public String npiOrIdField = "//input[@id='nipID']";
    public String physicianEmailField = "//input[@id='physician_email']";
    public String physicianPhoneField = "//input[@id='physician_phone']";
    public String confirmOrderButton = "//button[@id='confirm-order']";
    public String newOrderSuccessfulConfirmationMessage = "//h2[@id='swal2-title']";
    public String newOrderSuccessfulConfirmationOkButton = "//button[normalize-space()='ok']";
    public String countryDropdown = "//div[@class='iti__selected-flag']";
    public String purchaserTogglerIcon = "//span[@class='navbar-toggler-icon']";
    public String hamburgerMenuList = "//div[@class='mt--10px']/li/a";
    public String assignmentViewButton = "//tr[@class='odd']//button[@type='button'][normalize-space()='View']";
    public String observationLinkCopyValidationMessage = "//h2[@id='swal2-title']";
    public String observationLinkCopyOkButton = "//button[normalize-space()='Ok']";

    public void selectHamburgerTab(String tabName) {
        commonUtils.clickOnElement(commonUtils.findElementByXpath(purchaserTogglerIcon), null);
        commonUtils.selectTab(commonUtils.findElementsByXpath(hamburgerMenuList), tabName);
    }
    public String countryListbox = "//ul[@id='country-listbox']";
    public String countryOptionByName = "//li[span[normalize-space(text())='%s']]";
    public String countryDropdownArrow = "//div[@class='iti__arrow']";
    public String countryOptionArrow = "//span[@class='iti__country-name']";


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

    public void handleCityAndRegion(String countryName, WebElement regionElement, WebElement regionDropdownElement, String regionName) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        if (countryName.equals("United States")) {
            if (regionDropdownElement != null) {
                wait.until(ExpectedConditions.elementToBeClickable(regionDropdownElement));
                commonUtils.selectDropDownValue(regionDropdownElement, regionName);
            } else {
                System.out.println("Region dropdown not found!");
            }
        } else {
            if (regionElement != null) {
                wait.until(ExpectedConditions.visibilityOf(regionElement));
                commonUtils.enterValueInTextField(regionElement, regionName);
            } else {
                System.out.println("Region input fields not found!");
            }
        }
    }

    public void clickOnAssignmentView(String tableId, String userName) {
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='" + tableId + "']/tbody/tr"));

        for (WebElement row : rows) {
            WebElement nameCell = row.findElement(By.xpath("./td[2]"));

            if (nameCell.getText().trim().equals(userName)) {
                System.out.println("Found user: " + nameCell.getText());

                WebElement viewButton = row.findElement(By.xpath("//button[normalize-space()='View']"));
                viewButton.click();

                break;
            }
        }

    }

    public void clickOnObservationLink(String tableId, String userName) {
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='" + tableId + "']/tbody/tr"));

        for (WebElement row : rows) {
            WebElement nameCell = row.findElement(By.xpath("./td[2]"));

            if (nameCell.getText().trim().equals(userName)) {
                System.out.println("Found user: " + nameCell.getText());

                WebElement copyIcon = row.findElement(By.xpath("//ion-icon[@name='copy-outline']"));
                copyIcon.click();

                break;
            }
        }

    }

}
