package testCases.AdminAccount.HamburgerMenuModulesTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.HamburgerMenuModules.ContactUsPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class ContactUsTest extends BaseClass {

    @Test
    public void verifyContactUs() {
        logger.info("****** Starting Contact Us Test ******");
        try {

            ContactUsPage contactUsPage = new ContactUsPage();
            DashboardPage dashboardPage = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);

            loadTestData(
                    "./testData/AdminAccountData/adminLoginData.json",
                    "./testData/AdminAccountData/hamburgerMenuModulesData.json"
            );

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            dashboardPage.selectHamburgerTab("Contact Us");
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(contactUsPage.nameField), randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(contactUsPage.emailField),
                    randomString() + "@gmail.com");
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(contactUsPage.phoneField),
                    randomNumbers(10));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(contactUsPage.companyOrAccountField),
                    getTestData("company"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(contactUsPage.preferredContactMethodDropdown),
                    getTestData("preferredContactMethod"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(contactUsPage.questionTypeDropdown),
                    getTestData("questionType"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(contactUsPage.messageField),
                    getTestData("queryText"));
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(contactUsPage.submitRequestButton));
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Contact Us Test ******");
    }
}
