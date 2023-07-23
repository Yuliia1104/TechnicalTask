package test2;

import baseTest.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import libs.TestData;
import org.junit.Test;

@Epic("Web Elements Testing")
@Feature("Items list and its badges Testing")
public class Test2 extends BaseTest {
    @Description("This test verifies values of the items and its corresponding badges.")
    @Test
    public void listValuesVerification(){
        testPage2.isHeaderDisplayed()
                    .checkValuesOfListItems(TestData.expectedListOfItems)
                    .checkBadgesValuesOfList(TestData.expectedListOfItemsWithBadges);
    }
}
