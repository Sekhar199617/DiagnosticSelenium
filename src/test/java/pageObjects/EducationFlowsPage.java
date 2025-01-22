package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.CommonUtils;

public class EducationFlowsPage extends BasePage {

    CommonUtils commonUtils;

    public EducationFlowsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement addButton;

    @FindBy(xpath = "//input[@id='observation_flow_name']")
    WebElement educationFlowNameField;

    @FindBy(xpath = "//select[@id='flow_purpose']")
    WebElement purposeOfFlowDropDown;

    @FindBy(xpath = "//input[@id='active_check']")
    WebElement activeCheckbox;

    @FindBy(xpath = "//h2[@class='swal2-title']")
    WebElement dialogueText;

    @FindBy(xpath = "//button[normalize-space()='Ok']")
    WebElement educationFlowDialogue;

    public void clickOnAdd() {
        addButton.click();
    }

    public void enterEducationFlowName(String educationFlowName) {
        educationFlowNameField.sendKeys(educationFlowName);
    }

    public void selectPurposeOfFlowValue(String purposeOfFlow) {
        commonUtils.selectDropDownValue(purposeOfFlowDropDown, purposeOfFlow);
    }

    public void verifyActiveCheckbox() {
        commonUtils.validateCheckbox(activeCheckbox);
    }

    public void verifyEducationFlowDialogueText(String expectedMessage) {
        commonUtils.validateGetText(dialogueText, expectedMessage);
    }

    public void clickOnEducationFlowDialogueOk() {
        educationFlowDialogue.click();
    }
}
