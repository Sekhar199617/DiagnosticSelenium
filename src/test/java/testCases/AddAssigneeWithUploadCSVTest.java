package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsPage;
import pageObjects.AccountDetailsUsersAndRolesPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AddAssigneeWithUploadCSVTest extends BaseClass {
    public CommonUtils commonUtils;

    @Test(groups= {"Smoke"})
    public void verifyAddAssigneeWithCSV() {

    logger.info("****** Starting Add Assignee Wit hUpload CSV Test ******");
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

            // Locate the table rows
            List<WebElement> rows = driver.findElements(By.xpath("//table[@id='accountsTableUserRoles']/tbody/tr"));

            for (WebElement row : rows) {
                // Get the name cell from the first column
                WebElement nameCell = row.findElement(By.xpath("./td[1]"));

                if (nameCell.getText().trim().equals(p.getProperty("userAccountAdminName"))) {
                    System.out.println("Found user: " + nameCell.getText());

                    // Click the "Actions" button
                    WebElement actionsButton = row.findElement(By.xpath(".//button[contains(text(),'Actions')]"));
                    actionsButton.click();

                    // Click the "Assign Tests" option
                    WebElement assignTestsOption = row.findElement(By.xpath(".//a[contains(text(),'Assign Tests')]"));
                    assignTestsOption.click();
                    break;
                }
            }

            //Switch the tab
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            //Click on upload csv excel button
            commonUtils.clickOnElement(commonUtils.findElementByXpath(au.uploadCsvExcelButton),null);
            WebElement fileUploadElement = commonUtils.findElementByXpath(au.chooseFileButton);
            String filePath = Paths.get("src/test/resources/userAssignee.csv").toAbsolutePath().toString();
            commonUtils.uploadFile(fileUploadElement, filePath);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(au.uploadCsvButton),null);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(au.assignBundleButton),null);
            //Select assign bundles from dropdown
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(au.assigneeType),p.getProperty("userUploadAssigneeType"));
            //Select Experience Type
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(au.experienceAssigneeUploadDropdown),p.getProperty("userUploadAssigneeExperience"));
            Thread.sleep(1000);
            commonUtils.clickOnElement(commonUtils.findElementByXpath(au.numberToAssignCompleteUploadButton),null);
            commonUtils.validateGetText(commonUtils.findElementByXpath(au.userUploadValidationMessage),p.getProperty("userAssigneeUploadValidationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(au.userUploadOkButton),null);

    }catch(Exception e)
    {
        Assert.fail();
    }
		logger.info("****** Finished Add New Account User Test ******");
}
}
