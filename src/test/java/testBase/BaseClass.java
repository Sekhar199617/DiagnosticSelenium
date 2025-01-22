package testBase;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageObjects.LoginPage;
import utilities.CommonUtils;

public class BaseClass {
    
    public WebDriver driver;
    public Logger logger;
    public Properties p;
    
    @BeforeClass(groups= {"Smoke"})
    @Parameters({"os", "browser"})
    public void setup(String os, String browser) throws IOException 
    {
    	FileReader file = new FileReader("./src//test//resources//config.properties");
    	p = new Properties();
    	p.load(file);

        logger = LogManager.getLogger(this.getClass());
        
        switch(browser.toLowerCase())
        {
        case "chrome" : driver = new ChromeDriver(); break;
        case "edge" : driver = new EdgeDriver(); break;
        case "firefox" : driver = new FirefoxDriver(); break;
        default : System.out.println("Invalid browser name..."); return;
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
        CommonUtils commonUtils = new CommonUtils(driver);
        
        commonUtils.enterValueInTextField(loginPage.emailField, username);
        commonUtils.enterValueInTextField(loginPage.passwordField, password);
        
        if (isAdminLogin) {
        	commonUtils.clickOnElement(loginPage.adminLoginButton, "Login");
        } else {
        	commonUtils.clickOnElement(loginPage.observerLoginButton, "Login");
        }
    }
    
    public String randomString()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomNumbers()
	{
		String generatedNumbers = RandomStringUtils.randomNumeric(10);
		return generatedNumbers;
	}
	
	public String randomAlphaNumeric()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		String generatedNumbers = RandomStringUtils.randomNumeric(10);
		return (generatedString+ "@" +generatedNumbers);
	}
    
    public String captureScreen(String tname) throws IOException {
        // Ensure the driver is an instance of TakesScreenshot
        if (driver instanceof TakesScreenshot) {
            String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

            // Capture the screenshot
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

            // Define target file path with extension
            String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
            File targetFile = new File(targetFilePath);

            // Copy the screenshot to the target file
            FileUtils.copyFile(sourceFile, targetFile);

            return targetFilePath;
        } else {
            throw new IOException("Driver does not support screenshot capture.");
        }
    }
    
    @AfterClass(groups= {"Smoke"})
    public void tearDown() {
    	driver.quit();
    }
}
