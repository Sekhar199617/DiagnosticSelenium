package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utilities.CommonUtils;

import java.util.List;

public class AccountDetailsIntegrationPage extends BasePage{

    CommonUtils commonUtils;
    public boolean isDropdownValuePresent;

    public AccountDetailsIntegrationPage(WebDriver driver) {
        super(driver);
        this.commonUtils = new CommonUtils(driver);
    }

    public String newIntegrationButton = "//a[@id='add_integration']";
    public String apiMappingButton = "//button[@id='nav-api-mappings-tab']";
    public String typesOfIntegrationDropdown = "//select[@name='integration_type_id']";
    public String partnerDropdown = "//select[@name='support_entity_id']";

    public void validateDropdownValue(String dropdownXpath, String expectedValue) {
        WebElement dropdownElement = commonUtils.findElementByXpath(dropdownXpath);
        Select dropdown = new Select(dropdownElement);
        List<WebElement> options = dropdown.getOptions();
        isDropdownValuePresent = false;

        for (WebElement option : options) {
            if (option.getText().equals(expectedValue)) {
                isDropdownValuePresent = true;
                break;
            }
        }

        if (isDropdownValuePresent) {
            System.out.println("Validation Passed: '" + expectedValue + "' exists in the dropdown.");
        } else {
            System.out.println("Validation Failed: '" + expectedValue + "' is NOT present in the dropdown.");
        }
    }


}
