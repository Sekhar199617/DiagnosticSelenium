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

            CommonUtils commonUtils = new CommonUtils(driver);
            DashboardPage dashboardPage = new DashboardPage(driver);
            UsersAndRolesPage usersAndRolesPage = new UsersAndRolesPage(driver);
            EducationFlowsPage educationFlowsPage = new EducationFlowsPage(driver);

            loadTestData(
                    "./testData/adminLoginData.json",
                    "./testData/hamburgerMenuModulesData.json"
            );

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            dashboardPage.selectHamburgerTab("Education Flows");
            commonUtils.clickOnElement(commonUtils.findElementByXpath(educationFlowsPage.addButton), "Add");
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(educationFlowsPage.educationFlowNameField),
                    randomString());
            commonUtils.selectDropDownValueWithClick(commonUtils.findElementsByXpath(educationFlowsPage.list),
                    getTestData("purposeOfFlow"));
            commonUtils.validateCheckbox(commonUtils.findElementByXpath(educationFlowsPage.activeCheckbox));
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(educationFlowsPage.saveButton));
            commonUtils.validateGetText(commonUtils.findElementByXpath(educationFlowsPage.dialogueText),
                    getTestData("educationFlowDialogueText"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(educationFlowsPage.educationFlowDialogue),
                    "Ok");

            //Update Functionality
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(educationFlowsPage.educationFlowNameField),
                    randomString() + "updated");
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(educationFlowsPage.purposeOfFlowDropDown),
                    getTestData("updatedPurposeOfFlow"));
            commonUtils.validateCheckbox(commonUtils.findElementByXpath(educationFlowsPage.activeCheckbox));
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(educationFlowsPage.updateButton));

            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(
                            usersAndRolesPage.dialogueText),
                    getTestData("updatedEducationFlowDialogueText"),
                    commonUtils.findElementByXpath(usersAndRolesPage.dialogueOkButton));
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Educational Flows Test ******");
    }
}
