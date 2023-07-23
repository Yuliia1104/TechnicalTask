package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import libs.ScreenShot;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import pages.*;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.ArrayList;


public class BaseTest {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected TestPage1 testPage1;
    protected  TestPage2 testPage2;
    protected TestPage3 testPage3;
    protected TestPage4 testPage4;
    protected TestPage5 testPage5;
    protected TestPage6 testPage6;
    protected ArrayList<ScreenShot> listOfScreenShots = new ArrayList<>();

    /**
     * This section opens the browser
     */
    @Before
    public void openHTMLFileInChrome(){
        logger.info("Chrome is opened!");
        webDriver = initDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        webDriver.get("file:///Users/yuliiasoloviova/workSpace/HTMLPractice/TechTask/src/resources/QE-index.html");
        testPage1 = new TestPage1(webDriver);
        testPage2 = new TestPage2(webDriver);
        testPage3 = new TestPage3(webDriver);
        testPage4 = new TestPage4(webDriver);
        testPage5 = new TestPage5(webDriver);
        testPage6 = new TestPage6(webDriver);
    }

    /**
     * This section closes the browser and takes a screenshot if test fails
     */
    @Rule(order = Integer.MIN_VALUE)
public final TestWatcher watchman = new TestWatcher() {
    @Override
    protected void failed(Throwable e, Description description) {
        screenshot();
    }

    public void saveScreenshot(ArrayList<ScreenShot> screenShots) {
        screenShots.forEach(screenShot -> Allure.addAttachment(screenShot.getName(),
                new ByteArrayInputStream(screenShot.getScreenShotImg())));
    }

    public void screenshot() {
        if (webDriver == null) {
            logger.info("Driver for screenshot not found");
            return;
        }
        byte[] screen = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        listOfScreenShots.add(new ScreenShot("Default screenShot after failed test", screen));
        saveScreenshot(listOfScreenShots);
    }

    @Override
    protected void finished(Description description) {
        logger.info(
                String.format("Finished test: %s::%s", description.getClassName(), description.getMethodName()));
        try {
            webDriver.quit();
            logger.info("Browser was closed");
        } catch (Exception e) {
            logger.error(e);
        }
    }
};

    /**
     * This method sets up the browser depending on the settings.
     * @return
     */
    private WebDriver initDriver() {
        String browser = System.getProperty("browser");
        if ((browser == null) || "chrome".equalsIgnoreCase((browser))) {
            ChromeOptions ops = new ChromeOptions();
            ops.addArguments("--remote-allow-origins=*");
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver(ops);
        } else if ("firefox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        } else if ("safari".equalsIgnoreCase(browser)) {
            WebDriverManager.safaridriver().setup();
            webDriver = new SafariDriver();
        }
        return webDriver;
    }
}
