package pages;

import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class TestPage3 extends ParentPage {

    @FindBy(xpath = ".//h1[contains(text(), '3')]")
    private WebElement header3;
    @FindBy(xpath = ".//button[@class='btn btn-secondary dropdown-toggle']")
    private WebElement dropDownButton;
    @FindBy(xpath = ".//a[@class='dropdown-item']")
    private List<WebElement> dropDownOptions;

    public TestPage3(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * This method imitates scrolling down the page to the respective element (title in this case)
     * @return
     */
    @Step
    public TestPage3 scrollToThirdSection() {
        WebElement webElement = webDriver.findElement(By.xpath(".//h1[contains(text(), '3')]"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", webElement);
        return this;
    }

    /**
     * This method verifies if the user in on the right spot by checking the title
     * @return
     */
    @Step
    public TestPage3 isHeaderDisplayed() {
        isElementDisplayed(header3);
        return this;
    }

    /**
     * This method clicks on the button
     * @return
     */
    @Step
    public TestPage3 clickDropDownButton() {
        clickOnElement(dropDownButton);
        return this;
    }

    /**
     * This method compares option on the wab page with test data option
     * @param expectedList
     * @return
     */
    @Step
    public TestPage3 isDropDownOptionsDisplayed(ArrayList<String> expectedList) {
        Assert.assertEquals("Number of items ", expectedList.size(), dropDownOptions.size());
        SoftAssertions softAssert = new SoftAssertions();
        for (int i = 0; i < expectedList.size(); i++) {
            softAssert.assertThat(expectedList.get(i)).as(expectedList.get(i) + " is not equal to " + dropDownOptions.get(i).getText())
                    .isIn(dropDownOptions.get(i).getText());
            logger.info(expectedList.get(i) + " is equal to " + dropDownOptions.get(i).getText());
        }
        softAssert.assertAll();
        return this;
    }

    /**
     * This method verifies if chosen option exists in the test data and if yes how the button reflects the chosen option
     * @param option
     * @return
     */
    @Step
    public TestPage3 checkChosenOption(String option) {
        for (int i = 0; i < dropDownOptions.size(); i++) {
            if(option.equalsIgnoreCase(dropDownOptions.get(i).getText())){
                clickOnElement(dropDownOptions.get(i));
            }
        }
        Assert.assertEquals("There is no such option like "+dropDownButton.getText(), option, dropDownButton.getText());
        logger.info(String.format("The button value is \"%s\"", dropDownButton.getText()));
            return this;
    }
}
