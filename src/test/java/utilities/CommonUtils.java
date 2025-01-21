package utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CommonUtils {
    WebDriver driver;
    
    public CommonUtils(WebDriver driver) {
        this.driver = driver;
    }
    
    public void sendKeys(WebElement element, String value) {
        element.sendKeys(value);
    }
    
    public void click(WebElement element) {
        element.click();
    }
    
    public String getText(WebElement element) {
        return element.getText();
    }
    
    public void validateText(String actualAlertMessage, String expectedAlertMessage)
    {
        Assert.assertEquals(actualAlertMessage, expectedAlertMessage);
    }
}
