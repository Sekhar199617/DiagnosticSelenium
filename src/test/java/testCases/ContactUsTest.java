package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HamburgerMenuModules.AdminAccount.ContactUs.ContactUsPage;
import pageObjects.AccountDetailsModules.AdminAccount.Dashboard.DashboardPage;
import testBase.BaseClass;
import utilities.CommonUtils;

public class ContactUsTest extends BaseClass {

    @Test
    public void verifyContactUs() {
        try {
            logger.info("****** Starting Contact Us Test ******");
            login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            ContactUsPage cu = new ContactUsPage();

            dp.selectHamburgerTab("Contact Us");

            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(cu.nameField), randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(cu.emailField),
                    randomString() + "@gmail.com");
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(cu.phoneField), randomNumbers(10));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(cu.companyOrAccountField),
                    p.getProperty("company"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(cu.preferredContactMethodDropdown),
                    p.getProperty("preferredContactMethod"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(cu.questionTypeDropdown),
                    p.getProperty("questionType"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(cu.messageField),
                    p.getProperty("queryText"));
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(cu.submitRequestButton));
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Contact Us Test ******");
    }
}
