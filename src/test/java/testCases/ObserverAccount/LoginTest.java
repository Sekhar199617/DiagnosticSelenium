package testCases.ObserverAccount;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.ObserverAccount.HomePage;
import testBase.BaseClass;

public class LoginTest extends BaseClass {
	
	@Test(groups= {"Smoke"})
	public void verifyObserverLoginAndVideoPresence()
	{
		logger.info("****** Starting Observer Login and Video Presence Test Case ******");
		try
		{
			HomePage homePage = new HomePage();

			loadTestData(
					"./testData/ObserverAccountData/observerLoginData.json"
			);

			login(getTestData("observerEmail"), getTestData("observerPassword"), false);

			String actualText = commonUtils.getTextFromElement(commonUtils.findElementByXpath(
					homePage.videoObservationFlowText));
			Assert.assertEquals(actualText, "Video Observation Flow");

			WebElement videoElement = commonUtils.findElementByXpath(homePage.videoScreen);

			commonUtils.validateGetText(commonUtils.findElementByXpath(homePage.videoVerificationTabText),
					"Video Verification");
			commonUtils.validateGetText(commonUtils.findElementByXpath(homePage.imageVerificationTabText),
					"Image Verification");
			commonUtils.validateGetText(commonUtils.findElementByXpath(homePage.idVerificationTabText),
					"ID Verification");

			if (videoElement.isDisplayed()) {
				System.out.println("Video call is present.");
			} else {
				Assert.fail("Video call is not present.");
			}

		}catch (Exception e)
		{
			Assert.fail();
		}
		logger.info("****** Ended Observer Login and Video Presence Test Case ******");
	}
}