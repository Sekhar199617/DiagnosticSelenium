package pageObjects.AdminAccount.AccountDetailsModules;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.BasePage;
import utilities.CommonUtils;

public class AccountDetailsPage extends BasePage {

	CommonUtils commonUtils;

	public AccountDetailsPage(WebDriver driver) {
		super(driver);
		this.commonUtils = new CommonUtils(driver);
	}

	public String tabList = "//ul[@id='myTabs']/li";
	public String addText = "//*[@id='tab-content-3']/div[1]/div[2]/a";
	public String accountDetailsText = "//h4[normalize-space()='%s']";
	public String ordersTab = "//a[normalize-space()='Orders' and contains(@class, 'active')]";

	public void validateAccountDetailsTitle(String expectedTitle) {
		String dynamicXpath = String.format(accountDetailsText, expectedTitle);
		commonUtils.validateGetText(commonUtils.findElementByXpath(dynamicXpath), expectedTitle);
	}

	public void validateDefaultSelectedTab() {
		WebElement activeTab = commonUtils.findElementByXpath(ordersTab);

		if (activeTab != null) {
			System.out.println("Orders tab is selected by Default.");
		} else {
			System.out.println("Orders tab is not selected by Default.");
		}
	}

}
