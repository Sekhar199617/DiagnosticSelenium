package pageObjects;
import org.openqa.selenium.WebDriver;
import utilities.CommonUtils;

public class AccountDetailsOnlineEducationPage extends BasePage {

    CommonUtils commonUtils;

    public AccountDetailsOnlineEducationPage(WebDriver driver) {
        super(driver);
        this.commonUtils = new CommonUtils(driver);
    }

    public String experiencesForProvisionedOrdersRadioButton = "//input[@id='provisioned_experience']";
    public String manageButton = "//a[@id='accountExperienceLink']";
    public String addButton = "(//a[normalize-space()='Add'])[1]";
    public String experienceNameField = "//input[@id='exp_name']";
    public String attachBundleDropDown = "//select[@id='bundle_id']";
    public String saveButton = "//button[@id='addFlowSubmit']";

}
