package testCases.PurchaserAccount;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.AccountDetailsModules.UsersAndRolesPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import pageObjects.PurchaserAccount.AccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AssignmentsRegistrationTest extends BaseClass {

    public CommonUtils commonUtils;
    public AccountPage ob;

    @Test(groups = {"Smoke"})
    public void VerifyPurchaserFillInformationForRegistrationPageTest() {

        logger.info("****** Starting Purchaser Fill Information For Registration Page Test ******");
        try {

            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);

            commonUtils = new CommonUtils(driver);
            dp.searchForItem(p.getProperty("accountName"));
            dp.clickView();

            AccountDetailsPage ad = new AccountDetailsPage(driver);
            ob = new AccountPage(driver);

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");

            //Select account admin in user type dropdown
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.userTypeDropdown), p.getProperty("usersUserTypeAccountAdmin"));

            //Clicking on Assign Test in action dropdown for a account
            ob.performTableAction("accountsTableUserRoles", p.getProperty("userAccountAdminName"), "Assign Tests",1);

            //Switch the tab
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            //Click on logo
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.diagnosticLogo),null);

            dp.selectHamburgerTab("Assignments");
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.lastSessionDescArrow),null);
            ob.clickOnAssignmentView("assignmentsTable",p.getProperty("purchaserAssignmentNameToView"));

            String copiedFormName = ob.clickOnFormsLink("detailsAssignmentsTable",p.getProperty("purchaserAssignmentNameToView"));
            System.out.println(copiedFormName + " copied");

            String copiedURL = ob.getClipboardText();

            UsersAndRolesPage au = new UsersAndRolesPage(driver);
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(au.dialogueText),  p.getProperty("observationLinkCopyValidationMessage"),
                    commonUtils.findElementByXpath(au.dialogueOkButton));

            ob.openNewTabWithURL(copiedURL);

            ob.clickTrainingButton();
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.firstNameRegistrationField),p.getProperty("firstNameRegistrationPage"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.lastNameRegistrationField),p.getProperty("lastNameRegistrationPage"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.mobileNumberNewOrderField),p.getProperty("mobileNumberRegistrationPage"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.dateOfBirth),p.getProperty("dateOfBirthRegistrationPage"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.genderDropdown),p.getProperty("genderRegistrationPage"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.raceDropdown),p.getProperty("raceRegistrationPage"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.ethnicityDropdown),p.getProperty("ethnicityRegistrationPage"));
            commonUtils.scrollToBottom();
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.countryDropdownRegistration),p.getProperty("countryRegistrationPage"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.shippingAddress1),p.getProperty("shippingAddress1RegistrationPage"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.shippingAddress2),p.getProperty("shippingAddress2RegistrationPage"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.cityField),p.getProperty("cityRegistrationPage"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.stateInputFieldRegistration),p.getProperty("stateRegistrationPage"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.postalCode),p.getProperty("postalCodeRegistrationPage"));

            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.uploadFrontImageButton),null);
            WebElement frontImageFileUpload = commonUtils.findElementByXpath(ob.frontSideUploadChooseButton);
            String frontFilePath = Paths.get("src/test/resources/frontImage.png").toAbsolutePath().toString();
            commonUtils.uploadFile(frontImageFileUpload, frontFilePath);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.frontSideSaveButton),null);
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(au.dialogueText),  p.getProperty("uploadImageSuccessfulMessage"),
                    commonUtils.findElementByXpath(au.dialogueOkButton));

            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.uploadBackImageButton),null);
            WebElement backImageFileUpload = commonUtils.findElementByXpath(ob.backSideUploadChooseButton);
            String backFilePath = Paths.get("src/test/resources/backImage.png").toAbsolutePath().toString();
            commonUtils.uploadFile(backImageFileUpload, backFilePath);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.backSideSaveButton),null);
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(au.dialogueText),  p.getProperty("uploadImageSuccessfulMessage"),
                    commonUtils.findElementByXpath(au.dialogueOkButton));

            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.nextButton),null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(ob.registrationFormSubmission),p.getProperty("purchaserFillRegistrationFormMessage"));

        } catch (Exception e) {
            Assert.fail();
        }


        logger.info("****** Finished Purchaser Fill Information For Registration Page  Test ******");
    }
}
