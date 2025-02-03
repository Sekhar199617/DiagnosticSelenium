package pageObjects;

import org.openqa.selenium.WebDriver;
import utilities.CommonUtils;

public class AddGroupPage extends BasePage {

    CommonUtils commonUtils;

    public AddGroupPage(WebDriver driver) {
        super(driver);
        commonUtils = new CommonUtils(driver);
    }

    public String addGroupButton = "//a[@class='btn btn-primary group-add-btn']";
    public String groupNameField = "//input[@id='group_name']";
    public String orderingModeDropdown = "//select[@id='order_mode_id']";
    public String selectLogImageDropdown = "//select[@id='group_logo_image']";
    public String discountModeDropdown = "//select[@id='discount_mode_id']";
    public String  bundlesAndExperienceDropdown = "//select[@id='bundle_experience_id']";
    public String immediateCommunicationPreferenceCheckbox = "//input[@id='immediately']";
    public String manuallyCommunicationPreferenceCheckbox = "//input[@id='manually']";
    public String uploadCSV = "//input[@class='btn btn-primary uploadMembersBtn']";
    public String saveGroupButton = "//input[@id='addGroupForm']";
}
