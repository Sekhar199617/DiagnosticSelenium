package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DashboardPage;
import pageObjects.EducationFlowsPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class EducationFlowsTest extends BaseClass {

    @Test
    public void verifyEducationFlowsTest() {

        logger.info("****** Starting Educational Flows Test ******");
        try{
            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);

            commonUtils.clickOnElement(dp.togglerIcon, null);
            commonUtils.selectTab(dp.hamburgerMenuList, "Education Flows");

            EducationFlowsPage ef = new EducationFlowsPage(driver);
            commonUtils.clickOnElement(ef.addButton, "Add");
            commonUtils.enterValueInTextField(ef.educationFlowNameField, randomString());
            commonUtils.selectMobileCountryCode(ef.list, p.getProperty("purposeOfFlow"));
            commonUtils.validateCheckbox(ef.activeCheckbox);
            commonUtils.scrollToBottomAndClick(ef.saveButton);
            commonUtils.validateGetText(ef.dialogueText, p.getProperty("educationFlowDialogueText"));
            commonUtils.clickOnElement(ef.educationFlowDialogue, "Ok");

            //Update Functionality
            commonUtils.enterValueInTextField(ef.educationFlowNameField, randomString() + "updated");
            commonUtils.selectDropDownValue(ef.purposeOfFlowDropDown, p.getProperty("updatedPurposeOfFlow"));
            commonUtils.validateCheckbox(ef.activeCheckbox);
            commonUtils.scrollToBottomAndClick(ef.updateButton);
            commonUtils.validateGetText(ef.dialogueText, p.getProperty("updatedEducationFlowDialogueText"));
            commonUtils.clickOnElement(ef.educationFlowDialogue, "Ok");
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Educational Flows Test ******");
    }
}
