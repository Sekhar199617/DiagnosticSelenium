package testCases.AdminAccount.AccountDetailsModulesTests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.AccountDetailsModules.GroupsPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;
import java.nio.file.Paths;

public class GroupsTest extends BaseClass {

    @Test(groups = { "Smoke" })
    public void verifyCreateGroup() {
        try {
            logger.info("****** Starting Create Group Test Case ******");

            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            GroupsPage groupsPage = new GroupsPage(driver);
            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            AccountDetailsPage ad = new AccountDetailsPage(driver);

            dp.searchForItem(p.getProperty("accountName"));
            dp.clickView();

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Groups");
            commonUtils.clickOnElement(commonUtils.findElementByXpath(groupsPage.addGroupButton), null);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(groupsPage.groupNameField),randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(groupsPage.orderingModeDropdown),
                    p.getProperty("orderingModeDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(groupsPage.selectLogImageDropdown),
                    p.getProperty("selectLogImageDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(groupsPage.discountModeDropdown),
                    p.getProperty("discountModeDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(groupsPage.bundlesAndExperienceDropdown),
                    p.getProperty("bundlesAndExperienceDropdown"));

            Thread.sleep(1000);

            String communicationPreferencesType = p.getProperty("communicationPreferences");
            if (communicationPreferencesType.equalsIgnoreCase("Immediately")) {
                commonUtils.clickRadioButton(commonUtils.findElementByXpath(
                        groupsPage.immediateCommunicationPreferenceRadioButton));
            } else if (communicationPreferencesType.equalsIgnoreCase("Manually (Later)")) {
                commonUtils.clickRadioButton(commonUtils.findElementByXpath(
                        groupsPage.manuallyCommunicationPreferenceRadioButton));
            } else {
                throw new IllegalArgumentException(
                        "Invalid account type specified in config.properties: " + communicationPreferencesType);
            }
            commonUtils.clickOnElement(commonUtils.findElementByXpath(groupsPage.uploadCSV),null);
            WebElement fileUploadElement = commonUtils.findElementByXpath(groupsPage.fileUploadInput);
            String filePath = Paths.get("src/test/resources/group_member.csv").toAbsolutePath().toString();
            commonUtils.uploadFile(fileUploadElement, filePath);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(groupsPage.uploadButton),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(groupsPage.reviewMembersButton),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(groupsPage.okButton),null);
            commonUtils.scrollToBottom();
            commonUtils.clickOnElement(commonUtils.findElementByXpath(groupsPage.saveGroupButton),null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(groupsPage.successfulMessage),
                    p.getProperty("createdSuccessfullyMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(groupsPage.successfulOkButton),null);

            logger.info("****** Finished Create Group Test Case ******");
        } catch (Exception e) {
            Assert.fail();
        }
    }
}