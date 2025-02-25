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
    public String activeCheckbox = "[name='activeAccount']"; //css
    public String demoAccountCheckbox = "[name='demoUser']"; //css
    public String editButton = "//button[@id='enable-edit-form']";
    public String createNewButton = "//a[normalize-space()='Create New']";
    public String ssoButton = "//a[@id='tab16']";
    public String singleSignOnIntegrationAddButton = "//div[@id='addSSOIntegrationType']";
    public String integrationTypeDropdown = "//select[@id='sso_protocol']";
    public String integrationName = "//input[@id='integration_name']";
    public String samlSsoUrl = "//input[@id='sso_endpoint']";
    public String singleSignOnUrl = "//input[@name='single_sign_on']";
    public String singleSignOutUrl = "//input[@name='remote_logout_url']";
    public String certificateFingerprint = "//input[@id='certificate_fingerprint']";
    public String remoteLogoutUrl = "//input[@id='post_logout_url']";
    public String iPRanges = "//input[@id='ip_ranges']";
    public String saveSSOIntegrationButton = "//a[@onclick='save_sso_integration()']";
    public String successfulMessage = "//h2[@id='swal2-title']";
    public String successfulMessageOkButton = "//button[@class='swal2-confirm swal2-styled swal2-default-outline']";
}
