package toystore.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
        testCustomer.class,
        testItem.class,
        testOrder.class,
        testOrderline.class,
        testInvoice.class
})
public class testSuite {
}
