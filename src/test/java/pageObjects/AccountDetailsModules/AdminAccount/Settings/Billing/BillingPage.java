package pageObjects.AccountDetailsModules.AdminAccount.Settings.Billing;

public class BillingPage {

    public String billingContactDropdownField = "billing_contact_id"; //id
    public String billingContactDropdownOptions = "[id='billing_contact_id'] option"; //css
    public String billingEmailField = "billing_email"; //id
    public String billingPhoneField = "billing_phone"; //id
    public String billingCountryDropdownField = "billing_country"; //id
    public String billingAddress1Field = "[name='billing_line_one']"; //css
    public String billingAddress2Field = "[name='billing_line_two']"; //css
    public String billingCityField = "[name='city']"; //css
    public String billingStateField = "[name='state']"; //css
    public String billingPostalCodeField = "[name='postal_code']"; //css
    public String updateButtonField = "updateAccountDetails"; //id

}
