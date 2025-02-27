package pageObjects.AccountDetailsModules;

public class SettingsCommunicationsPage {

    public String primaryContactNameField = "[name='purchaser_name']"; //css
    public String emailField = "purchaser_email"; //id
    public String phoneField = "input[name='phone'][type='number']"; //css
    public String messagingSetDropdown = "messagingset"; //id
    public String defaultIntakeFormDropdown = "default_intake_form"; //id
    public String ecommerceTransactionsDropdown = "ecommerce_communication_type_id"; //id
    public String assigneePortalRegistrationDropdown = "assignee_portal_communication_type_id"; //id
    public String shipmentCommunicationsDropdown = "shipement_communication_type_id"; //id
    public String generalCommunicationsDropdown = "general_communication_type_id"; //id
    public String testResultsDropdown = "test_result_communication_type_id"; //id
}
