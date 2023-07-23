package test4;

import baseTest.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Test;

@Epic("Web Elements Testing")
@Feature("Buttons test")
public class Test4 extends BaseTest {
    @Description("This test verifies the functionality of primary and secondary buttons.")
    @Test
    public void buttonsAbility(){
        testPage4.scrollToForthSection()
                    .isHeader4Displayed()
                    .isPrimaryButtonEnabled()
                    .isSecondaryButtonDisabled();
    }
}
