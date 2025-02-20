package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.CommonUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

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
    public String newOrderButton = "//button[normalize-space()='new_order']";
    public String newPatientLink = "//a[@id='newPatientBtn']";
    public String firstNameNewOrderField = "//input[@id='first-name']";
    public String lastNameNewOrderField = "//input[@id='last-name']";
    public String emailNewOrderField = "//input[@id='order-email']";
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
    public String countryDropdown = "//div[@class='iti__selected-flag']";
    public String welcomeTextMessage = "//h1[@class='text-primary fw-semibold display-4 mb-0']";
    public String successfulConfirmationMessage = "//h2[@class='swal2-title' and @id='swal2-title']";
    public String successfulConfirmationOkButton = "//button[@type='button' and contains(@class, 'swal2-confirm')]";
    public String shippingTaskRadioButton = "//input[@class='shipping_tasks mx-2']";
    public String formUrlFormName = "//span[contains(@class,'fontHeadline')]";
    public String  editSettingButton = "//button[@id='enable-edit-form']";
    public String settingTab = "//ul[@id='settingTabs']/li";
    public String billingContactDropdown = "//select[@id='billing_contact_id']";
    public String billingCountryDropdown = "//select[@id='billing_country']";
    public String billingAddress1Field = "//input[@name='billing_line_one']";
    public String billingAddress2Field = "//input[@name='billing_line_two']";
    public String billingCityField = "//input[@name='city']";
    public String billingStateField = "//input[@name='state']";
    public String billingPostalCodeField = "//input[@name='postal_code']";
    public String billingUpdateButton = "//button[@name='updateAccountDetails']";
    public String addUserAndRolesButton = "(//a[@class='btn btn-primary'][normalize-space()='Add'])[1]";
    public String newUserNameField = "(//input[@name='name'])[2]";
    public String newUserPhoneField = "//input[@name='phone_number']";
    public String newUserEmailField = "(//input[@name='email'])[2]";
    public String newUserRoleDropdown = "//select[@name='userRole']";
    public String newUserUsertypeDropdown = "//select[@name='role']";
    public String newUserAdditionalPrivilegesCheckboxList = "div[class='manager_div'] input";
    public String newUserTimeZoneDropdown = "//select[@name='timezone_id']";
    public String newUserActiveCheckbox = "//input[@name='activeAccount']";
    public String newUserSaveButton = "//button[@name='save']";
    public String startTrainingButton = "//a[normalize-space()='Start Training']";
    public String resumeTrainingButton = "//a[normalize-space()='Resume Training']";
    public String firstNameRegistrationField = "(//input[@name='name'])[1]";
    public String lastNameRegistrationField = "//input[@name='last_name']";
    public String dateOfBirth = "//input[@name='dob']";
    public String genderDropdown = "//select[@name='gender']";
    public String raceDropdown = "//select[@name='race']";
    public String countryDropdownRegistration = "//select[@aria-label='Country Dropdown']";
    public String shippingAddress1 = "(//input[@name='address'])[1]";
    public String shippingAddress2 = "//input[@name='address2']";
    public String cityField = "//input[@name='city']";
    public String stateInputFieldRegistration = "//input[@id='state']";
    public String postalCode = "//input[@id='text-input']";
    public String uploadFrontImageButton = "//button[@id='upload_front_btn']";
    public String uploadBackImageButton = "//button[@id='upload_back_btn']";
    public String nextButton = "(//button[@name='submit'])[2]";
    public String ethnicityDropdown = "//select[@name='ethnicity']";
    public String frontSideUploadChooseButton = "//input[@name='frontID']";
    public String frontSideSaveButton = "//button[@name='submit1']";
    public String backSideUploadChooseButton = "//input[@name='backID']";
    public String backSideSaveButton = "(//button[@name='submit'])[1]";
    public String registrationFormSubmission = "//h1[@class='banner-title']";
    public String diagnosticLogo = "//img[@alt='Diagnostic.ly']";
    public String formScopeDropdown = "//select[@id='form_scope']";
    public String bundlesList = "//div[@class='col-md-10 showBundleList']";


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

    public void clickOnObservationLink(String tableId, String userName) {
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='" + tableId + "']/tbody/tr"));

        for (WebElement row : rows) {
            WebElement nameCell = row.findElement(By.xpath("./td[2]"));

            if (nameCell.getText().trim().equals(userName)) {
                System.out.println("Found user Observation: " + nameCell.getText());

                WebElement copyIcon = row.findElement(By.xpath("(//td[@class='text-center']/ion-icon[@name='copy-outline'])[1]"));
                copyIcon.click();

                break;
            }
        }

    }

    public static String getClipboardText() throws Exception {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        return (String) clipboard.getData(DataFlavor.stringFlavor);
    }


    public void shippingTaskDismiss(String userName, String actionText) {
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='purchaseUsersTable']/tbody/tr"));

        for (WebElement row : rows) {
            WebElement nameCell = row.findElement(By.xpath("./td[2]"));

            if (nameCell.getText().trim().equals(userName)) {
                System.out.println("Found user: " + nameCell.getText());

                WebElement actionsButton = row.findElement(By.xpath(".//button[contains(text(),'actions')]"));
                actionsButton.click();

                WebElement actionOption = row.findElement(By.xpath(".//a[contains(text(),'" + actionText + "')]"));
                actionOption.click();
                break;
            }
        }

    }

    public String clickOnFormsLink(String formType) {
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='globalFormTable']/tbody/tr"));

          String formName = "";
        for (WebElement row : rows) {
            WebElement nameCell = row.findElement(By.xpath("./td[2]"));

            if (nameCell.getText().trim().equals(formType)) {
                System.out.println("Found Form Type: " + nameCell.getText());

                WebElement valueCell = row.findElement(By.xpath("./td[4]"));
                 formName = valueCell.getText().trim();

                WebElement copyIcon = row.findElement(By.xpath("//td[@class='text-center']/ion-icon[@name='copy-outline']"));
                copyIcon.click();

                return formName;
            }
        }

        System.out.println(formName);
        return formName;
    }



    public void openNewTabWithURL(String url) {
        ((JavascriptExecutor) driver).executeScript("window.open()");

        // Switch to the new tab
        for (String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);
        }
        // Open the given URL in the new tab
        driver.get(url);
    }

    public String convertDateFormat(String date) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate localDate = LocalDate.parse(date, inputFormat);
        return localDate.format(outputFormat);
    }

    public void performUserActionOnUser(String tableId, String userName, String actionText) {
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='" + tableId + "']/tbody/tr"));

        for (WebElement row : rows) {
            WebElement nameCell = row.findElement(By.xpath("./td[1]"));

            if (nameCell.getText().trim().equals(userName)) {
                System.out.println("Found user: " + nameCell.getText());

                WebElement actionsButton = row.findElement(By.xpath(".//button[contains(text(),'actions')]"));
                actionsButton.click();

                WebElement actionOption = row.findElement(By.xpath(".//a[contains(text(),'" + actionText + "')]"));
                actionOption.click();
                break;
            }
        }

    }

    public boolean isElementDisplayed(String xpath) {
        try {
            return commonUtils.findElementByXpath(xpath).isDisplayed();
        } catch (Exception e) { // Catch NoSuchElementException or TimeoutException
            return false;
        }
    }

    public void clickTrainingButton() {
        if (isElementDisplayed(startTrainingButton)) {
            commonUtils.clickOnElement(commonUtils.findElementByXpath(startTrainingButton),null);
            System.out.println("Clicked on Start Training button");
        } else if (isElementDisplayed(resumeTrainingButton)) {
            commonUtils.clickOnElement(commonUtils.findElementByXpath(resumeTrainingButton),null);
            System.out.println("Clicked on Resume Training button");
        } else {
            throw new NoSuchElementException("Neither Start Training nor Resume Training button is displayed.");
        }
    }

    public void clickOnAssignmentView(String tableId, String userName) {
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='" + tableId + "']/tbody/tr"));

        for (WebElement row : rows) {
            WebElement nameCell = row.findElement(By.xpath("./td[2]"));

            if (nameCell.getText().trim().equals(userName)) {
                System.out.println("Found user: " + nameCell.getText());

                WebElement viewButton = row.findElement(By.xpath(".//button[normalize-space()='View']"));
                commonUtils.clickOnElement(viewButton,null);

                break;
            }
        }

    }

    public String clickOnObservationLink(String assignName) {
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='detailsAssignmentsTable']/tbody/tr"));
        String name = "";

        for (WebElement row : rows) {
            WebElement nameCell = row.findElement(By.xpath("./td[2]"));
            WebElement statusCell = row.findElement(By.xpath("./td[10]"));

            if (nameCell.getText().trim().equals(assignName) && statusCell.getText().trim().equals("Not Started")) {
                System.out.println("Found Form Type: " + nameCell.getText());

                WebElement valueCell = row.findElement(By.xpath("./td[4]"));
                name = valueCell.getText().trim();

                WebElement copyIcon = row.findElement(By.xpath("./td[@class='text-center']/ion-icon[@name='copy-outline']"));
                copyIcon.click();

                return name;
            }
        }

        System.out.println("No matching record found with status 'Not Started'");
        return name;
    }

}
