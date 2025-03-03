package testCases.AdminAccount.AccountDetailsModulesTests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.AdminAccount.AccountDetailsModules.FormsPage;
import pageObjects.AdminAccount.AccountDetailsModules.EventsTagsPage;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import pageObjects.PurchaserAccount.AccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.time.Duration;


public class EventsTagsTest extends BaseClass {

    public DashboardPage dp;
    public CommonUtils commonUtils;
    public AccountDetailsPage ad;
    public EventsTagsPage addEventTagsPage;
    public String eventName;
    public AccountPage pl;
    public  String jsonPath;
    public FormsPage addFormsPage;
    public String eventFormName;

    @BeforeMethod (groups = {"Smoke"})
    public void eventCommonStep(){

        dp = new DashboardPage(driver);
        commonUtils = new CommonUtils(driver);
        ad = new AccountDetailsPage(driver);
        addEventTagsPage = new EventsTagsPage(driver);
        pl = new AccountPage(driver);
        loadTestData(jsonPath);
        addFormsPage = new FormsPage(driver);

        loadTestData(
                "./testData/AdminAccountData/adminLoginData.json",
                "./testData/AdminAccountData/dashboardData.json",
                "./testData/AdminAccountData/accountDetailsData.json"
        );

        login(getTestData("adminEmail"), getTestData("adminPassword"), true);

        dp.searchForItem(getTestData("accountName"));
        dp.clickView();
        commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Events/Tags");

    }

    @Test(groups = { "Smoke" }, priority = 1)
    public void verifyAddEvent() {
        try {
            logger.info("****** Starting Add Event Test Case ******");
            eventName = randomString();
            loadTestData(jsonPath);
            // Fill Event Details
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addEventTagsPage.addEventButton), null);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.eventNameTagField), eventName );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.eventStartDateCalender), getTestData("eventStartDate"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.eventEndDateCalender), getTestData("eventEndDate") );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.eventStartTime), getTestData("eventStartTiming") );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.eventEndTime), getTestData("eventEndTiming") );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.testingWindowStartDateCalender), getTestData("testingWindowStartDate") );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.testingWindowEndDateCalender), getTestData("testingWindowEndDate"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.testingWindowStartTime), getTestData("testingWindowStartTiming") );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.testingWindowEndTime), getTestData("testingWindowEndTiming") );
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(addEventTagsPage.saveButton));
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(pl.successfulConfirmationMessage),getTestData("eventCreatedMessage"),commonUtils.findElementByXpath(pl.successfulConfirmationOkButton));

            logger.info("****** Finished Add Event Test Case ******");
        } catch (Exception e) {
            logger.error("Error occurred: " + e.getMessage());
            Assert.fail("Test failed due to exception.");
        }
    }

    @Test (groups = {"Smoke"}, priority = 3)
    public void verifyEditEvent() throws InterruptedException {
        try {
            logger.info("****** Starting Edit And Delete Event Test Case ******");

            pl.performTableAction("accountsTable", eventName, "Edit",1);
            loadTestData(jsonPath);
            //Click on edit for above created Event
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.eventStartDateCalender), getTestData("editEventStartDate"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.eventEndDateCalender), getTestData("editEventEndDate") );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.eventStartTime), getTestData("editEventStartTiming") );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.eventEndTime), getTestData("editEventEndTiming") );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.testingWindowStartDateCalender), getTestData("editTestingWindowStartDate") );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.testingWindowEndDateCalender), getTestData("editTestingWindowEndDate"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.testingWindowStartTime), getTestData("editTestingWindowStartTiming") );
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addEventTagsPage.testingWindowEndTime), getTestData("editTestingWindowEndTiming") );
            commonUtils.scrollToBottom();

            //Check form library is present or not
            addEventTagsPage.formLibraryDisplayed();

            pl.performTableAction("globalFormTable","Kiosk", "View", 1);
            String expectedFormScope = commonUtils.findElementByXpath(addEventTagsPage.formScope).getText();
            Assert.assertEquals(expectedFormScope,"Event", "Form Scope is Event");
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addEventTagsPage.formBackButton),null);
            commonUtils.scrollToBottom();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addEventTagsPage.updateEventButton),null);
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

            wait.until(ExpectedConditions.visibilityOf(commonUtils.findElementByXpath(pl.successfulConfirmationMessage)));

            //Delete created event
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(pl.successfulConfirmationMessage),getTestData("editSuccessfullyEventMessage"),commonUtils.findElementByXpath(pl.successfulConfirmationOkButton));
            commonUtils.findUserAndClickActionsDropdown("accountsTable",eventName);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addEventTagsPage.deleteActionButton),null);
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(pl.successfulConfirmationMessage),getTestData("deleteEventWarningMessage"),commonUtils.findElementByXpath(pl.successfulConfirmationOkButton));
            Thread.sleep(1000);
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(pl.successfulConfirmationMessage),getTestData("deleteEventMessage"),commonUtils.findElementByXpath(pl.successfulConfirmationOkButton));
            Thread.sleep(1000);

        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("****** Finished Edit And Delete Event Test Case ******");
    }

    @Test (groups = {"Smoke"}, priority = 2)
    public void verifyAddEditForm() throws InterruptedException {
        try{
            logger.info("****** Starting Add Edit Form Test Case ******");

            pl.performTableAction("accountsTable", eventName, "Edit",1);
            loadTestData(jsonPath);

            eventFormName = randomString();
            //Add form
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addEventTagsPage.addFormButton),null);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.formNameField), eventFormName);
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formEntityTypeDropdown), getTestData("formEntityTypeAccount"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.formTypeDropdown), getTestData("formTypeKiosk"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.formDescriptionField), randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(addFormsPage.selectLogoImageDropdown), getTestData("selectLogoImage"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.formInstructionField), randomString());
            commonUtils.scrollToBottom();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.addAccountFormFieldButton), null);
            commonUtils.scrollToBottom();
            System.out.println("Test");
            commonUtils.selectDynamicField(getTestData("availableFieldNameAccount"), addFormsPage.availableFieldAccount);
            System.out.println("Test2");
            commonUtils.clickSelectButton(addFormsPage.availableFieldSelectButton);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(addFormsPage.field1InstructionField), randomString());
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.saveField1Button), null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(addFormsPage.finishedButton), null);
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(pl.successfulConfirmationMessage),getTestData("createEventFormSuccessfulMessage"),commonUtils.findElementByXpath(pl.successfulConfirmationOkButton));


        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("****** Finished Add Edit Form Test Case ******");
    }


    @AfterMethod
    public void deleteCookies(){
        driver.manage().deleteAllCookies();
    }

}