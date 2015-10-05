package toystore.domain;

/**
 * Created by Thawhir on 2015/10/05.
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
        testCustomer.class,
        testItem.class,
        testOrder.class,
        testOrderline.class
})
public class testSuite {
}
