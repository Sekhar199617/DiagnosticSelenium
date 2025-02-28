package testBase;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageObjects.LoginPage;
import utilities.CommonUtils;

public class BaseClass {
    
	public static WebDriver driver;
    public Logger logger;
    public Properties p;
	public CommonUtils commonUtils;
	private JSONObject testData;

    @BeforeClass(groups = {"Smoke"})
    @Parameters({"os", "browser", "headless"})
    public void setup(@Optional("Windows") String os, @Optional("chrome") String browser, @Optional("false") String headless) throws IOException
    {
    	FileReader file = new FileReader("./src//test//resources//config.properties");
    	p = new Properties();
    	p.load(file);

        logger = LogManager.getLogger(this.getClass());

        boolean isHeadless = Boolean.parseBoolean(headless);

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless) {
                    chromeOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920x1080");
                }
                driver = new ChromeDriver(chromeOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (isHeadless) {
                    edgeOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920x1080");
                }
                driver = new EdgeDriver(edgeOptions);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless) {
                    firefoxOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920x1080");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                System.out.println("Invalid browser name...");
                return;
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(p.getProperty("adminAppURL"));
        driver.manage().window().maximize();
    }

	public void login(String username, String password, boolean isAdminLogin) {
        String appURL = isAdminLogin ? p.getProperty("adminAppURL") : p.getProperty("observerAppURL");
        driver.get(appURL);

        LoginPage loginPage = new LoginPage(driver);
        commonUtils = new CommonUtils(driver);

        commonUtils.enterValueInTextField(commonUtils.findElementByXpath(loginPage.emailField), username);
        commonUtils.enterValueInTextField(commonUtils.findElementByXpath(loginPage.passwordField), password);

        if (isAdminLogin) {
        	commonUtils.clickOnElement(commonUtils.findElementByXpath(loginPage.adminLoginButton), "Login");
        } else {
        	commonUtils.clickOnElement(commonUtils.findElementByXpath(loginPage.observerLoginButton), "Login");
        }
    }
    
    public String randomString()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomNumbers(int count)
	{
		String generatedNumbers = RandomStringUtils.randomNumeric(count);
		return generatedNumbers;
	}
	
	public String randomAlphaNumeric()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		String generatedNumbers = RandomStringUtils.randomNumeric(10);
		return (generatedString+ "@" +generatedNumbers);
	}
	
	public String captureScreen(String tname) throws IOException {
	    if (!(driver instanceof TakesScreenshot)) {
	        throw new IllegalStateException("Driver does not support screenshot capture.");
	    }

	    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

	    File screenshotDir = new File(System.getProperty("user.dir") + "\\screenshots");
	    if (!screenshotDir.exists()) {
	        screenshotDir.mkdirs();
	    }

	    String targetFilePath = screenshotDir + "\\" + tname + "_" + timeStamp + ".png";
	    File targetFile = new File(targetFilePath);
	    FileUtils.copyFile(sourceFile, targetFile);
	    return targetFilePath;
	}
	
	public void loadTestData(String jsonPath) {
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(jsonPath);
            Object obj = parser.parse(reader);
            testData = (JSONObject) obj;
        } catch (Exception e) {
            logger.error("Error reading the test data JSON file: " + e.getMessage());
        }
    }

    // Method to get data from the JSON file by key
    public String getTestData(String key) {
        return (String) testData.get(key);
    }
    
    /*@AfterClass(groups= {"Smoke"})
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }*/
}
