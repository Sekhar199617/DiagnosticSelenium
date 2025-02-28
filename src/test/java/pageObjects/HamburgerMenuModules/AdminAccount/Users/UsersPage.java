package pageObjects.HamburgerMenuModules.AdminAccount.Users;
import org.openqa.selenium.WebDriver;
import pageObjects.BasePage;

public class UsersPage extends BasePage {
    public UsersPage(WebDriver driver) {
        super(driver);
    }

    public String createUserButton = "//a[normalize-space()='Create User']";
}
