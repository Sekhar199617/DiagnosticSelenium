package pageObjects.AdminAccount.HamburgerMenuModules;

import org.openqa.selenium.WebDriver;
import pageObjects.BasePage;

public class EntitiesPage extends BasePage {

    public EntitiesPage(WebDriver driver) {
        super(driver);
    }

    public String addSupportEntityButton = "//a[normalize-space()='Add Support Entity']";
    public String entityNameField = "//div[@class='col-md-4']//input[@id='name']";
    public String typeDropDownField = "support_entity_category_id"; //name
    public String tabList = "div[id='nav-tab'] button";

    //Authentication
    public String permitAccountSupportCheckbox = "permit_account_support"; //id
    public String activeOutboundApiCheckbox = "active_outbound_api"; //id
    public String productionApiUrlField = "production_api_url"; //id
    public String productionApiKeyField = "production_api_key"; //id
    public String productionApiSecretField = "production_api_secret"; //id
    public String activeOutboundWebhookCheckbox = "active_outbound_webhook"; //id
    public String productionWebhookUrlField = "outbound_webhook_url"; //id
    public String productionWebhookApiKeyField = "outbound_webhook_key"; //id
    public String acceptInboundWebhookCheckbox = "accept_inbound_webhook"; //id
    public String authenticationApiKeyRadioButton = "(//input[@id='inbound_api_key'])[1]"; //xpath
    public String authenticationApiKeyField = "input[name='auth_api_key']"; //css

    //Comms
    public String entityCommunicationCheckboxList = "div[class='entity_communications'] input"; //css

    //Contacts
    public String contactNameField = "entity_contact_name"; //id
    public String emailAddressField = "entity_contact_email"; //id
    public String officePhoneField = "entity_contact_phone"; //id
    public String mobilePhoneField = "entity_contact_mobile"; //id
    public String addressField = "entity_contact_address"; //id
    public String cityField = "entity_contact_city"; //id
    public String stateDropdownField = "entity_contact_state"; //id
    public String zipcodeField = "entity_contact_zip"; //id

    public String billingContactNameField = "billing_contact_name"; //id
    public String billingContactEmailField = "billing_contact_email"; //id
    public String billingContactOfficePhoneField = "billing_contact_phone"; //id
    public String billingContactMobilePhoneField = "billing_contact_mobile"; //id
    public String billingContactAddressField = "billing_contact_address"; //id
    public String billingContactCityField = "billing_contact_city"; //id
    public String billingContactStateDropdownField = "billing_contact_state"; //id
    public String billingContactZipcodeField = "billing_contact_zip"; //id
    public String paymentTermsDropdownField = "payment_terms"; //id
    public String activeCheckbox = "//input[@id='active']";
    public String saveButton = "addSupportEntityForm"; //id
}
