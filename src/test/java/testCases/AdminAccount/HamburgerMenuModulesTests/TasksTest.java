package testCases.AdminAccount.HamburgerMenuModulesTests;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import pageObjects.AdminAccount.HamburgerMenuModules.TasksPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class TasksTest extends BaseClass {
    @Test
    public void verifySupportEntityVisibleToUser() {

        logger.info("****** Starting Send the Mail on Clicking The Contact Assignee Button in Shipping Task Test ******");
        try {

            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            TasksPage tp = new TasksPage(driver);

            loadTestData(
                    "./testData/AdminAccountData/adminLoginData.json",
                    "./testData/AdminAccountData/dashboardData.json",
                    "./testData/AdminAccountData/hamburgerMenuModulesData.json"
            );

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            // Selecting entities from hamburger menu
            dp.selectHamburgerTab("Tasks");
            tp.clickContactAssignee(getTestData("pendingTaskAccountName"),
                    getTestData("pendingTaskAssigneeName"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(tp.sendContactAssigneeButton),null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(tp.emailSentSuccessfulMessage),
                    getTestData("emailSentSuccessfulValidationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(tp.emailSentSuccessfulMessageOkButton),
                    null);

        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("****** Finished Send the Mail on Clicking The Contact Assignee Button in Shipping Task Test ******");
    }
}