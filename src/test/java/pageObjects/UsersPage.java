package pageObjects;
import org.openqa.selenium.WebDriver;

public class UsersPage extends BasePage{
    public UsersPage(WebDriver driver) {
        super(driver);
    }

    public String createUserButton = "//a[normalize-space()='Create User']";
}
