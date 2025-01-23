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
        this.commonUtils = new CommonUtils(driver);
    }

    @FindBy(xpath = "//ul[@id='settingTabs']/li/a")
    public List<WebElement> settingsTabsList;

    @FindBy(xpath = "//button[@id='enable-edit-form']")
    public WebElement editButton;

    @FindBy(xpath = "//a[normalize-space()='Create New']")
    public WebElement createNewButton;

}
