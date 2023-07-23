package TestSuits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test1.Test1;
import test2.Test2;
import test3.Test3;
import test4.Test4;
import test5.Test5;
import test6.Test6;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Test1.class,
        Test2.class,
        Test3.class,
        Test4.class,
        Test5.class,
        Test6.class
})
public class TestSuit {
}
