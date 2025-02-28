package testCases.HamburgerMenuModulesTests.EducationFlows;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsModules.AdminAccount.UsersAndRoles.UsersAndRolesPage;
import pageObjects.AccountDetailsModules.AdminAccount.Dashboard.DashboardPage;
import pageObjects.HamburgerMenuModules.AdminAccount.EducationFlows.EducationFlowsPage;
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

            dp.selectHamburgerTab("Education Flows");

            EducationFlowsPage ef = new EducationFlowsPage(driver);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ef.addButton), "Add");
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ef.educationFlowNameField),
                    randomString());
            commonUtils.selectDropDownValueWithClick(commonUtils.findElementsByXpath(ef.list),
                    p.getProperty("purposeOfFlow"));
            commonUtils.validateCheckbox(commonUtils.findElementByXpath(ef.activeCheckbox));
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(ef.saveButton));
            commonUtils.validateGetText(commonUtils.findElementByXpath(ef.dialogueText),
                    p.getProperty("educationFlowDialogueText"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ef.educationFlowDialogue), "Ok");

            //Update Functionality
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ef.educationFlowNameField),
                    randomString() + "updated");
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ef.purposeOfFlowDropDown),
                    p.getProperty("updatedPurposeOfFlow"));
            commonUtils.validateCheckbox(commonUtils.findElementByXpath(ef.activeCheckbox));
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(ef.updateButton));
            UsersAndRolesPage au = new UsersAndRolesPage(driver);
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(au.dialogueText),
                    p.getProperty("updatedEducationFlowDialogueText"),
                    commonUtils.findElementByXpath(au.dialogueOkButton));
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Educational Flows Test ******");
    }
}
