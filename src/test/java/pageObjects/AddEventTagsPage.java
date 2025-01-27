package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.CommonUtils;

public class AddEventTagsPage extends BasePage {

	CommonUtils commonUtils;

	public AddEventTagsPage(WebDriver driver) {
		super(driver);
		commonUtils = new CommonUtils(driver);
	}
	
	@FindBy (xpath = "//a[@href='events.php?accountId=48&mode=add']")
	public WebElement addEventButton;

	@FindBy(xpath = "/html/body/div[6]/div[2]/div[1]/form/div/div[1]/div[2]/input[1]")
	public WebElement eventNameTagField;

	@FindBy(xpath = "//input[@id='event_start_date']")
	public WebElement eventStartDateCalender;

	@FindBy(xpath = "//input[@id='event_end_date']")
	public WebElement eventEndDateCalender;

	@FindBy(xpath = "//input[@name='event_start_time']")
	public WebElement eventStartTime;

	@FindBy(xpath = "//input[@name='event_end_time']")
	public WebElement eventEndtTime;

	@FindBy(xpath = "//input[@name='testing_window_end_date']")
	public WebElement testing_windowEndDateCalender;

	@FindBy(xpath = "//input[@name='testing_window_start_date']")
	public WebElement testing_windowStartDateCalender;

	@FindBy(xpath = "//input[@name='testing_window_start_time']")
	public WebElement testing_windowStartTime;

	@FindBy(xpath = "//input[@name='testing_window_end_time']")
	public WebElement testing_windowEndtTime;

	@FindBy(xpath = "/html/body/div[6]/div[2]/div[1]/form/div/div[4]/div[2]/input")
	public WebElement physicalLocationField;

	@FindBy(xpath = "/html/body/div[6]/div[2]/div[1]/form/div/div[5]/div[2]/input")
	public WebElement eventUrlField;

	@FindBy(xpath = "//button[@name='submit_event']")
	public WebElement saveButton;

	@FindBy(xpath = "//*[@id=\"event-form\"]/div/div[7]/a")
	public WebElement cancelButton;


}