package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.CommonUtils;

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

    public String deleteAccount = "(//button[normalize-space()='Yes, Delete It'])[1]";
    public String newAccountHeading = "//div[@class='row mb-3']";
    public String backToSearchButton = "//a[normalize-space()='Back to Search Results']";

    public void performActionOnUser( String userName) throws InterruptedException {
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='accountsTable']/tbody/tr"));

        for (WebElement row : rows) {
            WebElement nameCell = row.findElement(By.xpath("./td[1]"));

            if (nameCell.getText().trim().equals(userName)) {
                System.out.println("Found user: " + nameCell.getText());

                WebElement actionsButton = row.findElement(By.xpath(".//button[contains(text(),'Actions')]"));
                actionsButton.click();

                Thread.sleep(1000); // Wait for dropdown to appear

                List<WebElement> deleteOptions = row.findElements(By.xpath(".//span[contains(text(),'Delete')]"));
                if (!deleteOptions.isEmpty()) {
                    WebElement actionOption = deleteOptions.get(0);
                    actionOption.click();
                    System.out.println("Delete button clicked for user: " + userName);
                } else {
                    System.out.println("Delete option not found for user: " + userName);
                }

                break;
            }
        }

    }

    public boolean isAccountDeleted(String userName) throws InterruptedException {
       dp.searchForItem(userName); // Use the existing search method
        Thread.sleep(2000); // Wait for results to load

        List<WebElement> noDataMessage = driver.findElements(By.xpath("//td[contains(text(),'No data available in table')]"));

        boolean isDeleted = !noDataMessage.isEmpty();
        if (isDeleted) {
            System.out.println("Account successfully deleted.");
            return true;
        } else {
            System.out.println("Account is still present.");
            return false;
        }
    }

}
