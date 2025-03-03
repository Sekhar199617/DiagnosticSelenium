package pageObjects.AdminAccount.HamburgerMenuModules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.BasePage;
import utilities.CommonUtils;

import java.util.List;
import java.util.Properties;

public class EntityPage extends BasePage {
    CommonUtils commonUtils;
    Properties p;
    public String entityVariableName;

    public EntityPage(WebDriver driver) {
        super(driver);
        this.commonUtils = new CommonUtils(driver);
        this.p = p;
    }

    public String supportEntityChart = "//table[@id='supportEntityTable']/tbody/tr";
    public String AuthenticationTab = "//button[@id='nav-auth-tab']";
    public String updateSupportEntityButton = "//button[@id='addSupportEntityForm']";
    public String checkboxLocator = "//input[@id='permit_account_support']";
    public String supportEntityUpdateValidationMessage = "//h2[@id='swal2-title']";
    public String supportEntityUpdatedOKbutton = "//button[normalize-space()='Ok']";
    public String supportEntityCancelButton = "//button[@class='btn btn-secondary wbtwPrimaryBtn']";

    public void editSupportEntity(String entityTableXpath, String entityName, String actionText) {
        List<WebElement> rows = commonUtils.findElementsByXpath(entityTableXpath);

        for (WebElement row : rows) {
            WebElement nameCell = row.findElement(By.xpath("./td[1]"));

            if (nameCell.getText().trim().equals(entityName)) {
                System.out.println("Found entity: " + nameCell.getText());

                // Store the "Type" value
                entityVariableName = row.findElement(By.xpath("./td[2]")).getText().trim();
                System.out.println("Entity Type: " + entityVariableName);

                // Click the "Actions" button
                WebElement actionsButton = row.findElement(By.xpath(".//button[contains(text(),'Actions')]"));
                actionsButton.click();

                // Click the specific action (Edit/Delete/etc.)
                WebElement actionOption = row.findElement(By.xpath(".//a[contains(text(),'" + actionText + "')]"));
                actionOption.click();
                break;
            }
        }
    }


}
