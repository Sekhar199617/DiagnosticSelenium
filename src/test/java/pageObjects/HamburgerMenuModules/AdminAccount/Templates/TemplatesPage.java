package pageObjects.HamburgerMenuModules.AdminAccount.Templates;
import org.openqa.selenium.WebDriver;
import pageObjects.BasePage;

public class TemplatesPage extends BasePage {

    public TemplatesPage(WebDriver driver) {
        super(driver);
    }

    public String addButton = "//button[normalize-space()='Add']";
    public String copyMessagingTemplateDropDown = "messagingId"; //name
    public String messagingSetNameField = "messagingSetName"; //id
    public String allowAccountsToSelectThisMessagingSet = "allow_accounts_to_select"; //name
    public String webSiteNameField = "[id='templateAddForm'] div input[name='name']"; //css
    public String emailFromNameForPurchasersField = "email_from_name_for_purchasers"; //name
    public String emailFromAddressForPurchasersField = "email_from_address_for_purchsers"; //name
    public String emailFromNameForAssigneesField = "email_from_name_for_assignees"; //name
    public String emailFromAddressForAssigneesField = "email_from_address_for_assignees"; //name
    public String websiteURLField = "websiteURL"; //name
    public String provisioningSystemURLField = "provisionURL"; //name
    public String logoFileUpload = "input[name='logoURL']"; //css
    public String supportPhoneField = "supportPhone"; //name
    public String faviconFileUpload = "input[name='faviconURL']"; //css
    public String addMessageSetButton = "//button[@id='add_message']"; //xpath
}
