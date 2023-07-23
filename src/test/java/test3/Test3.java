package test3;

import baseTest.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import libs.TestData;
import org.junit.Test;

@Epic("Web Elements Testing")
@Feature("Drop down test")
public class Test3 extends BaseTest {
    @Description("This test compares options available one web page with test data, plus this test verifies chosen option.")
    @Test
    public void dropDownVerification(){
        testPage3.scrollToThirdSection()
                    .isHeaderDisplayed()
                    .clickDropDownButton()
                    .isDropDownOptionsDisplayed(TestData.dropDownItems)
                    .checkChosenOption(TestData.OPTION_3)
        ;

    }
}
