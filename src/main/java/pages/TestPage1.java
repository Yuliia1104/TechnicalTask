package pages;

import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TestPage1 extends ParentPage {
    @FindBy(xpath = ".//h1[contains(text(), '1')]")
    private WebElement header;
    @FindBy(xpath = ".//input[@id = 'inputEmail']")
    private WebElement emailInput;
    @FindBy(xpath = ".//input[@id = 'inputPassword']")
    private WebElement passwordInput;
    @FindBy(xpath = ".//button[@type = 'submit']")
    private WebElement submitButton;

    public TestPage1(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * This method verifies if the user in on the right spot by checking the title
     * @return
     */
    @Step
    public TestPage1 isHeaderDisplayed() {
        isElementDisplayed(header);
        return this;
    }

    /**
     * Here are three methods verify if fields of registration for are displayed
     * @return
     */
    @Step
    public TestPage1 isEmailInputDisplayed(){
        isElementDisplayed(emailInput);
        return this;
    }
    @Step
    public TestPage1 isPasswordInputDisplayed(){
        isElementDisplayed(passwordInput);
        return this;
    }

    @Step
    public TestPage1 isButtonDisplayed(){
        isElementDisplayed(submitButton);
        return this;
    }

    /**
     * This method fills in email field of login form
     * @param emailAddress
     * @return
     */
    @Step
    public TestPage1 fillInEmailInput(String emailAddress){
        enterTextIntoElement(emailInput, emailAddress);
        return this;
    }

    /**
     * this method fills in password field of login form
     * @param password
     * @return
     */
    @Step
    public TestPage1 fillInPasswordInput(String password){
        enterTextIntoElement(passwordInput, password);
        return this;
    }

    /**
     * This method verifies whether correct credentials were entered into login form
     * @param email
     * @param password
     * @return
     */
    @Step
    public TestPage1 checkInputValues(String email, String password){
        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(email).isEqualTo(emailInput.getAttribute("value"));
        softAssert.assertThat(password).isEqualTo(passwordInput.getAttribute("value"));
        logger.info("Inserted values verified");
        softAssert.assertAll();
        return this;
    }
}
