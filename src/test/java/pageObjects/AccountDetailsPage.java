package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.CommonUtils;

public class AccountDetailsPage extends BasePage {
	
	CommonUtils commonUtils;

	public AccountDetailsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//ul[@id='myTabs']/li")
	List<WebElement> tabList;
	
	@FindBy(xpath = "//*[@id='tab-content-3']/div[1]/div[2]/a")
	WebElement addText;
	
	public void selectTab(String tabName) {
		commonUtils.selectTab(tabList, tabName);
	}
	
	public void clickOnAdd() {
		addText.click();
	}

}
