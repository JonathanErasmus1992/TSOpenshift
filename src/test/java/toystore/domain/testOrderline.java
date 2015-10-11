package toystore.domain;


import toystore.conf.OrderlineFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

public class testOrderline {
    private Orderline orderline;

    @Before
    public void setUp()
    {
        orderline = OrderlineFactory.createOrderline(1);
    }

    @Test
    public void testCreate()
    {
        Assert.assertEquals(orderline.getQuantity(),1);
    }

    @Test
    public void testUpdate()
    {
        Orderline newOrderline = new Orderline
                                        .Builder(orderline.getQuantity())
                                        .copy(orderline)
                                        .quantity(2)
                                        .build();
        Assert.assertEquals(orderline.getID(),newOrderline.getID());
        Assert.assertNotEquals(orderline.getQuantity(),newOrderline.getQuantity());
    }

    @After
    public void tearDown()
    {

    }
}
