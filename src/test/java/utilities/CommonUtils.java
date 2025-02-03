package utilities;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import testBase.BaseClass;

public class CommonUtils extends BaseClass {
    WebDriver driver;

    public CommonUtils(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findElement(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            System.out.println("Element not found within timeout: " + locator);
            return null;
        }
    }

    public WebElement findElementById(String locatorValue) {
        return findElement(By.id(locatorValue));
    }

    public WebElement findElementByName(String locatorValue) {
        return findElement(By.name(locatorValue));
    }

    public WebElement findElementByCssSelector(String locatorValue) {
        return findElement(By.cssSelector(locatorValue));
    }

    public WebElement findElementByXpath(String locatorValue) {
        return findElement(By.xpath(locatorValue));
    }

    public List<WebElement> findElements(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (TimeoutException e) {
            System.out.println("Elements not found within timeout: " + locator);
            return null;
        }
    }

    public List<WebElement> findElementsByCssSelector(String locatorValue) {
        return findElements(By.cssSelector(locatorValue));
    }

    public List<WebElement> findElementsByXpath(String locatorValue) {
        return findElements(By.xpath(locatorValue));
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

    //SessionValid
    public boolean isSessionValid() {
        try {
            driver.getTitle(); // Perform a lightweight operation to check session
            return true;
        } catch (Exception e) {
            return false; // Session is invalid
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

    public void clickRadioButton(WebElement element) {
        waitForElementToBeVisible(element, 5);
        if (!element.isSelected()) {
            element.click();
        }
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

    public void createUser(WebElement newUserNameField, WebElement mobileCountryCodeDropDown, List<WebElement> countryList,
                           WebElement phoneNumberField, WebElement emailField, WebElement roleDropDown, WebElement userTypeDropDown,
                           WebElement licenseIDField, WebElement credentialsField, WebElement unselectedBundlesList, WebElement rightArrow,
                           WebElement selectedBundleField, List<WebElement> additionalPriviligesCheckboxesList, List<WebElement> supportManagerPriviligesCheckboxesList,
                           WebElement userTimeZoneRadioButton, WebElement userDefaultTimeZoneDropDown, WebElement activeCheckbox, WebElement saveButton,
                           WebElement dialogueText, WebElement dialogueOkButton, String randomUser, String mobileCountryCode, String randomPhoneNumber,
                           String role, String userType, String licenseID, String credentials, String unselectedBundle, String defaultTimeZone, String dialogueTextExpected) {

        enterValueInTextField(newUserNameField, randomUser);
        clickOnElement(mobileCountryCodeDropDown, null);
        selectDropDownValueWithClick(countryList, mobileCountryCode);
        enterValueInTextField(phoneNumberField, randomPhoneNumber);
        enterValueInTextField(emailField, randomUser + "@gmail.com");
        selectDropDownValue(roleDropDown, role);
        selectDropDownValue(userTypeDropDown, userType);

        // Handling specific logic for different user types
        switch(userType) {
            case "Account Admin":
            case "Admin":
            case "Support Entity Admin":
                break;
            case "Provider":
            case "Medical Staff":
                enterValueInTextField(licenseIDField, licenseID);
                enterValueInTextField(credentialsField, credentials);
                break;
            case "Observer":
                selectDropDownValue(unselectedBundlesList, unselectedBundle);
                clickOnElement(rightArrow, null);
                validateInputText(selectedBundleField, unselectedBundle);
                break;
            case "Manager":
                selectRandomCheckboxes(additionalPriviligesCheckboxesList);
                break;
            case "Support Entity Manager":
                selectRandomCheckboxes(supportManagerPriviligesCheckboxesList);
                break;
            default:
                System.out.println("User type is not valid");
                break;
        }

        validateRadioButton(userTimeZoneRadioButton);
        selectDropDownValue(userDefaultTimeZoneDropDown, defaultTimeZone);
        validateCheckbox(activeCheckbox);
        scrollToBottomAndClick(saveButton);
        validateGetText(dialogueText, dialogueTextExpected);
        clickOnElement(dialogueOkButton, "Ok");
    }

    public void scrollToElement(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            waitForElementToBeVisible(element, 10);
        } catch (Exception e) {
            throw new RuntimeException("Failed to scroll to element: " + e.getMessage(), e);
        }
    }

    public void uploadFile(WebElement element, String filePath) {
        File file = new File(filePath);

        if (file.exists()) {
            waitForElementToBeClickable(element, 10);
            element.sendKeys(filePath);
        } else {
            System.out.println("File not found at: " + filePath);
        }
    }

    //Changes
    public void clickSelectButton( String availableFieldSelectButton) {
        WebElement selectButton = findElementByXpath(availableFieldSelectButton);
        if (selectButton.isDisplayed()) {
            selectButton.click();
        }
    }

    // Method to scroll up to half of the page
    public void scrollToBottom() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(1000); // Small delay to allow rendering
        } catch (Exception e) {
            System.err.println("Error scrolling to bottom: " + e.getMessage());
        }
    }

    public void selectBundles(String bundleName) {
        clickOnElement(findElementByXpath("//div[@id='unselected_bundles']//div[span[text()='" + bundleName + "']]"), null);
        System.out.println("Clicked on field: " + bundleName);
    }

    public void selectDynamicField(String fieldName, String dynamicLocatorPattern) {
        String formattedLocator = String.format(dynamicLocatorPattern, fieldName);

        try {
            WebElement fieldElement = driver.findElement(By.xpath(formattedLocator));
            fieldElement.click();
            System.out.println("Clicked on field: " + fieldName);
        } catch (NoSuchElementException e) {
            System.err.println("Field with name '" + fieldName + "' not found.");
        }

    }

    public void handleCityAndState(String countryName, WebElement cityElement, WebElement stateElement, WebElement cityDropdownElement, WebElement stateDropdownElement, String cityName, String stateName) {
        if (countryName.equals("United States")) {
            if (cityDropdownElement != null && stateDropdownElement != null) {
                selectDropDownValue(cityDropdownElement, cityName);
                selectDropDownValue(stateDropdownElement, stateName);
            } else {
                System.out.println("City or State dropdown not found!");
            }
        } else {
            if (cityElement != null && stateElement != null) {
                enterValueInTextField(cityElement, cityName);
                enterValueInTextField(stateElement, stateName);
            } else {
                System.out.println("City or State input fields not found!");
            }
        }
    }
    //Method for scroll up
    public void scrollToUp()
    {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(1000); // Small delay to allow rendering
        } catch (Exception e) {
            System.err.println("Error scrolling to top: " + e.getMessage());
        }
    }

}

