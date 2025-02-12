package testCases;

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
            AccountDetailsUsersAndRolesPage au = new AccountDetailsUsersAndRolesPage(driver);
        commonUtils.selectDropDownValue(commonUtils.findElementByXpath(au.userTypeDropdown),p.getProperty("usersUserTypeAccountAdmin"));

            //Clicking on Assign Test in action dropdown for a account
            au.performActionOnUser("accountsTableUserRoles", p.getProperty("userAccountAdminName"), "Assign Tests");
            PurchaseLevelAccountPage pl = new PurchaseLevelAccountPage(driver);
        commonUtils.selectDropDownValue(commonUtils.findElementByXpath(pl.userTypeDropdown),p.getProperty("usersUserTypeAccountAdmin"));

        // Click on assign test for account name
            pl.performActionOnUser("accountsTableUserRoles",p.getProperty("userAccountAdminName"),"Assign Tests" );

            //Switch the tab
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
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
		logger.info("****** Finished Add Assignee With Upload CSVr Test ******");
}
}
