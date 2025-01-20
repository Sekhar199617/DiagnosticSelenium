package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.CommonUtils;

public class LoginPage extends BasePage{
	
	CommonUtils commonUtils;
	
	
	public LoginPage(WebDriver driver) {
		super(driver);
		commonUtils = new CommonUtils(driver);
	}
	
	@FindBy(xpath = "//input[@placeholder='Email']")
	WebElement emailField;
	
	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement passwordField;
	
	@FindBy(xpath = "//button[@id='adminLoginForm']")
	WebElement adminLoginButton;
	
	@FindBy(xpath = "//button[@id='observerLoginForm']")
	WebElement observerLoginButton;
	
	
	public void enterEmail(String username) {
		commonUtils.sendKeys(emailField, username);
    }
	
	public void enterPassword(String password)
	{
		commonUtils.sendKeys(passwordField, password);
	}
	
	public void clickOnAdminLogin()
	{
		commonUtils.click(adminLoginButton);
	}
	
	public void clickOnObserverLogin()
	{
		commonUtils.click(observerLoginButton);
	}

}
