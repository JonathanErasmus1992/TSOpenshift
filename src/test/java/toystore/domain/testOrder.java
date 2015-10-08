package toystore.domain;

/**
 * Created by Thawhir on 2015/10/05.
 */
import toystore.conf.OrderFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.util.Date;
import java.util.List;

public class testOrder {
    private Orders order;
    private Date date;
    private List<Orderline> orderlines;

    @Before
    public void setUp()
    {
        order = OrderFactory.createOrder(date, 200, false, orderlines);
    }

    @Test
    public void testCreate()
    {
        Assert.assertEquals(order.getCheckout(), false);
    }

    @Test
    public void testUpdate()
    {
        Orders newOrders = new Orders
                                .Builder(order.getDateModified())
                                .copy(order)
                                .checkout(true)
                                .build();
        Assert.assertEquals(order.getDateModified(),newOrders.getDateModified());
        Assert.assertNotEquals(order.getCheckout(),newOrders.getCheckout());
    }

    @After
    public void tearDown()
    {

    }
}
