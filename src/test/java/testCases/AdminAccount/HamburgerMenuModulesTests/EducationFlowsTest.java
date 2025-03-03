package testCases.AdminAccount.HamburgerMenuModulesTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.AccountDetailsModules.UsersAndRolesPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import pageObjects.AdminAccount.HamburgerMenuModules.EducationFlowsPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class EducationFlowsTest extends BaseClass {

    @Test
    public void verifyEducationFlowsTest() {

        logger.info("****** Starting Educational Flows Test ******");
        try{
            loadTestData(
                    "./testData/adminLoginData.json",
                    "./testData/hamburgerMenuModulesData.json"
            );

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);

            dp.selectHamburgerTab("Education Flows");

            EducationFlowsPage ef = new EducationFlowsPage(driver);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ef.addButton), "Add");
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ef.educationFlowNameField),
                    randomString());
            commonUtils.selectDropDownValueWithClick(commonUtils.findElementsByXpath(ef.list),
                    getTestData("purposeOfFlow"));
            commonUtils.validateCheckbox(commonUtils.findElementByXpath(ef.activeCheckbox));
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(ef.saveButton));
            commonUtils.validateGetText(commonUtils.findElementByXpath(ef.dialogueText),
                    getTestData("educationFlowDialogueText"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ef.educationFlowDialogue), "Ok");

            //Update Functionality
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ef.educationFlowNameField),
                    randomString() + "updated");
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ef.purposeOfFlowDropDown),
                    getTestData("updatedPurposeOfFlow"));
            commonUtils.validateCheckbox(commonUtils.findElementByXpath(ef.activeCheckbox));
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(ef.updateButton));
            UsersAndRolesPage au = new UsersAndRolesPage(driver);
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(au.dialogueText),
                    getTestData("updatedEducationFlowDialogueText"),
                    commonUtils.findElementByXpath(au.dialogueOkButton));
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Educational Flows Test ******");
    }
}
