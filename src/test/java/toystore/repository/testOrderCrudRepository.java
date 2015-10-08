package toystore.repository;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import org.junit.Assert;

import java.util.Date;
import java.util.List;

import toystore.App;
import toystore.conf.OrderFactory;
import toystore.domain.Orders;
import toystore.domain.Orderline;

/**
 * Created by Thawhir on 2015/10/05.
 */
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class testOrderCrudRepository extends AbstractTestNGSpringContextTests{
    private Long id;
    private Date date = new Date();
    private List<Orderline> orderlines;
    @Autowired
    OrderRepository repository;


    @Test
    public void testCreate()
    {
        Orders order = OrderFactory.createOrder(date, 200, false, orderlines);
        repository.save(order);
        id = order.getID();
        Assert.assertNotNull(order);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRead()
    {
        Orders order = repository.findOne(id);
        Assert.assertEquals(order.getCheckout(), false);
    }

    @Test(dependsOnMethods = "testRead")
    public void testUpdate()
    {
        Orders order = repository.findOne(id);
        Orders newOrders = new Orders
                .Builder(order.getDateModified())
                .copy(order)
                .totalPrice(300)
                .build();
        repository.save(newOrders);

        Orders updatedOrders = repository.findOne(id);
        Assert.assertNotEquals(order.getTotalPrice(),updatedOrders.getTotalPrice());
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete()
    {
        Orders order = repository.findOne(id);
        repository.delete(order);
        Orders newOrders = repository.findOne(id);
        Assert.assertNull(newOrders);
    }

}
