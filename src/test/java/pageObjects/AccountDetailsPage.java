package pageObjects;
import org.openqa.selenium.WebDriver;
import utilities.CommonUtils;


public class AccountDetailsPage extends BasePage {

	CommonUtils commonUtils;

	public AccountDetailsPage(WebDriver driver) {
		super(driver);
		this.commonUtils = new CommonUtils(driver);
	}

	public String tabList = "//ul[@id='myTabs']/li";
	public String addText = "//*[@id='tab-content-3']/div[1]/div[2]/a";

}
