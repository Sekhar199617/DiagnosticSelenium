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
        try {
            logger.info("****** Starting Contact Us Test ******");

            loadTestData(
                    "./testData/adminLoginData.json",
                    "./testData/hamburgerMenuModulesData.json"
            );

            login(getTestData("adminEmail"), getTestData("adminPassword"), true);

            DashboardPage dp = new DashboardPage(driver);
            CommonUtils commonUtils = new CommonUtils(driver);
            ContactUsPage cu = new ContactUsPage();

            dp.selectHamburgerTab("Contact Us");

            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(cu.nameField), randomString());
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(cu.emailField),
                    randomString() + "@gmail.com");
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(cu.phoneField), randomNumbers(10));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(cu.companyOrAccountField),
                    getTestData("company"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(cu.preferredContactMethodDropdown),
                    getTestData("preferredContactMethod"));
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(cu.questionTypeDropdown),
                    getTestData("questionType"));
            commonUtils.enterValueInTextField(commonUtils.findElementByXpath(cu.messageField),
                    getTestData("queryText"));
            commonUtils.scrollToBottomAndClick(commonUtils.findElementByXpath(cu.submitRequestButton));
        }catch(Exception e)
        {
            Assert.fail();
        }
        logger.info("****** Finished Contact Us Test ******");
    }
}
