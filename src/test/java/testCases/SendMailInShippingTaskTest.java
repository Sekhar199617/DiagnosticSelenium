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

        logger.info("****** Starting Send the Mail on Clicking The Contact Assignee Button in Shipping Task Test ******");
        try {
            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            TaskPage tp = new TaskPage(driver);

            // Selecting entities from hamburger menu
            dp.selectHamburgerTab("Tasks");

            tp.clickContactAssignee(p.getProperty("pendingTaskAccountName"),  p.getProperty("pendingTaskAssigneeName"));
            // Locate all table rows
            List<WebElement> rows = driver.findElements(By.xpath("//table[@id='purchaseUsersTable']/tbody/tr"));

            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));

                if (cells.size() >= 3) {
                    String accountName = cells.get(1).getText().trim();
                    String assigneeName = cells.get(2).getText().trim();

                    if (accountName.equals(p.getProperty("pendingTaskAccountName")) && assigneeName.equals(p.getProperty("pendingTaskAssigneeName"))) {
                        // Click the "Actions" dropdown
                        row.findElement(By.xpath(".//button[contains(text(),'Actions')]")).click();

                        Thread.sleep(1000);
                        // Click the "Contact Assignee" option
                        row.findElement(By.xpath(".//a[contains(text(),'Contact Assignee')]")).click();
                        Thread.sleep(1000);

                        System.out.println("Clicked 'Contact Assignee' for account: " + p.getProperty("pendingTaskAccountName") + " and assignee: " + p.getProperty("pendingTaskAssigneeName"));
                        break; // Stop after finding and clicking the correct row
                    }
                }
            }
            commonUtils.clickOnElement(commonUtils.findElementByXpath(tp.sendContactAssigneeButton),null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(tp.emailSentSuccessfulMessage),p.getProperty("emailSentSuccessfulValidationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(tp.emailSentSuccessfulMessageOkButton),null);

        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("****** Finished Send the Mail on Clicking The Contact Assignee Button in Shipping Task Test ******");
    }
}
