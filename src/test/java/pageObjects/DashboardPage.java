package pageObjects;
import org.openqa.selenium.StaleElementReferenceException;
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
	public String accountNameInTable = "//table[@id='accountsTable']//tbody//td[1]";
	public String activeAccounts = "[id='show_current_status'] b a"; //css
	public String totalEntriesText = "accountsTable_info"; //id
	public String logo = "//a[@class='navbar-brand']";
	
	public boolean isMyAccountExists()	{
		try	{
			return (commonUtils.findElementByXpath(headerText).isDisplayed());
		}catch(Exception e)	{
			return false;
		}
	}

	public void selectHamburgerTab(String tabName) {
		int attempts = 0;
		boolean tabSelected = false;

		while (attempts < 2 && !tabSelected) {
			try {
				// Wait for the hamburger menu toggle icon to be clickable and click on it
				commonUtils.waitForElementToBeClickable(commonUtils.findElementByXpath(togglerIcon), 15);
				commonUtils.clickOnElement(commonUtils.findElementByXpath(togglerIcon), null);

				// Wait for the hamburger menu list and select the desired tab
				commonUtils.selectTab(commonUtils.findElementsByXpath(hamburgerMenuList), tabName);

				tabSelected = true;  // If no exception is thrown, tab is selected successfully
			} catch (StaleElementReferenceException e) {
				System.out.println("Caught StaleElementReferenceException. Retrying...");
				attempts++;
			} catch (Exception e) {
				System.out.println("Exception occurred: " + e.getMessage());
				break;  // Exit loop on other exceptions
			}
		}

		if (!tabSelected) {
			System.out.println("Tab could not be selected after 2 attempts.");
		}
	}


	public void searchForItem(String searchItem) {
		commonUtils.enterValueInTextField(commonUtils.findElementByXpath(searchField), searchItem);
		commonUtils.clickOnElement(commonUtils.findElementByXpath(searchButton), "Search");
	}

	public void validateAccountNameInTable(String accountName) {
		commonUtils.validateGetText(commonUtils.findElementByXpath(accountNameInTable), accountName);
	}

	public void clickView() {
		commonUtils.clickOnElement(commonUtils.findElementByXpath(actionsDropDown), null);
		commonUtils.clickOnElement(commonUtils.findElementByXpath(view), "View");
	}

	public void clickOnLogo() {
		int attempts = 0;
		boolean elementClicked = false;

		while (attempts < 2 && !elementClicked) {
			try {
				commonUtils.clickOnElement(commonUtils.findElementByXpath(logo), null);

				elementClicked = true;  // If no exception is thrown, tab is selected successfully
			} catch (StaleElementReferenceException e) {
				System.out.println("Caught StaleElementReferenceException. Retrying...");
				attempts++;
			} catch (Exception e) {
				System.out.println("Exception occurred: " + e.getMessage());
				break;  // Exit loop on other exceptions
			}
		}

		if (!elementClicked) {
			System.out.println("Logo not clicked after 2 attempts.");
		}
	}

}
