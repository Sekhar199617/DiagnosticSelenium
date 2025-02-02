package pageObjects;
import org.openqa.selenium.WebDriver;
import utilities.CommonUtils;

public class DashboardPage extends BasePage {
	
	CommonUtils commonUtils;

	public DashboardPage(WebDriver driver) {
		super(driver);
		this.commonUtils = new CommonUtils(driver);
	}

	public String headerText = "//h3[normalize-space()='Dashboard']";
	public String togglerIcon = "//span[@class='navbar-toggler-icon']";
	public String logoutButton = "//a[normalize-space()='Logout']";
	public String searchField = "//input[@id='searchAccName']";
	public String searchButton = "//button[@id='search_btn']";
	public String actionsDropDown = "//button[@id='dropdownMenuButton1']";
	public String view = "//a[normalize-space()='View']";
	public String hamburgerMenuList = "//div[@class='mt--10px']/li/a";
	
	public boolean isMyAccountExists()	{
		try	{
			return (commonUtils.findElementByXpath(headerText).isDisplayed());
		}catch(Exception e)	{
			return false;
		}
	}

	public void selectHamburgerTab(String tabName) {
		commonUtils.clickOnElement(commonUtils.findElementByXpath(togglerIcon), null);
		commonUtils.selectTab(commonUtils.findElementsByXpath(hamburgerMenuList), tabName);
	}

	public void searchForItem(String searchItem) {
		commonUtils.enterValueInTextField(commonUtils.findElementByXpath(searchField), searchItem);
		commonUtils.clickOnElement(commonUtils.findElementByXpath(searchButton), "Search");
	}

	public void clickView() {
		commonUtils.clickOnElement(commonUtils.findElementByXpath(actionsDropDown), null);
		commonUtils.clickOnElement(commonUtils.findElementByXpath(view), "View");
	}

}
