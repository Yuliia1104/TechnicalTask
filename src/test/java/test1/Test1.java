package test1;

import baseTest.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import libs.TestData;
import org.junit.Test;

@Epic("Web Elements Testing")
@Feature("Form Testing")
public class Test1 extends BaseTest {

    @Description("This test verifies if form input fields are visible.")
    @Test
    public void test1ElementsCheck() {
        testPage1.isHeaderDisplayed()
                .isEmailInputDisplayed()
                .isPasswordInputDisplayed()
        ;
    }
    @Description("This test inserts values and validates inserted data.")
    @Test
    public void EnterCredentialsInInputs() {
        testPage1.fillInEmailInput(TestData.EMAIL_ADDRESS)
                .fillInPasswordInput(TestData.PASSWORD)
                .checkInputValues(TestData.EMAIL_ADDRESS, TestData.PASSWORD);
        ;
    }
}
