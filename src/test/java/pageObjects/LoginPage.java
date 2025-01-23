package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@placeholder='Email']")
	public WebElement emailField;
	
	@FindBy(xpath = "//input[@placeholder='Password']")
	public WebElement passwordField;
	
	@FindBy(xpath = "//button[@id='adminLoginForm']")
	public WebElement adminLoginButton;
	
	@FindBy(xpath = "//button[@id='observerLoginForm']")
	public WebElement observerLoginButton;
	
	@FindBy(xpath = "//div[@role='alert']")
	public WebElement alertMessage;

}
