package testCases.PurchaserAccount;

import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import pageObjects.PurchaserAccount.AccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DashboardUploadCSVTest extends BaseClass {
    public CommonUtils commonUtils;
    public DashboardPage dp;
    public AccountDetailsPage ad;
    public AccountPage pl;

    @Test(groups= {"Smoke"})
    public void verifyAddAssigneeWithCSV() {

        logger.info("****** Starting Add Assignee With Upload CSV Test ******");
        try{

            loadTestData("./testData/PurchaserAccountData/purchaser.json",
                    "./testData/AdminAccountData/accountDetailsData.json",
                    "./testData/AdminAccountData/adminLoginData.json");

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            dp = new DashboardPage(driver);
            commonUtils = new CommonUtils(driver);
            ad = new AccountDetailsPage(driver);
            pl = new AccountPage(driver);

            dp.searchForItem(getTestData("accountName"));
            dp.clickView();
            //Navigate to Users & roles tab

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");

            //Select account admin in user type dropdown

            pl.performTableAction("accountsTableUserRoles", getTestData("userAccountAdminName"), "Assign Tests",1);

            //Switch the tab
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            //Click on logo
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.diagnosticLogo),null);
            //Click on upload csv excel button
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.uploadCsvExcelButton),null);
            WebElement fileUploadElement = commonUtils.findElementByXpath(pl.chooseFileButton);
            String filePath = Paths.get("src/test/resources/userAssignee.csv").toAbsolutePath().toString();
            commonUtils.uploadFile(fileUploadElement, filePath);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.uploadCsvButton),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.assignBundleButton),null);
            //Select assign bundles from dropdown
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.assigneeType),getTestData("userUploadAssigneeType"));
            //Select Experience Type
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.experienceAssigneeUploadDropdown),getTestData("userUploadAssigneeExperience"));
            Thread.sleep(1000);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.numberToAssignCompleteUploadButton),null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(pl.userUploadValidationMessage),getTestData("userAssigneeUploadValidationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.userUploadOkButton),null);

        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Add Assignee With Upload CSV Test ******");
    }
}

