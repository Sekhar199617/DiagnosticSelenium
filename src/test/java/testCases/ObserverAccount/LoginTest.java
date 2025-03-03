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
		try
		{
			logger.info("****** Starting Observer Login and Video Presence Test Case ******");
			login(p.getProperty("observerEmail"), p.getProperty("observerPassword"), false);

			HomePage oh = new HomePage();
			String actualText = commonUtils.getTextFromElement(commonUtils.findElementByXpath(
					oh.videoObservationFlowText));
			Assert.assertEquals(actualText, "Video Observation Flow");

			WebElement videoElement = commonUtils.findElementByXpath(oh.videoScreen);

			commonUtils.validateGetText(commonUtils.findElementByXpath(oh.videoVerificationTabText),
					"Video Verification");
			commonUtils.validateGetText(commonUtils.findElementByXpath(oh.imageVerificationTabText),
					"Image Verification");
			commonUtils.validateGetText(commonUtils.findElementByXpath(oh.idVerificationTabText),
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
