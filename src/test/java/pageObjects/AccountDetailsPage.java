package pageObjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utilities.CommonUtils;


public class AccountDetailsPage extends BasePage {

	CommonUtils commonUtils;

	public AccountDetailsPage(WebDriver driver) {
		super(driver);
		this.commonUtils = new CommonUtils(driver);
	}
	
	@FindBy(xpath = "//ul[@id='myTabs']/li")
	public List<WebElement> tabList;
	
	@FindBy(xpath = "//*[@id='tab-content-3']/div[1]/div[2]/a")
	public WebElement addText;

	@FindBy(xpath = "//div[@id='accountsTableUserRoles_info']")
	WebElement noOfPageInfo;

	@FindBy(xpath = "//ul[@class='dropdown-menu show']//span[@class='dropdown-item'][normalize-space()='Delete']")
	WebElement deleteButton;

	@FindBy(xpath = "//div[@class='swal2-actions']//button[normalize-space()='Ok']")
	public WebElement dialogueWindowOkButton;

	@FindBy(xpath = "//h2[@id='swal2-title']")
	public WebElement dialogueWindowTitleText;

}
