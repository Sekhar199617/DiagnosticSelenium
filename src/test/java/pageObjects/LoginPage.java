package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@placeholder='Email']")
	WebElement emailField;
	
	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement passwordField;
	
	@FindBy(xpath = "//button[@id='adminLoginForm']")
	WebElement adminLoginButton;
	
	@FindBy(xpath = "//button[@id='observerLoginForm']")
	WebElement observerLoginButton;
	
	@FindBy(xpath = "//div[@role='alert']")
	WebElement alertMessage;
	
	
	public void enterEmail(String username) {
		emailField.sendKeys(username);
    }
	
	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public void clickOnAdminLogin() {
		adminLoginButton.click();
	}
	
	public void clickOnObserverLogin() {
		observerLoginButton.click();
	}
	
	public String alertMessage() {
	    return alertMessage.getText();
	}

}
