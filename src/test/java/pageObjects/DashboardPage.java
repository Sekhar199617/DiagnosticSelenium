package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.CommonUtils;
import java.util.List;

public class DashboardPage extends BasePage {
	
	CommonUtils commonUtils;

	public DashboardPage(WebDriver driver) {
		super(driver);
		this.commonUtils = new CommonUtils(driver);
	}
	
	@FindBy(xpath = "//h3[normalize-space()='Dashboard']")
	public WebElement headerText;
	
	@FindBy(xpath = "//span[@class='navbar-toggler-icon']")
	public WebElement togglerIcon;
	
	@FindBy(xpath = "//a[normalize-space()='Logout']")
	public WebElement logoutButton;
	
	@FindBy(xpath = "//input[@id='searchAccName']")
	public WebElement searchField;
	
	@FindBy(xpath = "//button[@id='search_btn']")
	public WebElement searchButton;
	
	@FindBy(xpath = "//button[@id='dropdownMenuButton1']")
	public WebElement actionsDropDown;
	
	@FindBy(xpath = "//a[normalize-space()='View']")
	public WebElement view;

	@FindBy(xpath = "//div[@class='mt--10px']/li/a")
	public List<WebElement> hamburgerMenuList;
	
	public boolean isMyAccountExists()	{
		try	{
			return (headerText.isDisplayed());
		}catch(Exception e)	{
			return false;
		}
	}

}
