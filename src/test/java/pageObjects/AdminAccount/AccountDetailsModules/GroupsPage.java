package pageObjects.AdminAccount.AccountDetailsModules;

import org.openqa.selenium.WebDriver;
import pageObjects.BasePage;
import utilities.CommonUtils;

public class GroupsPage extends BasePage {

    CommonUtils commonUtils;

    public GroupsPage(WebDriver driver) {
        super(driver);
        commonUtils = new CommonUtils(driver);
    }

    public String addGroupButton = "//a[@class='btn btn-primary group-add-btn']";
    public String groupNameField = "//input[@id='group_name']";
    public String orderingModeDropdown = "//select[@id='order_mode_id']";
    public String selectLogImageDropdown = "//select[@id='group_logo_image']";
    public String discountModeDropdown = "//select[@id='discount_mode_id']";
    public String  bundlesAndExperienceDropdown = "//select[@id='bundle_experience_id']";
    public String immediateCommunicationPreferenceRadioButton = "//input[@id='immediately']";
    public String manuallyCommunicationPreferenceRadioButton = "//input[@id='manually']";
    public String uploadCSV = "//input[@class='btn btn-primary uploadMembersBtn']";
    public String fileUploadInput = "//input[@id='upload_csv']";
    public String uploadButton = "//button[@name='submit1']";
    public String reviewMembersButton = "//button[@id='assign_product_btn']";
    public String okButton = "//button[@class='swal2-confirm swal2-styled swal2-default-outline']";
    public String saveGroupButton = "//input[@id='addGroupForm']";
    public String successfulMessage = "//h2[@id='swal2-title']";
    public String successfulOkButton = "//button[@class='swal2-confirm swal2-styled swal2-default-outline']";
}
