package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.CommonUtils;

public class MyAccountPage extends BasePage {
	
	CommonUtils commonUtils;

	public MyAccountPage(WebDriver driver) {
		super(driver);
		commonUtils = new CommonUtils(driver);
	}
	
	@FindBy(xpath = "//h3[normalize-space()='Dashboard']")
	WebElement headerText;
	
	@FindBy(xpath = "//span[@class='navbar-toggler-icon']")
	WebElement togglerIcon;
	
	@FindBy(xpath = "//a[normalize-space()='Logout']")
	WebElement logoutButton;
	
	public boolean isMyAccountExists()
	{
		try
		{
			return (headerText.isDisplayed());
		}catch(Exception e)
		{
			return false;
		}
	}
	
	public void clickOnTogglerIcon()
	{
		commonUtils.click(togglerIcon);
	}
	
	public void clickOnLogoutButton()
	{
		commonUtils.click(logoutButton);
	}

}
