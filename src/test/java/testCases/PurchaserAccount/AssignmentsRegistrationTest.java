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

            AccountDetailsPage ad = new AccountDetailsPage(driver);
            ob = new AccountPage(driver);
            DashboardPage dp = new DashboardPage(driver);
            commonUtils = new CommonUtils(driver);

            loadTestData(
                    "./testData/AdminAccountData/adminLoginData.json",
                    "./testData/AdminAccountData/accountDetailsData.json",
                    "./testData/AdminAccountData/dashboardData.json",
                    "./testData/PurchaserAccountData/purchaser.json"
            );

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            dp.searchForItem(getTestData("accountName"));
            dp.clickView();

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");

            //Select account admin in user type dropdown
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.userTypeDropdown),
                    getTestData("userType"));

            //Clicking on Assign Test in action dropdown for a account
            ob.performTableAction("accountsTableUserRoles", getTestData("userAccountAdminName"),
                    "Assign Tests",1);

            //Switch the tab
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            //Click on logo
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.diagnosticLogo),null);

            dp.selectHamburgerTab("Assignments");
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.lastSessionDescArrow),null);
            ob.clickOnAssignmentView("assignmentsTable",getTestData("purchaserAssignmentNameToView"));

            String copiedFormName = ob.clickOnFormsLink("detailsAssignmentsTable",
                    getTestData("purchaserAssignmentNameToView"));
            System.out.println(copiedFormName + " copied");

            String copiedURL = ob.getClipboardText();

            UsersAndRolesPage au = new UsersAndRolesPage(driver);
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(au.dialogueText),
                    getTestData("observationLinkCopyValidationMessage"),
                    commonUtils.findElementByXpath(au.dialogueOkButton));

            ob.openNewTabWithURL(copiedURL);

            ob.clickTrainingButton();
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.firstNameRegistrationField),
                    randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.lastNameRegistrationField),
                    randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.mobileNumberNewOrderField),
                    randomNumbers(10));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.dateOfBirth),
                    getTestData("dateOfBirthRegistrationPage"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.genderDropdown),
                    getTestData("genderRegistrationPage"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.raceDropdown),
                    getTestData("raceRegistrationPage"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.ethnicityDropdown),
                    getTestData("ethnicityRegistrationPage"));
            commonUtils.scrollToBottom();
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.countryDropdownRegistration),
                    getTestData("countryRegistrationPage"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.shippingAddress1),
                    getTestData("shippingAddress1RegistrationPage"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.shippingAddress2),
                    getTestData("shippingAddress2RegistrationPage"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.cityField),
                    getTestData("cityRegistrationPage"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.stateInputFieldRegistration),
                    getTestData("stateRegistrationPage"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.postalCode),
                    getTestData("postalCodeRegistrationPage"));

            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.uploadFrontImageButton),null);
            WebElement frontImageFileUpload = commonUtils.findElementByXpath(ob.frontSideUploadChooseButton);
            String frontFilePath = Paths.get("src/test/resources/frontImage.png").toAbsolutePath().toString();
            commonUtils.uploadFile(frontImageFileUpload, frontFilePath);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.frontSideSaveButton),null);
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(au.dialogueText),
                    getTestData("uploadImageSuccessfulMessage"),
                    commonUtils.findElementByXpath(au.dialogueOkButton));

            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.uploadBackImageButton),null);
            WebElement backImageFileUpload = commonUtils.findElementByXpath(ob.backSideUploadChooseButton);
            String backFilePath = Paths.get("src/test/resources/backImage.png").toAbsolutePath().toString();
            commonUtils.uploadFile(backImageFileUpload, backFilePath);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.backSideSaveButton),null);
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(au.dialogueText),
                    getTestData("uploadImageSuccessfulMessage"),
                    commonUtils.findElementByXpath(au.dialogueOkButton));

            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.nextButton),null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(ob.registrationFormSubmission),
                    getTestData("purchaserFillRegistrationFormMessage"));

        } catch (Exception e) {
            Assert.fail();
        }

        logger.info("****** Finished Purchaser Fill Information For Registration Page  Test ******");
    }
}