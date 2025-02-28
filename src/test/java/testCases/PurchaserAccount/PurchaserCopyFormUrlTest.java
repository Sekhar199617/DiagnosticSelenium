package testCases.PurchaserAccount;

import org.testng.annotations.Test;
import org.testng.Assert;
import pageObjects.AdminAccount.AccountDetailsModules.AccountDetailsPage;
import pageObjects.AdminAccount.Dashboard.DashboardPage;
import pageObjects.PurchaserAccount.AccountPage;
import testBase.BaseClass;
import utilities.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class PurchaserCopyFormUrlTest extends BaseClass {

    public CommonUtils commonUtils;
    public AccountPage ob;

    @Test(groups = {"Smoke"})
    public void VerifyPurchaserCopyFormUrl(){

        logger.info("***** Starting Purchaser Copy Form Url And Past it in Different Tab *****");
        try {
            login(p.getProperty("adminEmail"),p.getProperty("adminPassword"),true);

            DashboardPage dp = new DashboardPage(driver);

            commonUtils = new CommonUtils(driver);
            dp.searchForItem(p.getProperty("accountName"));
            dp.clickView();

            AccountDetailsPage ad = new AccountDetailsPage(driver);
            ob = new AccountPage(driver);

            commonUtils.selectTab(commonUtils.findElementsByXpath(ad.tabList), "Users & Roles");

            //Select account admin in user type dropdown
            commonUtils.selectDropDownValue(commonUtils.findElementByXpath(ob.userTypeDropdown), p.getProperty("usersUserTypeAccountAdmin"));

            //Clicking on Assign Test in action dropdown for a account
            ob.performTableAction("accountsTableUserRoles", p.getProperty("userAccountAdminName"), "Assign Tests",1);

            //Switch the tab
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            dp.selectHamburgerTab("Forms");

            String copiedFormName = ob.clickOnFormsLink("globalFormTable",p.getProperty("purchaserFormCopyUrlFormType"));
            System.out.println(copiedFormName + " copied");

            String copiedURL = ob.getClipboardText();
            commonUtils.validateGetText(commonUtils.findElementByXpath(ob.successfulConfirmationMessage),p.getProperty("copyFormUrlConfirmationMessage"));
            commonUtils.clickOnElement(commonUtils.findElementByXpath(ob.successfulConfirmationOkButton),null);

            //Open the copied link in new Tab
            ob.openNewTabWithURL(copiedURL);
            String expectedFormName = commonUtils.findElementByXpath(ob.formUrlFormName).getText();

            Assert.assertEquals(copiedFormName,expectedFormName , "Form name is not matching");


        } catch (Exception e) {
            Assert.fail();
        }

        logger.info("****** Finished Purchaser Dismiss Shipping Task Test ******");
    }

    }
