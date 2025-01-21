package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.CommonUtils;

public class AccountDetailsOnlineEducationPage extends BasePage {

    CommonUtils commonUtils;

    public AccountDetailsOnlineEducationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='provisioned_experience']")
    WebElement experiencesForProvisionedOrdersRadioButton;

    @FindBy(xpath = "//a[@id='accountExperienceLink']")
    WebElement manageButton;

    @FindBy(xpath = "(//a[normalize-space()='Add'])[1]")
    WebElement addButton;

    @FindBy(xpath = "//input[@id='exp_name']")
    WebElement experienceNameField;

    @FindBy(xpath = "//select[@id='bundle_id']")
    WebElement attachBundleDropDown;

    @FindBy(xpath = "//button[@id='addFlowSubmit']")
    WebElement saveButton;

    public void selectExperiencesForProvisionedOrdersRadioButton() {
        if (!experiencesForProvisionedOrdersRadioButton.isSelected()) {
            experiencesForProvisionedOrdersRadioButton.click();
        }
    }

    public void clickOnManage() {
        manageButton.click();
    }

    public void clickOnAdd() {
        addButton.click();
    }

    public void enterExperienceName(String experienceName) {
        experienceNameField.sendKeys(experienceName);
    }

    public void selectAttachBundleValue(String attachBundleValue) {
        commonUtils.selectDropDownValue(attachBundleDropDown, attachBundleValue);
    }

    public void clickOnSave() {
        saveButton.click();
    }


}
