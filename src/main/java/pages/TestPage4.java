package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TestPage4 extends ParentPage {
    @FindBy(xpath = ".//h1[contains(text(), '4')]")
    private WebElement header4;
    @FindBy(xpath = ".//div[@id='test-4-div']//button[@class='btn btn-lg btn-primary']")
    private WebElement buttonPrimary;
    @FindBy(xpath = ".//div[@id='test-4-div']//button[@class='btn btn-lg btn-secondary']")
    private WebElement buttonSecondary;

    public TestPage4(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * This method imitates scrolling down the page to the respective element (title in this case)
     * @return
     */
    @Step
    public TestPage4 scrollToForthSection() {
        WebElement webElement = webDriver.findElement(By.xpath(".//h1[contains(text(), '4')]"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", webElement);
        return this;
    }

    /**
     * This method verifies if the user in on the right spot by checking the title
     * @return
     */
    @Step
    public TestPage4 isHeader4Displayed() {
        isElementDisplayed(header4);
        return this;
    }

    /**
     * This method verifies is the primary button is enabled
     * @return
     */
    @Step
    public TestPage4 isPrimaryButtonEnabled() {
        Assert.assertTrue(buttonPrimary.getText() + " is disabled", buttonPrimary.isEnabled());
        logger.info("Primary button is enabled");
        return this;
    }

    /**
     * This method verifies is secondary button is disabled
     * @return
     */
    @Step
    public TestPage4 isSecondaryButtonDisabled() {
        Assert.assertFalse(buttonPrimary.getText() + " is enabled", buttonSecondary.isEnabled());
        logger.info("Secondary button is disabled");
        return this;
    }
}
