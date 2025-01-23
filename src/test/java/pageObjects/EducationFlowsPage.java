package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.CommonUtils;
import java.util.List;

public class EducationFlowsPage extends BasePage {

    CommonUtils commonUtils;

    public EducationFlowsPage(WebDriver driver) {
        super(driver);
        this.commonUtils = new CommonUtils(driver);
    }

    @FindBy(xpath = "//button[normalize-space()='Add']")
    public WebElement addButton;

    @FindBy(xpath = "//input[@id='observation_flow_name']")
    public WebElement educationFlowNameField;

    @FindBy(xpath = "//select[@id='flow_purpose']")
    public WebElement purposeOfFlowDropDown;

    @FindBy(xpath = "//select[@id='flow_purpose']//option")
    public List<WebElement> list;

    @FindBy(xpath = "//input[@id='active_check']")
    public WebElement activeCheckbox;

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    public WebElement saveButton;

    @FindBy(xpath = "//h2[@class='swal2-title']")
    public WebElement dialogueText;

    @FindBy(xpath = "//button[normalize-space()='Ok']")
    public WebElement educationFlowDialogue;

    @FindBy(xpath = "//button[@id='submitEductionFlowForm']")
    public WebElement updateButton;

}
