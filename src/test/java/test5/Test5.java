package test5;

import baseTest.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import libs.TestData;
import org.junit.Test;

@Epic("Web Elements Testing")
@Feature("Buttons test")
public class Test5 extends BaseTest {
    @Description("This test verifies the button functionality and validates alert message.")
    @Test
    public void alertVerification(){
        testPage5.scrollToFivesSection()
                    .isHeader5Displayed()
                    .clickAlertButton()
                    .checkAlertMessage(TestData.ALERT_MESSAGE)
        ;
    }
}
