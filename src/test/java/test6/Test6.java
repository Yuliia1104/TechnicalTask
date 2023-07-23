package test6;

import baseTest.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import libs.TestData;
import org.junit.Test;

@Epic("Web Elements Testing")
@Feature("Web table item value test")
public class Test6 extends BaseTest {
    @Description("This test verifies column names and value of the cell.")
    @Test
    public void tableDataVerification() {
        testPage6.scrollToSixthSection()
                .isHeader6Displayed()
                .checkColumnNames(TestData.columnNamesList)
                .checkCellValue(3, 3, TestData.TABLE_DATA_3_3)
        ;
    }

}
