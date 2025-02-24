package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.CommonUtils;

import java.time.Duration;
import java.util.List;

public class CreateAccountPage extends  BasePage{

    CommonUtils commonUtils;
    DashboardPage dp;
    public  CreateAccountPage(WebDriver driver) {
        super(driver);
        commonUtils = new CommonUtils(driver);
        dp = new DashboardPage(driver);
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

    public String deletedAccountTableMessage = "//td[contains(text(),'No data available in table')]";
    public String deleteAccount = "(//button[normalize-space()='Yes, Delete It'])[1]";
    public String newAccountHeading = "//div[@class='row mb-3']";
    public String backToSearchButton = "//a[normalize-space()='Back to Search Results']";

    public void performAccountDeleteActionOnUser(String tableId, String accountName, String action) throws InterruptedException {
        // Use the common method to find the user and click the "Actions" button
        WebElement row = commonUtils.findUserAndClickActionsDropdown(tableId, accountName);

        if (row != null) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            try {
                // Find the action option (e.g., "Delete", "Edit", etc.)
                WebElement actionOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu show']//span[@class='dropdown-item'][normalize-space()='" + action + "']")));
                actionOption.click();
                System.out.println(action + " button clicked for user: " + accountName);
            } catch (NoSuchElementException e) {
                System.out.println(action + " option not found for user: " + accountName);
            }
        } else {
            System.out.println("User not found: " + accountName);
        }
    }

    public boolean isAccountDeleted(String userName) throws InterruptedException {
        dp.searchForItem(userName); // Use the existing search method
        Thread.sleep(2000); // Wait for results to load

        try {
            WebElement noDataMessage = commonUtils.findElementByXpath(deletedAccountTableMessage);
            if (noDataMessage.isDisplayed()) {
                System.out.println("Account successfully deleted.");
                return true;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Account is still present.");
        }
        return false;
        }
    }


