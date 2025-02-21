package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsPage;
import pageObjects.AccountDetailsUsersAndRolesPage;
import pageObjects.DashboardPage;
import pageObjects.PurchaseLevelAccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PurchaserFillInformationForRegistrationPageTest extends BaseClass {

    public CommonUtils commonUtils;
    public PurchaseLevelAccountPage ob;

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
            ob = new PurchaseLevelAccountPage(driver);

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");

            //Select account admin in user type dropdown
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.userTypeDropdown), p.getProperty("usersUserTypeAccountAdmin"));

            //Clicking on Assign Test in action dropdown for a account
            ob.performActionOnUser("accountsTableUserRoles", p.getProperty("userAccountAdminName"), "Assign Tests");

            //Switch the tab
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            //Click on logo
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.diagnosticLogo),null);

            dp.selectHamburgerTab("Assignments");
            // commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.assignmentViewButton),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.lastSessionDate),null);
            ob.clickOnAssignmentView2("assignmentsTable",p.getProperty("purchaserAssignmentNameToClickView"),"Wellness Bundle for Company Test 1 for (Pro)");
            commonUtils.scrollToBottom();

            String copiedFormName = ob.clickOnObservationLinkCopy(p.getProperty("purchaserAssignmentNameToClickView"));
            System.out.println(copiedFormName + " copied");

            String copiedURL = ob.getClipboardText();

            AccountDetailsUsersAndRolesPage au = new AccountDetailsUsersAndRolesPage(driver);
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
            commonUtils.scrollToBottom();
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.ethnicityDropdown),p.getProperty("ethnicityRegistrationPage"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.countryDropdownRegistration),p.getProperty("countryRegistrationPage"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.shippingAddress1),p.getProperty("shippingAddress1RegistrationPage"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.shippingAddress2),p.getProperty("shippingAddress2RegistrationPage"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.cityField),p.getProperty("cityRegistrationPage"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.stateInputFieldRegistration),p.getProperty("stateRegistrationPage"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(ob.postalCode),p.getProperty("postalCodeRegistrationPage"));

            Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(15))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class);

            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.uploadFrontImageButton),null);

            // Upload Front Image
            WebElement frontImageFileUpload = fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ob.frontSideUploadChooseButton)));
            String frontFilePath = Paths.get("src/test/resources/frontImage.png").toAbsolutePath().toString();
            commonUtils.uploadFile(frontImageFileUpload, frontFilePath);

            //WebElement frontImageFileUpload = commonUtils.findElementByXpath(ob.frontSideUploadChooseButton);
            //String frontFilePath = Paths.get("src/test/resources/frontImage.png").toAbsolutePath().toString();
            //commonUtils.uploadFile(frontImageFileUpload, frontFilePath);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.frontSideSaveButton),null);
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(au.dialogueText),  p.getProperty("uploadImageSuccessfulMessage"),
                    commonUtils.findElementByXpath(au.dialogueOkButton));

            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.uploadBackImageButton),null);
//            WebElement backImageFileUpload = commonUtils.findElementByXpath(ob.backSideUploadChooseButton);
//            String backFilePath = Paths.get("src/test/resources/backImage.png").toAbsolutePath().toString();
//            commonUtils.uploadFile(backImageFileUpload, backFilePath);
            // Upload Back Image
            WebElement backImageFileUpload = fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ob.backSideUploadChooseButton)));
            String backFilePath = Paths.get("src/test/resources/backImage.png").toAbsolutePath().toString();
            commonUtils.uploadFile(backImageFileUpload, backFilePath);

            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.backSideSaveButton),null);
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(au.dialogueText),  p.getProperty("uploadImageSuccessfulMessage"),
                    commonUtils.findElementByXpath(au.dialogueOkButton));

            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.nextButton),null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(ob.registrationFormSubmission),p.getProperty("purchaserFillRegistrationFormMessage"));

            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.getHelpButton),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.textChatOption),null);
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//            WebElement closeChatButton = wait.until(ExpectedConditions.visibilityOf(commonUtils.findElementByXpath(ob.closeChatButton)));
//            closeChatButton.click();
           // commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.closeChatButton),null);



            WebElement closeButton = fluentWait.until(ExpectedConditions.elementToBeClickable(commonUtils.findElementByXpath(ob.closeChatButton)));
            closeButton.click();
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(ob.successfulConfirmationMessage),p.getProperty("closeChatValidationMessage"),commonUtils.findElementByXpath(ob.successfulConfirmationOkButton));

            commonUtils.validateGetText(commonUtils.findElementByXpath(ob.registrationFormSubmission),p.getProperty("purchaserFillRegistrationFormMessage"));


        } catch (Exception e) {
            AssertJUnit.fail();
        }


        logger.info("****** Finished Purchaser Fill Information For Registration Page  Test ******");
    }
}
