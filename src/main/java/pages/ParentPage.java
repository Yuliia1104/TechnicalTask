package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ParentPage {
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait15, webDriverWait30;

    /**
     * Constructor
     * @param webDriver
     */
    public ParentPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        webDriverWait30 = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        }

    /**
     * This method check if web element displayed using WebElement
     * @param webElement
     * @return
     */
    protected boolean isElementDisplayed(WebElement webElement){
        try {
            webDriverWait30.until(ExpectedConditions.visibilityOf(webElement));
            boolean status = webElement.isDisplayed();
            String message;
            if (status){
                message = getWebElementName(webElement) + " element is displayed";
            }else {
                message = getWebElementName(webElement) + " element exists but not displayed";
            }
            logger.info(message);
            return status;
        }catch (Exception e){
            logger.info(getWebElementName(webElement) + " element does not exist.");
            return false;
        }
    }

    /**
     * This method extracts the name of the web element
     * @param webElement
     * @return
     */
    protected String getWebElementName(WebElement webElement){
        try{
            return webElement.getAccessibleName();
        }catch (Exception e){
            return "";
        }
    }

    /**
     * This method enters text into web element
     * @param webElement
     * @param text
     */
    protected void enterTextIntoElement(WebElement webElement, String text){
        try{
            webDriverWait30.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " is entered into element " + getWebElementName(webElement));
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    /**
     * This method clicks on the web element
     * @param webElement
     */
    protected void clickOnElement(WebElement webElement){
        try {
            webDriverWait30.until(ExpectedConditions.elementToBeClickable(webElement));
            String name = getWebElementName(webElement);
            webElement.click();
            logger.info( name +" element is clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    /**
     * This method fails the test
     * @param e
     */
    protected void printErrorAndStopTest(Exception e){
        logger.error("Cannot reach the element" + e);
        Assert.fail("Cannot reach the element");
    }

    }

