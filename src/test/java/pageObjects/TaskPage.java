package pageObjects;

import org.openqa.selenium.WebDriver;
import utilities.CommonUtils;

public class TaskPage extends BasePage{
    CommonUtils commonUtils;

    public TaskPage(WebDriver driver) {
        super(driver);
        commonUtils = new CommonUtils(driver);
    }

    public String shippingTaskChart = "//table[@id='purchaseUsersTable']";
   // public String accountNameShippingTaskChart = "//*[@id=\"purchaseUsersTable\"]/thead/tr/th[2]";
    public String sendContactAssigneeButton = "//button[@name='sendExpMail']";
    public String emailSentSuccessfulMessage = "//h2[@class='swal2-title']";
    public String emailSentSuccessfulMessageOkButton = "//button[@class='swal2-confirm swal2-styled swal2-default-outline']";
    public String accountNameShippingTaskChart = "table[@id='purchaseUsersTable']/tbody/tr";
}
