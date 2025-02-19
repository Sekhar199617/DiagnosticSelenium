package pageObjects;

import org.openqa.selenium.WebDriver;

public class ContactUsPage {

    public String nameField = "(//input[@id='name'])[2]";
    public String emailField = "(//input[@id='email'])[2]";
    public String phoneField = "(//input[@id='phone'])[2]";
    public String companyOrAccountField = "(//input[@id='company_account'])[2]";
    public String preferredContactMethodDropdown = "(//select[@id='contact_method'])[2]";
    public String questionTypeDropdown = "(//select[@id='question_type'])[2]";
    public String messageField = "(//textarea[@id='question_message'])[2]";
    public String submitRequestButton = "//button[@id='addContactUsForm']";

}
