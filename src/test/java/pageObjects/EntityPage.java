package pageObjects;

import org.openqa.selenium.WebDriver;
import utilities.CommonUtils;

public class EntityPage extends BasePage{
    CommonUtils commonUtils;

    public EntityPage(WebDriver driver) {
        super(driver);
        this.commonUtils = new CommonUtils(driver);
    }

    public String supportEntityChart = "//table[@id='supportEntityTable']/tbody/tr";
    public String AuthenticationTab = "//button[@id='nav-auth-tab']";
    public String updateSupportEntityButton = "//button[@id='addSupportEntityForm']";
    public String checkboxLocator = "//input[@id='permit_account_support']";
    public String supportEntityUpdateValidationMessage = "//h2[@id='swal2-title']";
    public String supportEntityUpdatedOKbutton = "//button[normalize-space()='Ok']";
    public String supportEntityCancelButton = "//button[@class='btn btn-secondary wbtwPrimaryBtn']";


}
