package pages;



import io.qameta.allure.Step;
import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestPage5 extends ParentPage{
    @FindBy(xpath = ".//button[@id='test5-button']")
    private WebElement alertButton;
    @FindBy(xpath = ".//h1[contains(text(), '5')]")
    private WebElement header5;
    @FindBy(xpath = ".//div[@id = 'test5-alert']")
    private WebElement  alertMessage;

    public TestPage5(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * This method imitates scrolling down the page to the respective element (title in this case)
     * @return
     */
    @Step
    public TestPage5 scrollToFivesSection() {
        WebElement webElement = webDriver.findElement(By.xpath(".//h1[contains(text(), '5')]"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", webElement);
        return this;
    }

    /**
     * This method verifies if the user in on the right spot by checking the title
     * @return
     */
    @Step
    public TestPage5 isHeader5Displayed() {
        isElementDisplayed(header5);
        return this;
    }

    /**
     * This method click the button
     * @return
     */
    @Step
    public TestPage5 clickAlertButton(){
        webDriverWait30.until(ExpectedConditions.elementToBeClickable(alertButton));
        clickOnElement(alertButton);
        return this;
    }

    /**
     * This method compares alert message with test data alert message
     * @param alertText
     * @return
     */
    @Step
    public TestPage5 checkAlertMessage(String alertText){
        webDriverWait15.until(ExpectedConditions.visibilityOf(alertMessage));
        Assert.assertEquals("Text in alert defers from " + alertMessage.getText(), alertText, alertMessage.getText());
        logger.info(TestData.ALERT_MESSAGE + " MATCHES WITH " + alertMessage.getText().trim());
        return this;
    }
}
