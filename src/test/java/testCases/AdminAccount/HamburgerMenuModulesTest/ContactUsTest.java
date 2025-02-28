package testCases.AdminAccount.HamburgerMenuModulesTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminAccount.HamburgerMenuModules.ContactUsPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class ContactUsTest extends BaseClass {

    @Test
    public void verifyContactUs() {
        try {
            logger.info("****** Starting Contact Us Test ******");
            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            ContactUsPage contactUsPage = new ContactUsPage();
            DashboardPage dashboardPage = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);

            dashboardPage.selectHamburgerTab("Contact Us");

            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(contactUsPage.nameField), randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(contactUsPage.emailField),
                    randomString() + "@gmail.com");
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(contactUsPage.phoneField),
                    randomNumbers(10));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(contactUsPage.companyOrAccountField),
                    p.getProperty("company"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(contactUsPage.preferredContactMethodDropdown),
                    p.getProperty("preferredContactMethod"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(contactUsPage.questionTypeDropdown),
                    p.getProperty("questionType"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(contactUsPage.messageField),
                    p.getProperty("queryText"));
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(contactUsPage.submitRequestButton));
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Contact Us Test ******");
    }
}