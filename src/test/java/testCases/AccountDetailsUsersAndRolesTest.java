package testCases;
import org.testng.annotations.Test;
import pageObjects.AccountDetailsPage;
import pageObjects.AccountDetailsUsersAndRolesPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;

public class AccountDetailsUsersAndRolesTest extends BaseClass {
	
	@Test
	public void verifyAddNewAccountUser() {
		
		login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);
		
		DashboardPage dp = new DashboardPage(driver);

		dp.searchForElement(p.getProperty("accountName"));
		dp.clickOnActionsDropDown();
		dp.clickOnView();
		
		AccountDetailsPage ad = new AccountDetailsPage(driver);
		ad.selectTab("Users & Roles");
		ad.clickOnAdd();
		
		AccountDetailsUsersAndRolesPage au = new AccountDetailsUsersAndRolesPage(driver);
		au.enterNewUserName(randomString());
		au.clickOnCountryDropDown();
		au.selectMobileCountryCode(p.getProperty("mobileCountryCode"));
		au.enterPhoneNumber(randomNumbers());
		au.enterEmail(randomString() + "@gmail.com");
		au.selectRole(p.getProperty("role"));
		
		au.selectUserType(p.getProperty("userType"));
		
		switch(p.getProperty("userType")) {
			case "account_admin": 
				au.validateInputText(p.getProperty("accountName"));
				break;
			case "provider":
				au.enterLicenceID(p.getProperty("licenseID"));
				au.enterCredentials(p.getProperty("credentials"));
				break;
			case "medical_staff":
				au.enterLicenceID(p.getProperty("licenseID"));
				au.enterCredentials(p.getProperty("credentials"));
				break;
			case "observer":
				au.selectUnselectedBundle(p.getProperty("unselectedBundle"));
				au.clickOnRightArrow();
				au.verifySelectedBundleText(p.getProperty("unselectedBundle"));
				break;
			case "manager":
				au.selectRandomAdditionalPriviligesCheckboxes();
				break;
			case "support_entity_admin":
				break;
			case "support_entity_manager":
				au.selectRandomSupportManagerPriviligesCheckboxes();
				break;
			default :
				System.out.println("Invalid user type provided: " + p.getProperty("userType"));
                break;		
		}
		
		
		au.verifyShowAssigneeSessionTimeRadioButton();
		au.selectUserDefaultTimeZone(p.getProperty("defaultTimeZone"));
		au.verifyActiveCheckbox();
		au.clickOnSave();
		
		au.verifyDialogueText(p.getProperty("dialogueText"));
		au.clickOnDialogueOk();
	}
}
