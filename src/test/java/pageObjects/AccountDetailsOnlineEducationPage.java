package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.CommonUtils;

public class AccountDetailsOnlineEducationPage extends BasePage {

    CommonUtils commonUtils;

    public AccountDetailsOnlineEducationPage(WebDriver driver) {
        super(driver);
        this.commonUtils = new CommonUtils(driver);
    }

    @FindBy(xpath = "//input[@id='provisioned_experience']")
    public WebElement experiencesForProvisionedOrdersRadioButton;

    @FindBy(xpath = "//a[@id='accountExperienceLink']")
    public WebElement manageButton;

    @FindBy(xpath = "(//a[normalize-space()='Add'])[1]")
    public WebElement addButton;

    @FindBy(xpath = "//input[@id='exp_name']")
    public WebElement experienceNameField;

    @FindBy(xpath = "//select[@id='bundle_id']")
    public WebElement attachBundleDropDown;

    @FindBy(xpath = "//button[@id='addFlowSubmit']")
    public WebElement saveButton;


}
