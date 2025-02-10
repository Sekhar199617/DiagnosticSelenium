package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DashboardPage;
import pageObjects.TaskPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.util.List;

public class SendMailInShippingTaskTest extends BaseClass {
    @Test
    public void verifySupportEntityVisibleToUser() {

        logger.info("****** Starting Support Entity Visible To User Test ******");
        try {
            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            TaskPage tp = new TaskPage(driver);

            // Selecting entities from hamburger menu
            dp.selectHamburgerTab("Tasks");

            tp.clickContactAssignee(p.getProperty("pendingTaskAccountName"),  p.getProperty("pendingTaskAssigneeName"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(tp.sendContactAssigneeButton),null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(tp.emailSentSuccessfulMessage),p.getProperty("emailSentSuccessfulValidationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(tp.emailSentSuccessfulMessageOkButton),null);

        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("****** Finished Add Patient Data Form Test Case ******");
    }
}
