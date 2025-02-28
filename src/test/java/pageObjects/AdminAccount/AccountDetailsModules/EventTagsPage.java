package pageObjects.AdminAccount.AccountDetailsModules;

import org.openqa.selenium.WebDriver;
import pageObjects.BasePage;
import utilities.CommonUtils;

public class EventTagsPage extends BasePage {

    CommonUtils commonUtils;

    public EventTagsPage(WebDriver driver) {
        super(driver);
        commonUtils = new CommonUtils(driver);
    }

    public String addEventButton = "//a[@href='events.php?accountId=48&mode=add']";
    public  String eventNameTagField = "(//input[@id='messagingSetName'])[1]";
    public  String eventStartDateCalender = "//input[@id='event_start_date']";
    public  String eventEndDateCalender = "//input[@id='event_end_date']";
    public  String eventStartTime = "//input[@name='event_start_time']";
    public  String eventEndTime = "//input[@name='event_end_time']";
    public  String testingWindowEndDateCalender = "//input[@name='testing_window_end_date']";
    public  String testingWindowStartDateCalender = "//input[@name='testing_window_start_date']";
    public  String testingWindowStartTime = "//input[@name='testing_window_start_time']";
    public  String testingWindowEndTime = "//input[@name='testing_window_end_time']";
    public  String saveButton = "//button[@name='submit_event']";

}