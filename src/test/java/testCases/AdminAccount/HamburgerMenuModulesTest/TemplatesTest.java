package testCases.AdminAccount.HamburgerMenuModulesTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.AccountDetailsModules.UsersAndRolesPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import pageObjects.AdminAccount.HamburgerMenuModules.TemplatesPage;
import testBase.BaseClass;
import utilities.CommonUtils;
import java.io.File;

public class TemplatesTest extends BaseClass {

    @Test
    public void verifyAddNewMessageSet() {
        logger.info("****** Starting Add New Message Set Test ******");
        try{

            DashboardPage dashboardPage = new DashboardPage(driver);
            TemplatesPage templatesPage = new TemplatesPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            UsersAndRolesPage usersAndRolesPage = new UsersAndRolesPage(driver);

            String loginJsonPath = "./testData//adminLoginData.json";
            loadTestData(loginJsonPath);

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            String hamburgerMenuModulesDataJsonPath = "./testData//hamburgerMenuModulesData.json";
            loadTestData(hamburgerMenuModulesDataJsonPath);

            dashboardPage.selectHamburgerTab("Templates");

            commonUtils.clickOnElement(commonUtils.findElementByXpath(templatesPage.addButton), "Add");
            commonUtils.selectDropDownValue(commonUtils.findElementByName(templatesPage.copyMessagingTemplateDropDown),
                    getTestData("copyMessagingTemplate"));
            commonUtils.enterValueInTextField(commonUtils.findElementById(templatesPage.messagingSetNameField),
                    randomString());
            commonUtils.validateCheckbox(commonUtils.findElementByName(templatesPage.allowAccountsToSelectThisMessagingSet));
            commonUtils.enterValueInTextField(commonUtils.findElementByCssSelector(templatesPage.webSiteNameField),
                    randomString() + " Site");
            commonUtils.enterValueInTextField(commonUtils.findElementByName(templatesPage.emailFromNameForPurchasersField),
                    randomString() + "namepurch");
            commonUtils.enterValueInTextField(commonUtils.findElementByName(templatesPage.emailFromAddressForPurchasersField),
                    randomString() + "addresspurch@gmail.com");
            commonUtils.enterValueInTextField(commonUtils.findElementByName(templatesPage.emailFromNameForAssigneesField),
                    randomString() + "nameassign");

            commonUtils.enterValueInTextField(commonUtils.findElementByName(templatesPage.emailFromAddressForAssigneesField),
                    randomString() + "addressassign@gmail.com");
            commonUtils.enterValueInTextField(commonUtils.findElementByName(templatesPage.websiteURLField),
                    getTestData("websiteURL"));
            commonUtils.enterValueInTextField(commonUtils.findElementByName(templatesPage.provisioningSystemURLField),
                    getTestData("provisioningSystemURL"));
            commonUtils.enterValueInTextField(commonUtils.findElementByName(templatesPage.supportPhoneField),
                    randomNumbers(10));
            String logoFilePath = System.getProperty("user.dir") + File.separator + "src" +
                    File.separator + "test" + File.separator + "resources" + File.separator + "Selenium_Logo.png";
            commonUtils.uploadFile(commonUtils.findElementByCssSelector(templatesPage.logoFileUpload), logoFilePath);
            String faviconFilePath = System.getProperty("user.dir") + File.separator + "src" +
                    File.separator + "test" + File.separator + "resources" + File.separator + "Jenkins.png";
            commonUtils.uploadFile(commonUtils.findElementByCssSelector(templatesPage.faviconFileUpload), faviconFilePath);
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(templatesPage.addMessageSetButton));

            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(
                            usersAndRolesPage.dialogueText),
                    getTestData("messageSetDialogueText"),
                    commonUtils.findElementByXpath(usersAndRolesPage.dialogueOkButton)
            );
        }catch (Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Add new Message Set Test ******");
    }
}