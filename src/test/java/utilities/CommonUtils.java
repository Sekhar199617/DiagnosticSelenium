package utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
}
