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
	
	@FindBy(xpath = "//button[@id='observerLoginForm']")
	WebElement loginButton;
	
	public void enterEmail(String email)
	{
		emailField.sendKeys(email);
	}
	
	public void enterPassword(String password)
	{
		passwordField.sendKeys(password);
	}
	
	public void clickOnLogin()
	{
		loginButton.click();
	}

}
