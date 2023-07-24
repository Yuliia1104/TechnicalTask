package pages;

import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class TestPage2 extends ParentPage {

    @FindBy(xpath = ".//h1[contains(text(), '2')]")
    private WebElement header2;
    @FindBy(xpath = ".//li[@class='list-group-item justify-content-between']")
    private List<WebElement> listOfItems;
    @FindBy(xpath = ".//span[@class='badge badge-pill badge-primary']")
    private List<WebElement> listOfBadges;

    public TestPage2(WebDriver webDriver) {
        super(webDriver);
    }
    /**
     * This method verifies if the user in on the right spot by checking the title
     * @return
     */
    @Step
    public TestPage2 isHeaderDisplayed() {
        isElementDisplayed(header2);
        return this;
    }

    /**
     * This method captures List of item values and compare it to test data
     * @param expectedList
     * @return
     */
    @Step
    public TestPage2 checkValuesOfListItems(ArrayList<String> expectedList) {

        ArrayList<String> actualList = new ArrayList<>();
        for (WebElement webElement: listOfItems) {
            actualList.add(webElement.getText().replaceAll("\\s\\d+$", ""));
        }

        SoftAssertions softAssert = new SoftAssertions();
        for (int i = 0; i < expectedList.size(); i++) {
            softAssert.assertThat(expectedList.get(i)).as(expectedList.get(i)+" is not on the list!").isIn(actualList);
            logger.info(expectedList.get(i)+" is equal to "+ actualList.get(i));

        }
        softAssert.assertAll();
        return this;
    }

    /**
     * This method verifies the badge value of each item
     * @return
     */
    @Step
    public TestPage2 checkBadgesValuesOfList(ArrayList<String> expectedListOfItemsWithBadges){

        SoftAssertions softAssert = new SoftAssertions();
        for (int i = 0; i < expectedListOfItemsWithBadges.size(); i++) {
                softAssert.assertThat(expectedListOfItemsWithBadges.get(i)).as("Badge does not match").contains(listOfBadges.get(i).getText());
            logger.info(expectedListOfItemsWithBadges.get(i) +" contains " + listOfBadges.get(i).getText());
        }
        softAssert.assertAll();
        return this;
    }
}
