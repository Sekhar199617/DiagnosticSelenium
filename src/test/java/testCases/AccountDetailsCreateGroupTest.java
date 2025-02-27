package testCases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsPage;
import pageObjects.AccountDetailsAddGroupPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.nio.file.Paths;

public class AccountDetailsCreateGroupTest extends BaseClass {

    public DashboardPage dp;
    public CommonUtils commonUtils;
    public AccountDetailsPage ad;
    public AccountDetailsAddGroupPage gp;
    public  String jsonPath;


    @Test(groups = { "Smoke" })
    public void verify_create_group() {
        try {
            logger.info("****** Starting Create Group Test Case ******");

            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            dp = new DashboardPage(driver);
            commonUtils = new CommonUtils(driver);
            ad = new AccountDetailsPage(driver);
            gp = new AccountDetailsAddGroupPage(driver);
            jsonPath = "./testData//accountDetailsData.json";
            loadTestData(jsonPath);

            dp.searchForItem(getTestData("accountName"));
            dp.clickView();

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Groups");
            commonUtils.clickOnElement(commonUtils.findElementByXpath(gp.addGroupButton), null);
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(gp.groupNameField),randomString());
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(gp.orderingModeDropdown),getTestData("orderingModeDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(gp.selectLogImageDropdown),getTestData("selectLogImageDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(gp.discountModeDropdown),getTestData("discountModeDropdown"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(gp.bundlesAndExperienceDropdown),getTestData("bundlesAndExperienceDropdown"));

            String communicationPreferencesType = getTestData("communicationPreferences");
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
            commonUtils.clickOnElement(commonUtils.findElementByXpath(gp.saveGroupButton),null);
            commonUtils.validateDialogueTextAndClickConfirm(commonUtils.findElementByXpath(gp.successfulMessage),getTestData("createdSuccessfullyMessage"),commonUtils.findElementByXpath(gp.successfulOkButton));

            logger.info("****** Finished Create Group Test Case ******");
        } catch (Exception e) {
            Assert.fail();
        }
    }
}
