package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.CommonUtils;

public class AddFormsPage extends BasePage{

    CommonUtils commonUtils;

    public AddFormsPage(WebDriver driver) {
        super(driver);
        commonUtils = new CommonUtils(driver);
    }

    public String addButton = "//a[@class='btn btn-primary addform']";
    public String createNewButton = "//a[@class='btn btn-primary']";

    public String selectedAccountName = "//select[@id='account_id']/option[@selected]";

    public String formScopeDropdown = "//select[@name='form_scope']";
    public String formNameField =  "//input[@name='form_name']";
    public String formEntityTypeDropdown = "//select[@id='form_entity_type']";
    public String formTypeDropdown = "//select[@id='form_type']";
    public String formDescriptionField = "//input[@id='form_description']";
    public String selectLogoImageDropdown = "//select[@name='form_logo_type']";
    public String formInstructionField = "//input[@name='form_instruction']";
    public String afterFormSubmissionRedirectToDropDown = "//select[@name='after_form_submission_redirect_to']";
    public String addAccountFormFieldButton = "//a[normalize-space()='Add Account Form Fields']";

    public String field1InstructionField = "(//textarea[@id='custom_instruction'])[1]";
    public String saveField1Button = "(//button[@onclick='formTemplate(this)'])[1]";
    public String attachedBundlesList = "//div[@id='unselected_bundles']";
    public String rightArrowButton = "//button[@class='btn mb-3 observ-btn-next observ-btn-arrow']";
    public String movedBundlesList = "//div[@id='selected_bundles']";
    public String addFormFieldButton = "//a[@onclick='commonField(\"show\")']";
    public String finishedButton = "//input[@value='Finished']";
    public  String  saveField1button = "(//button[@class='btn btn-primary wbtwPrimaryBtn onSubmitButton'])[1]";
    public  String consentCheckbox = "(//input[@id='consent_acknowledge'])[1]";
    public  String patientFinishedButton = "//input[@type='submit']";
    public String dynamicLocatorPattern1 = "//div[contains(@class,'base_commonField')]//div[normalize-space(text())='%s']";
    public  String AccountDynamicLocatorPattern1 = "//div[contains(@class,'availableFieldLabel')]//div[normalize-space(text())='%s']";

    public String successfulMessageText = "//h2[@id='swal2-title']";
    public String okButton = "//button[normalize-space()='Ok']";

}
