package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsUsersAndRolesPage;
import pageObjects.DashboardPage;
import pageObjects.TemplatesPage;
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

            commonUtils.clickOnElement(dp.togglerIcon, null);
            commonUtils.selectTab(dp.hamburgerMenuList, "Templates");

            TemplatesPage tp = new TemplatesPage(driver);
            commonUtils.clickOnElement(tp.addButton, "Add");
            commonUtils.selectDropDownValue(tp.copyMessagingTemplateDropDown, p.getProperty("copyMessagingTemplate"));
            commonUtils.enterValueInTextField(tp.messagingSetNameField, randomString());
            commonUtils.validateCheckbox(tp.allowAccountsToSelectThisMessagingSet);
            commonUtils.enterValueInTextField(tp.webSiteNameField, randomString() + " Site");
            commonUtils.enterValueInTextField(tp.emailFromNameForPurchasersField, randomString() + "namepurch");
            commonUtils.enterValueInTextField(tp.emailFromAddressForPurchasersField, randomString() + "addresspurch@gmail.com");
            commonUtils.enterValueInTextField(tp.emailFromNameForAssigneesField, randomString() + "nameassign");

            commonUtils.enterValueInTextField(tp.emailFromAddressForAssigneesField, randomString() + "addressassign@gmail.com");
            commonUtils.enterValueInTextField(tp.websiteURLField, p.getProperty("websiteURL"));
            commonUtils.enterValueInTextField(tp.provisioningSystemURLField, p.getProperty("provisioningSystemURL"));
            commonUtils.enterValueInTextField(tp.supportPhoneField, randomNumbers());
            String logoFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "Selenium_Logo.png";
            commonUtils.uploadFile(tp.logoFileUpload, logoFilePath);
            String faviconFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "Jenkins.png";
            commonUtils.uploadFile(tp.faviconFileUpload, faviconFilePath);
            commonUtils.scrollToBottomAndClick(tp.addMessageSetButton);

            AccountDetailsUsersAndRolesPage au = new AccountDetailsUsersAndRolesPage(driver);
            commonUtils.validateGetText(au.dialogueText, p.getProperty("messageSetDialogueText"));
            commonUtils.clickOnElement(au.dialogueOkButton, "Ok");
        }catch (Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Add new Message Set Test ******");
    }
}
