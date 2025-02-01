package OrangeHrmTests.Base;

import org.Base.BasePage;
import org.OrangeHrm.LoginPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;

import static org.Utility.Utility.setUtilityDriver;

public class BaseTest {

    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    protected WebDriver driver;
    protected BasePage basePage;
    protected LoginPage loginPage;


    @BeforeClass
    public void setUp(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();

    }

    @BeforeMethod
    public void loadApplication(){
        String URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        driver.get(URL);

        // Initialize page objects
        basePage = new BasePage();
        BasePage.setDriver(driver);
        setUtilityDriver();
        loginPage = new LoginPage();
    }



//    take screenshot of failed tests
    @AfterMethod
    public void takeFailTestScreenShot(ITestResult testResult){
//        check the test result status is failure
        if (ITestResult.FAILURE==testResult.getStatus()){
//            take screenshot
            TakesScreenshot takesScreenshot= (TakesScreenshot)driver;
            File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);

//            get the time stamp
            String timestamp = java.time.LocalDateTime.now()
                    .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS"));
//generate file name
            String fileName = String.format("screenshot_%s_(%s).png", timestamp, testResult.getName());
//destination path
            File destination = new File("D:\\SeleniumTesting\\DelivergatePracticalExam\\src\\main\\java\\org\\OrangeHrm\\ScreenShots\\" + fileName);
            try {
                // Copy the screenshot from source to destination
                FileHandler.copy(sourceFile, destination);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Print the location of the screenshot
            System.out.println("Screenshot Located at " + destination);
        }
    }


    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }


}
