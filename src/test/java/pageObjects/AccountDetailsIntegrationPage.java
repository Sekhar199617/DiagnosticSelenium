package pageObjects;

import org.openqa.selenium.WebDriver;
import utilities.CommonUtils;

public class AccountDetailsIntegrationPage extends BasePage{

    CommonUtils commonUtils;

    public AccountDetailsIntegrationPage(WebDriver driver) {
        super(driver);
        this.commonUtils = new CommonUtils(driver);
    }

    public String newIntegrationButton = "//a[@id='add_integration']";
    public String apiMappingButton = "//button[@id='nav-api-mappings-tab']";
    public String typesOfIntegrationDropdown = "//select[@name='integration_type_id']";
    public String partnerDropdown = "//select[@name='support_entity_id']";

}
