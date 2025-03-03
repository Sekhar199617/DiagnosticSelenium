package testCases.AdminAccount.HamburgerMenuModulesTests;
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
            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);

            dp.selectHamburgerTab("Templates");

            TemplatesPage tp = new TemplatesPage(driver);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(tp.addButton), "Add");
            commonUtils.selectDropDownValue(commonUtils.findElementByName(tp.copyMessagingTemplateDropDown),
                    p.getProperty("copyMessagingTemplate"));
            commonUtils.enterValueInTextField(commonUtils.findElementById(tp.messagingSetNameField), randomString());
            commonUtils.validateCheckbox(commonUtils.findElementByName(tp.allowAccountsToSelectThisMessagingSet));
            commonUtils.enterValueInTextField(commonUtils.findElementByCssSelector(tp.webSiteNameField),
                    randomString() + " Site");
            commonUtils.enterValueInTextField(commonUtils.findElementByName(tp.emailFromNameForPurchasersField),
                    randomString() + "namepurch");
            commonUtils.enterValueInTextField(commonUtils.findElementByName(tp.emailFromAddressForPurchasersField),
                    randomString() + "addresspurch@gmail.com");
            commonUtils.enterValueInTextField(commonUtils.findElementByName(tp.emailFromNameForAssigneesField),
                    randomString() + "nameassign");

            commonUtils.enterValueInTextField(commonUtils.findElementByName(tp.emailFromAddressForAssigneesField),
                    randomString() + "addressassign@gmail.com");
            commonUtils.enterValueInTextField(commonUtils.findElementByName(tp.websiteURLField),
                    p.getProperty("websiteURL"));
            commonUtils.enterValueInTextField(commonUtils.findElementByName(tp.provisioningSystemURLField),
                    p.getProperty("provisioningSystemURL"));
            commonUtils.enterValueInTextField(commonUtils.findElementByName(tp.supportPhoneField), randomNumbers(10));
            String logoFilePath = System.getProperty("user.dir") + File.separator + "src" +
                    File.separator + "test" + File.separator + "resources" + File.separator + "Selenium_Logo.png";
            commonUtils.uploadFile(commonUtils.findElementByCssSelector(tp.logoFileUpload), logoFilePath);
            String faviconFilePath = System.getProperty("user.dir") + File.separator + "src" +
                    File.separator + "test" + File.separator + "resources" + File.separator + "Jenkins.png";
            commonUtils.uploadFile(commonUtils.findElementByCssSelector(tp.faviconFileUpload), faviconFilePath);
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(tp.addMessageSetButton));

            UsersAndRolesPage au = new UsersAndRolesPage(driver);
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(au.dialogueText),
                    p.getProperty("messageSetDialogueText"),
                    commonUtils.findElementByXpath(au.dialogueOkButton));
        }catch (Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Add new Message Set Test ******");
    }
}
