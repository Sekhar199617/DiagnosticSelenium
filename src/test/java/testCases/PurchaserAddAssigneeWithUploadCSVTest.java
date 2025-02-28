package testCases;

import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.AccountDetailsModules.AdminAccount.AccountDetailsPage;
import pageObjects.AccountDetailsModules.AdminAccount.Dashboard.DashboardPage;
import pageObjects.PurchaseLevelAccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PurchaserAddAssigneeWithUploadCSVTest extends BaseClass {
    public CommonUtils commonUtils;

    @Test(groups= {"Smoke"})
    public void verifyAddAssigneeWithCSV() {

    logger.info("****** Starting Add Assignee With Upload CSV Test ******");
		try{

        login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

        DashboardPage dp = new DashboardPage(driver);
        commonUtils = new CommonUtils(driver);

        dp.searchForItem(p.getProperty("accountName"));
        dp.clickView();
        //Navigate to Users & roles tab

        AccountDetailsPage ad = new AccountDetailsPage(driver);
        commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");

        //Select account admin in user type dropdown
            PurchaseLevelAccountPage pl = new PurchaseLevelAccountPage(driver);
            pl.performTableAction("accountsTableUserRoles", p.getProperty("userAccountAdminName"), "Assign Tests",1);

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
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.assigneeType),p.getProperty("userUploadAssigneeType"));
            //Select Experience Type
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.experienceAssigneeUploadDropdown),p.getProperty("userUploadAssigneeExperience"));
            Thread.sleep(1000);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.numberToAssignCompleteUploadButton),null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(pl.userUploadValidationMessage),p.getProperty("userAssigneeUploadValidationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(pl.userUploadOkButton),null);

    }catch(Exception e)
    {
        Assert.fail();
    }
		logger.info("****** Finished Add Assignee With Upload CSV Test ******");
}
}
