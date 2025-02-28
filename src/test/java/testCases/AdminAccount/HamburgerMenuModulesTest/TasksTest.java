package testCases.AdminAccount.HamburgerMenuModulesTest;

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
            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            TasksPage tasksPage = new TasksPage(driver);
            DashboardPage dashboardPage = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);

            dashboardPage.selectHamburgerTab("Tasks");

            tasksPage.clickContactAssignee(p.getProperty("pendingTaskAccountName"),
                    p.getProperty("pendingTaskAssigneeName"));

            commonUtils.clickOnElement(commonUtils.findElementByXpath(tasksPage.sendContactAssigneeButton),null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(tasksPage.emailSentSuccessfulMessage),
                    p.getProperty("emailSentSuccessfulValidationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(tasksPage.emailSentSuccessfulMessageOkButton),
                    null);

        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("****** Finished Send the Mail on Clicking The Contact Assignee Button in Shipping Task Test ******");
    }
}
