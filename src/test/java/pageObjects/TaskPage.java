package pageObjects;

import org.openqa.selenium.By;;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.CommonUtils;

import java.util.List;

public class TaskPage extends BasePage{
    CommonUtils commonUtils;

    public TaskPage(WebDriver driver) {
        super(driver);
        commonUtils = new CommonUtils(driver);
    }

    public String sendContactAssigneeButton = "//button[@name='sendExpMail']";
    public String emailSentSuccessfulMessage = "//h2[@class='swal2-title']";
    public String emailSentSuccessfulMessageOkButton = "//button[@class='swal2-confirm swal2-styled swal2-default-outline']";

    public void clickContactAssignee(String accountName, String assigneeName) {
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='purchaseUsersTable']/tbody/tr"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));

            if (cells.size() >= 3) {
                String fetchedAccountName = cells.get(1).getText().trim();
                String fetchedAssigneeName = cells.get(2).getText().trim();

                if (fetchedAccountName.equals(accountName) && fetchedAssigneeName.equals(assigneeName)) {
                    // Click the "Actions" dropdown
                    row.findElement(By.xpath(".//button[contains(text(),'Actions')]")).click();

                    // Click the "Contact Assignee" option
                    row.findElement(By.xpath(".//a[contains(text(),'Contact Assignee')]")).click();

                    System.out.println("Clicked 'Contact Assignee' for account: " + accountName + " and assignee: " + assigneeName);
                    break; // Stop after finding and clicking the correct row
                }
            }
        }
    }
}
