package utilities;
import java.io.File;
import java.io.FileReader;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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

        waitForElementToBeVisible(element, 10);
        waitForElementToBeClickable(element, 10);
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
        boolean tabFound = false;

    	for(WebElement tab: element) {
    		String tabText = tab.getText();
            if (tabText.equalsIgnoreCase(tabName)) {
                waitForElementToBeClickable(tab, 10);
                tab.click();
                tabFound = true;
                break;
            }
    	}

        if (!tabFound) {
            Assert.fail("Tab with name '" + tabName + "' was not found");
        }
    }

    public void selectDropDownValueWithClick(List<WebElement> element, String name) {
        for(WebElement dropdownElement : element) {
            String dropdownElementText = dropdownElement.getText();
            if(dropdownElementText.equalsIgnoreCase(name)) {
                waitForElementToBeClickable(dropdownElement, 10);;
                dropdownElement.click();
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
        waitForElementToBeVisible(element, 10);
    	Select dropdown = new Select(element);
    	dropdown.selectByVisibleText(text);
    }

    public void validateSelectedDropdownValue(List<WebElement> options, String expectedContactName) {

        boolean isContactPresent = false;
        boolean isContactSelected = false;

        for (WebElement option : options) {
            if (option.getText().trim().equals(expectedContactName)) {
                isContactPresent = true;

                // Check if the option is pre-selected
                if (option.isSelected()) {
                    isContactSelected = true;
                    Assert.assertTrue(isContactSelected, "Option Selected By Default");
                }

                System.out.println("The contact '" + expectedContactName + "' is present in the dropdown.");
                break;
            }
        }
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
            JavascriptExecutor js = (JavascriptExecutor) driver;
            boolean elementFound = false;

            // Scroll the page in small increments and check for the element's visibility
            while (!elementFound) {
                js.executeScript("window.scrollBy(0, 500);");
                Thread.sleep(500);

                try {
                    js.executeScript("arguments[0].scrollIntoView(true);", element);
                    waitForElementToBeVisible(element, 10);  // Wait for element to be visible
                    element.click();
                    elementFound = true;
                } catch (Exception e) {
                    // If the element is not visible yet, continue scrolling
                    System.out.println("Element not found, scrolling more...");
                }
            }
        } catch (Exception e) {
            System.err.println("Error during scrolling and clicking the element: " + e.getMessage());
        }
    }


    public void selectRadioButton(WebElement radioButton) {
        waitForElementToBeClickable(radioButton, 10);
        if (!radioButton.isSelected()) {
            radioButton.click();
        }
    }

    public void selectCheckbox(WebElement checkbox) {
        scrollToElement(checkbox);
        waitForElementToBeClickable(checkbox, 5);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void createUser(String newUserNameField, String randomUser,
                           String mobileCountryCodeDropDown, String countryList,
                           String phoneNumberField, String emailField,
                           String roleDropDown, String userTypeDropDown, String licenseIDField,
                           String credentialsField, String bundlesNotAttachedField,
                           String rightArrow, String selectedBundleField,
                           String additionalPriviligesCheckboxesList,
                           String supportManagerPriviligesCheckboxesList,
                           String userTimeZoneRadioButton, String userDefaultTimeZoneDropDown,
                           String activeCheckbox, String saveButton, String dialogueText,
                           String dialogueOkButton, String mobileCountryCode,
                           String randomPhoneNumber, String role, String userType, String licenseID,
                           String credentials, String defaultTimeZone,
                           String dialogueTextExpected) {

        enterValueInTextField(findElementByXpath(newUserNameField), randomUser);
        clickOnElement(findElementByXpath(mobileCountryCodeDropDown), null);
        selectDropDownValueWithClick(findElementsByXpath(countryList), mobileCountryCode);
        enterValueInTextField(findElementByXpath(phoneNumberField), randomPhoneNumber);
        enterValueInTextField(findElementByXpath(emailField), randomUser + "@gmail.com");
        selectDropDownValue(findElementByXpath(roleDropDown), role);
        selectDropDownValue(findElementByXpath(userTypeDropDown), userType);

        switch(userType) {
            case "Account Admin":
            case "Admin":
            case "Support Entity Admin":
                break;
            case "Provider":
            case "Medical Staff":
                enterValueInTextField(findElementByXpath(licenseIDField), licenseID);
                enterValueInTextField(findElementByXpath(credentialsField), credentials);
                break;
            case "Observer":
                moveNotAttachedBundlesToAttached(bundlesNotAttachedField, rightArrow, selectedBundleField);
                break;
            case "Manager":
                selectRandomCheckboxes(findElementsByXpath(additionalPriviligesCheckboxesList));
                break;
            case "Support Entity Manager":
                selectRandomCheckboxes(findElementsByXpath(supportManagerPriviligesCheckboxesList));
                break;
            default:
                System.out.println("User type is not valid");
                break;
        }

        //validateRadioButton(findElementByXpath(userTimeZoneRadioButton));
        scrollToElement(findElementByXpath(userDefaultTimeZoneDropDown));
        selectDropDownValue(findElementByXpath(userDefaultTimeZoneDropDown), defaultTimeZone);
        validateCheckbox(findElementByXpath(activeCheckbox));
        scrollToBottomAndClick(findElementByCssSelector(saveButton));
        validateGetText(findElementByXpath(dialogueText), dialogueTextExpected);
        clickOnElement(findElementByXpath(dialogueOkButton), "Ok");
    }

    public void scrollToElement(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            waitForElementToBeVisible(element, 10);
            waitForElementToBeClickable(element, 10);
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

    public void moveNotAttachedBundlesToAttached(String bundlesNotAttachedField, String rightArrow,
                                                 String selectedBundleField) {
            WebElement unselectedDropdown = findElementByXpath(bundlesNotAttachedField);
            Select unselectedSelect = new Select(unselectedDropdown);
            List<WebElement> unselectedOptions = unselectedSelect.getOptions();
            int optionCount = unselectedOptions.size();

            Random random = new Random();
            String firstSelectedOptionText = "";
            String secondSelectedOptionText = "";

            if (optionCount == 1) {
                firstSelectedOptionText = unselectedOptions.get(0).getText();
                System.out.println("Only one option available: " + firstSelectedOptionText);
            } else if (optionCount > 1) {
                firstSelectedOptionText = unselectedOptions.get(random.nextInt(optionCount)).getText();

                // Ensure both selected options are different
                do {
                    secondSelectedOptionText = unselectedOptions.get(random.nextInt(optionCount)).getText();
                } while (firstSelectedOptionText.equals(secondSelectedOptionText));
                System.out.println("Randomly selected options: " + firstSelectedOptionText + " and " + secondSelectedOptionText);
            }

            unselectedSelect.selectByVisibleText(firstSelectedOptionText);
            if (!secondSelectedOptionText.isEmpty()) {
                unselectedSelect.selectByVisibleText(secondSelectedOptionText);
            }

            clickOnElement(findElementByXpath(rightArrow), null);

            // Validate that the options are moved to the selected bundles dropdown
            WebElement selectedBundlesDropdown = findElementById(selectedBundleField);
            List<WebElement> selectedBundlesOptions = selectedBundlesDropdown.findElements(By.tagName("option"));

            boolean isFirstOptionMoved = false;
            // If only one option is selected, don't validate second
            boolean isSecondOptionMoved = secondSelectedOptionText.isEmpty();

            // Check if the selected options exist in the selected bundles dropdown
            for (WebElement option : selectedBundlesOptions) {
                if (option.getText().equals(firstSelectedOptionText)) {
                    isFirstOptionMoved = true;
                }
                if (!secondSelectedOptionText.isEmpty() && option.getText().equals(secondSelectedOptionText)) {
                    isSecondOptionMoved = true;
                }
                // Break early if both options are found
                if (isFirstOptionMoved && isSecondOptionMoved) {
                    break;
                }
            }

            if (isFirstOptionMoved && isSecondOptionMoved) {
                System.out.println("Selected options moved successfully: " + firstSelectedOptionText + " and " + secondSelectedOptionText);
            } else {
                System.out.println("One or both options were not moved to the selected bundles.");
            }

    }

    public void validateDialogueTextAndClickConfirm(WebElement dialogueTextElement, String dialogueText,
                                                    WebElement confirmButtonElement) {
        validateGetText(dialogueTextElement, dialogueText);
        clickOnElement(confirmButtonElement, null);
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
// This method checks if a checkbox is selected. If not selected, it clicks to select it.

    public void validateAndClickCheckbox(String checkboxLocator) {
        WebElement checkbox = driver.findElement(By.xpath(checkboxLocator));

        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void validateAndClickRadiobutton (String radiobuttonLocator)
    {
        WebElement radioButton = driver.findElement(By.xpath(radiobuttonLocator));

        if (!radioButton.isSelected()){
            radioButton.click();
        }
    }

    public WebElement findUserAndClickActionsDropdown(String tableId, String accountName) {
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='" + tableId + "']/tbody/tr"));

        for (WebElement row : rows) {
            WebElement nameCell = row.findElement(By.xpath("./td[1]"));

            if (nameCell.getText().trim().equals(accountName)) {
                System.out.println("Found user: " + nameCell.getText());

                WebElement actionOption = row.findElement(By.xpath(".//button[contains(text(),'Action')]"));
                actionOption.click();

                return row; // Return the row for further actions
            }
        }
        return null; // User not found
    }

    public void closeExtraWindows() {
        if (driver != null) {
            String mainWindow = driver.getWindowHandle();
            Set<String> windowHandles = driver.getWindowHandles();

            for (String window : windowHandles) {
                if (!window.equals(mainWindow)) {
                    driver.switchTo().window(window);
                    driver.close();
                }
            }
            driver.switchTo().window(mainWindow);  // Keep one tab active
        }
    }

    public void selectRandomDropDown(List<WebElement> element) {
        Random random = new Random();
        int randomIndex = random.nextInt(element.size());

        WebElement selectDropdown = element.get(randomIndex);
        waitForElementToBeVisible(selectDropdown, 5);
        selectDropdown.click();
    }

}
