package utilities;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);

        waitForElementToBeVisible(element, 5);
        waitForElementToBeClickable(element, 5);
        if (elementText != null) {
            if (element.getText().equalsIgnoreCase(elementText)) {
                element.click();
            }
        } else {
            element.click();
        }
    }
    
    public void enterValueInTextField(WebElement element, String value) {
        waitForElementToBeClickable(element, 5);
        element.click();
        element.clear();
        element.sendKeys(value);
    }

    public void waitForElementToBeClickable(WebElement element, int waitingTime) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitingTime));
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void waitForElementToBeVisible(WebElement element, int waitingTime) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitingTime));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void selectTab(List<WebElement> element, String tabName) {
    	for(WebElement tab: element) {
    		String tabText = tab.getText();
            if (tabText.equalsIgnoreCase(tabName)) {
                waitForElementToBeClickable(tab, 5);
                tab.click();
                break;  
            }
    	}
    }

    public void selectDropDownValueWithClick(List<WebElement> element, String countryName) {
        for(WebElement country : element) {
            String countryText = country.getText();
            if(countryText.equalsIgnoreCase(countryName)) {
                waitForElementToBeClickable(country, 5);;
                country.click();
                break;
            }
        }
    }
    
    public void selectDropDownValue(WebElement element, String text) {
        waitForElementToBeVisible(element, 5);
    	Select dropdown = new Select(element);
    	dropdown.selectByVisibleText(text);
    }

    public void validateRadioButton(WebElement element) {
        waitForElementToBeVisible(element, 5);
    	element.isSelected();
    }
    
    public void validateCheckbox(WebElement element) {
        waitForElementToBeVisible(element, 5);
    	element.isSelected();
    }
    
    public void validateInputText(WebElement element, String expectedMessage) {
        waitForElementToBeVisible(element, 5);
    	String actualMessage = element.getDomProperty("value");
    	Assert.assertEquals(actualMessage, expectedMessage);
    }
    
    public void validateGetText(WebElement element, String expectedMessage) {
        waitForElementToBeVisible(element, 10);
    	String actualMessage = element.getText().trim();
    	Assert.assertEquals(actualMessage, expectedMessage);
    }
    
    public void selectRandomCheckboxes(List<WebElement> element) {
    	Random random = new Random();
        int randomIndex = random.nextInt(element.size());

        WebElement selectCheckbox = element.get(randomIndex);
        waitForElementToBeVisible(selectCheckbox, 5);
        selectCheckbox.click();
    }

    public String getTextFromElement(WebElement element) {
        waitForElementToBeVisible(element, 5);
        return element.getText();
    }

    public void scrollToBottomAndClick(WebElement element) {
        try {
            // Scroll to the bottom of the page in small increments
            JavascriptExecutor js = (JavascriptExecutor) driver;
            for (int i = 0; i < 10; i++) {
                js.executeScript("window.scrollBy(0, 500);");  // Scroll down by 500px at a time
                Thread.sleep(500);  // Wait for the page to load and update the DOM
            }
            // Now scroll to the element to make sure it's in view
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            waitForElementToBeVisible(element,10);
            element.click();
        } catch (Exception e) {
            System.err.println("Error during scrolling and clicking the element: " + e.getMessage());
        }
    }

    public void selectRadioButton(WebElement radioButton) {
        waitForElementToBeClickable(radioButton, 5);
        if (!radioButton.isSelected()) {
            radioButton.click();
        }
    }
}