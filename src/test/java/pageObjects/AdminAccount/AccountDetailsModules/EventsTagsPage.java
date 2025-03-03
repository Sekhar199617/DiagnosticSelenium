package pageObjects.AdminAccount.AccountDetailsModules;

import org.openqa.selenium.WebDriver;

import pageObjects.BasePage;
import pageObjects.PurchaserAccount.AccountPage;
import utilities.CommonUtils;

public class EventsTagsPage extends BasePage {

    CommonUtils commonUtils;
    AccountPage pl;

    public EventsTagsPage(WebDriver driver) {
        super(driver);
        commonUtils = new CommonUtils(driver);
        pl = new AccountPage(driver);
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
    public String saveButton = "//button[@name='submit_event']";
    public String deleteActionButton = "//ul[@class='dropdown-menu show']//span[@class='dropdown-item'][normalize-space()='Delete']";
    public String addFormButton = "//a[normalize-space()='Add']";
    public String updateEventButton = "//button[normalize-space()='Update']";
    public String formLibraryTable = "//table[@id='globalFormTable']";
    public String formScope = "(//div[normalize-space()='Event'])[1]";
    public String formBackButton = "//a[normalize-space()='Back']";

    public void formLibraryDisplayed (){
        if (pl.isElementDisplayed(commonUtils.findElementByXpath(formLibraryTable))){
            System.out.println("Form Library is displayed");
        }
        else {
            System.out.println("Form Library is not displayed");
        }
    }
}