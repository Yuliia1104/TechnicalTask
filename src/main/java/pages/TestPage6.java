package pages;

import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TestPage6 extends ParentPage{
    @FindBy(xpath = ".//h1[contains(text(), '6')]")
    private WebElement header6;
    @FindBy(xpath = ".//th")
    private List<WebElement> columnNamesList;

    private final String TABLE_ROW = ".//tr[%s]";
    private final String TABLE_COLUMN = ".//td[%s]";

    public TestPage6(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * This method imitates scrolling down the page to the respective element (title in this case)
     * @return
     */
    @Step
    public TestPage6 scrollToSixthSection() {
        WebElement webElement = webDriver.findElement(By.xpath(".//h1[contains(text(), '6')]"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", webElement);
        return this;
    }

    /**
     * This method verifies if the user in on the right spot by checking the title
     * @return
     */
    @Step
    public TestPage6 isHeader6Displayed() {
        isElementDisplayed(header6);
        return this;
    }

    /**
     * This method check the column name
     * @param expectedColumnNames
     * @return
     */
    @Step
    public TestPage6 checkColumnNames(List<String> expectedColumnNames){
        SoftAssertions softAssert = new SoftAssertions();
        for (int i = 0; i < expectedColumnNames.size(); i++) {
            softAssert.assertThat(expectedColumnNames.get(i))
                    .as(expectedColumnNames.get(i) + " does not match with " + columnNamesList.get(i).getText())
                    .isIn(columnNamesList.get(i).getText());
            logger.info(expectedColumnNames.get(i) + " is equal to " + columnNamesList.get(i).getText());
        }
        softAssert.assertAll();
        return this;
    }

    /**
     * This method compares cell data of the web table with the test data
     * @param rowNumber
     * @param columnNumber
     * @param tableData
     * @return
     */
    @Step
    public TestPage6 checkCellValue(Integer rowNumber, Integer columnNumber, String tableData){
        WebElement table = webDriver.findElement(By.tagName("table"));
        WebElement tableRow = table.findElement(By.xpath(String.format(TABLE_ROW, rowNumber)));
        WebElement tableCell = tableRow.findElement(By.xpath(String.format(TABLE_COLUMN, columnNumber)));
        Assert.assertEquals("Value "+tableCell.getText() +" is not found", tableData, tableCell.getText());
        logger.info(tableCell.getText()+ " Is equal to "+tableData);
        return this;
    }
}
