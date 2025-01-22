package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.CommonUtils;

import java.util.List;

public class AccountDetailsSettingsPage extends BasePage {

    CommonUtils commonUtils;

    public AccountDetailsSettingsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//ul[@id='settingTabs']/li/a")
    List<WebElement> settingsTabsList;

    @FindBy(xpath = "//button[@id='enable-edit-form']")
    WebElement editButton;

    @FindBy(xpath = "//a[normalize-space()='Create New']")
    WebElement createNewButton;

    public void selectSettingsTab(String settingsTabName) {
        commonUtils.selectTab(settingsTabsList, settingsTabName);
    }

    public void clickOnEdit() {
        editButton.click();
    }

    public void clickOnCreateNew() {
        createNewButton.click();
    }
}
