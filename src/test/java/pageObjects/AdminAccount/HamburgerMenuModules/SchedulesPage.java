package pageObjects.AdminAccount.HamburgerMenuModules;

import org.openqa.selenium.WebDriver;
import pageObjects.BasePage;

public class SchedulesPage extends BasePage {

    public SchedulesPage(WebDriver driver) {
        super(driver);
    }

    public String editScheduleButton = "//a[normalize-space()='Edit Schedule']";
    public String newObserverButton = "//a[normalize-space()='New Observer']";
}
