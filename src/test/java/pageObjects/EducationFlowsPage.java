package pageObjects;
import org.openqa.selenium.WebDriver;
import utilities.CommonUtils;

public class EducationFlowsPage extends BasePage {

    CommonUtils commonUtils;

    public EducationFlowsPage(WebDriver driver) {
        super(driver);
        this.commonUtils = new CommonUtils(driver);
    }

    public String addButton = "//button[normalize-space()='Add']";
    public String educationFlowNameField = "//input[@id='observation_flow_name']";
    public String purposeOfFlowDropDown = "//select[@id='flow_purpose']";
    public String list = "//select[@id='flow_purpose']";
    public String activeCheckbox = "//input[@id='active_check']";
    public String saveButton = "//button[contains(text(),'Save')]";
    public String dialogueText = "//h2[@class='swal2-title']";
    public String educationFlowDialogue = "//button[normalize-space()='Ok']";
    public String updateButton = "//button[@id='submitEductionFlowForm']";

}
