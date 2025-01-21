package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.CommonUtils;

public class MyAccountPage extends BasePage {
	
	CommonUtils commonUtils;

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//h3[normalize-space()='Dashboard']")
	WebElement headerText;
	
	@FindBy(xpath = "//span[@class='navbar-toggler-icon']")
	WebElement togglerIcon;
	
	@FindBy(xpath = "//a[normalize-space()='Logout']")
	WebElement logoutButton;
	
	@FindBy(xpath = "//input[@id='searchAccName']")
	WebElement searchField;
	
	@FindBy(xpath = "//button[@id='search_btn']")
	WebElement searchButton;
	
	@FindBy(xpath = "//button[@id='dropdownMenuButton1']")
	WebElement actionsDropDown;
	
	@FindBy(xpath = "//a[normalize-space()='View']")
	WebElement view;
	
	public boolean isMyAccountExists()	{
		try	{
			return (headerText.isDisplayed());
		}catch(Exception e)	{
			return false;
		}
	}
	
	public void clickOnTogglerIcon() {
		togglerIcon.click();
	}
	
	public void clickOnLogoutButton() {
		logoutButton.click();
	}
	
	public void searchForElement(String searchElement) {
		commonUtils.search(searchElement, searchField, searchButton);
	}
	
	public void clickOnActionsDropDown() {
		actionsDropDown.click();
	}
	
	public void clickOnView() {
		view.click();
	}

}
