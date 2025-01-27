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

	@FindBy(xpath = "(//input[@id='messagingSetName'])[1]")
	public WebElement eventNameTagField;

	@FindBy(xpath = "//input[@id='event_start_date']")
	public WebElement eventStartDateCalender;

	@FindBy(xpath = "//input[@id='event_end_date']")
	public WebElement eventEndDateCalender;

	@FindBy(xpath = "//input[@name='event_start_time']")
	public WebElement eventStartTime;

	@FindBy(xpath = "//input[@name='event_end_time']")
	public WebElement eventEndTime;

	@FindBy(xpath = "//input[@name='testing_window_end_date']")
	public WebElement testingWindowEndDateCalender;

	@FindBy(xpath = "//input[@name='testing_window_start_date']")
	public WebElement testingWindowStartDateCalender;

	@FindBy(xpath = "//input[@name='testing_window_start_time']")
	public WebElement testingWindowStartTime;

	@FindBy(xpath = "//input[@name='testing_window_end_time']")
	public WebElement testingWindowEndTime;

	@FindBy(xpath = "(//input[@id='messagingSetName'])[2]")
	public WebElement physicalLocationField;

	@FindBy(xpath = "(//input[@id='messagingSetName'])[3]")
	public WebElement eventUrlField;

	@FindBy(xpath = "//button[@name='submit_event']")
	public WebElement saveButton;

	@FindBy(xpath = "//*[@id='event-form']/div/div[7]/a")
	public WebElement cancelButton;


}