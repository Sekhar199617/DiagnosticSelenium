package pageObjects;
import org.openqa.selenium.WebDriver;
import utilities.CommonUtils;

public class AccountDetailsSettingsPage extends BasePage {

    CommonUtils commonUtils;

    public AccountDetailsSettingsPage(WebDriver driver) {
        super(driver);
        this.commonUtils = new CommonUtils(driver);
    }

    public String settingsTabsList = "//ul[@id='settingTabs']/li/a";
    public String editButton = "//button[@id='enable-edit-form']";
    public String createNewButton = "//a[normalize-space()='Create New']";
}
