package utilities;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CommonUtils {
    WebDriver driver;
    
    public CommonUtils(WebDriver driver) {
        this.driver = driver;
    }
    
    public void clickOnElement(WebElement element, String elementText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        if (elementText != null) {
            if (element.getText().equalsIgnoreCase(elementText)) {
                element.click();
            }
        } else {
            element.click();
        }
    }
    
    public void enterValueInTextField(WebElement element, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        element.clear();
        element.sendKeys(value);
    }
    
    public void validateText(String actualAlertMessage, String expectedAlertMessage) {
        Assert.assertEquals(actualAlertMessage, expectedAlertMessage);
    }
    
    public void search(String searchElement, WebElement elementField, WebElement searchButton) {
    	elementField.sendKeys(searchElement);
    	searchButton.click();
    }
    
    public void selectTab(List<WebElement> element, String tabName) {
    	for(WebElement tab: element) {
    		String tabText = tab.getText();
            if (tabText.equalsIgnoreCase(tabName)) {
                tab.click();
                break;  
            }
    	}
    }
    
    public void selectMobileCountryCode(List<WebElement> element, String countryName) {
    	for(WebElement country : element) {
    		String countryText = country.getText();
    		if(countryText.equalsIgnoreCase(countryName)) {
    			country.click();
    			break;
    		}
    	}
    }
    
    public void selectDropDownValue(WebElement element, String text) {
    	Select dropdown = new Select(element);
    	dropdown.selectByVisibleText(text);
    }
    
    public void validateRadioButton(WebElement element) {
    	element.isSelected();
    }
    
    public void validateCheckbox(WebElement element) {
    	element.isSelected();
    }
    
    public void validateInputText(WebElement element, String expectedMessage) {
    	String actualMessage = element.getDomProperty("value");
    	Assert.assertEquals(actualMessage, expectedMessage);
    }
    
    public void validateGetText(WebElement element, String expectedMessage) {
    	String actualMessage = element.getText();
    	Assert.assertEquals(actualMessage, expectedMessage);
    }
    
    public void selectRandomCheckboxes(List<WebElement> element) {
    	Random random = new Random();
        int randomIndex = random.nextInt(element.size());
        
        WebElement selectedCheckbox = element.get(randomIndex);
        selectedCheckbox.click();
    }
    
}
