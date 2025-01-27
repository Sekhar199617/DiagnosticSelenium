package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TemplatesPage extends BasePage {

    public TemplatesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[normalize-space()='Add']")
    public WebElement addButton;

    @FindBy(name = "messagingId")
    public WebElement copyMessagingTemplateDropDown;

    @FindBy(id = "messagingSetName")
    public WebElement messagingSetNameField;

    @FindBy(name = "allow_accounts_to_select")
    public WebElement allowAccountsToSelectThisMessagingSet;

    @FindBy(css = "[id='templateAddForm'] div input[name='name']")
    public WebElement webSiteNameField;

    @FindBy(name = "email_from_name_for_purchasers")
    public WebElement emailFromNameForPurchasersField;

    @FindBy(name = "email_from_address_for_purchsers")
    public WebElement emailFromAddressForPurchasersField;

    @FindBy(name = "email_from_name_for_assignees")
    public WebElement emailFromNameForAssigneesField;

    @FindBy(name = "email_from_address_for_assignees")
    public WebElement emailFromAddressForAssigneesField;

    @FindBy(name = "websiteURL")
    public WebElement websiteURLField;

    @FindBy(name = "provisionURL")
    public WebElement provisioningSystemURLField;

    @FindBy(css = "input[name='logoURL']")
    public WebElement logoFileUpload;

    @FindBy(name = "supportPhone")
    public WebElement supportPhoneField;

    @FindBy(css = "input[name='faviconURL']")
    public WebElement faviconFileUpload;

    @FindBy(xpath = "//button[@id='add_message']")
    public WebElement addMessageSetButton;
}
