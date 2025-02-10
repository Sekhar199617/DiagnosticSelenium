package pageObjects;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public String emailField = "//input[@placeholder='Email']";
	public String passwordField = "//input[@placeholder='Password']";
	public String adminLoginButton = "//button[@id='adminLoginForm']";
	public String observerLoginButton = "//button[@id='observerLoginForm']";
	public String alertMessage = "//div[@role='alert']";
	public String emailErrorText = "email-error"; //id
	public String passwordErrorText = "password-error"; //id
}
