package testCases.AdminAccount.AccountDetailsModulesTests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.AccountDetailsModules.GroupsPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.nio.file.Paths;

public class GroupsTest extends BaseClass {

    @Test(groups = { "Smoke" })
    public void verify_create_group() {
        try {
            logger.info("****** Starting Create Group Test Case ******");

            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            dp.searchForItem(p.getProperty("accountName"));
            dp.clickView();

            AccountDetailsPage ad = new AccountDetailsPage(driver);
            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Groups");

            GroupsPage gp = new GroupsPage(driver);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(gp.addGroupButton), null);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(gp.groupNameField),randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(gp.orderingModeDropdown),p.getProperty("orderingModeDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(gp.selectLogImageDropdown),p.getProperty("selectLogImageDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(gp.discountModeDropdown),p.getProperty("discountModeDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(gp.bundlesAndExperienceDropdown),p.getProperty("bundlesAndExperienceDropdown"));

            Thread.sleep(1000);

            String communicationPreferencesType = p.getProperty("communicationPreferences");
            if (communicationPreferencesType.equalsIgnoreCase("Immediately")) {
                commonUtils.clickRadioButton(commonUtils.findElementByXpath(gp.immediateCommunicationPreferenceRadioButton));
            } else if (communicationPreferencesType.equalsIgnoreCase("Manually (Later)")) {
                commonUtils.clickRadioButton(commonUtils.findElementByXpath(gp.manuallyCommunicationPreferenceRadioButton));
            } else {
                throw new IllegalArgumentException(
                        "Invalid account type specified in config.properties: " + communicationPreferencesType);
            }
            commonUtils.clickOnElement(commonUtils.findElementByXpath(gp.uploadCSV),null);
            WebElement fileUploadElement = commonUtils.findElementByXpath(gp.fileUploadInput);
            String filePath = Paths.get("src/test/resources/group_member.csv").toAbsolutePath().toString();
            commonUtils.uploadFile(fileUploadElement, filePath);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(gp.uploadButton),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(gp.reviewMembersButton),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(gp.okButton),null);
            commonUtils.scrollToBottom();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(gp.saveGroupButton),null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(gp.successfulMessage),p.getProperty("createdSuccessfullyMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(gp.successfulOkButton),null);

            logger.info("****** Finished Create Group Test Case ******");
        } catch (Exception e) {
            Assert.fail();
        }
    }
}
