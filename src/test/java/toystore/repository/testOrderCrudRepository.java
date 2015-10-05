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
import toystore.domain.Order;
import toystore.domain.Orderline;

/**
 * Created by Thawhir on 2015/10/05.
 */
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class testOrderCrudRepository extends AbstractTestNGSpringContextTests{
    private Long id;
    private Date date;
    private List<Orderline> orderlines;
    @Autowired
    OrderRepository repository;

    @Test
    public void testCreate()
    {
        Order order = OrderFactory.createOrder(date, 200, false, orderlines);
        repository.save(order);
        id = order.getID();
        Assert.assertNotNull(order);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRead()
    {
        Order order = repository.findOne(id);
        Assert.assertEquals(order.getDateModified(), date);
    }

    @Test(dependsOnMethods = "testRead")
    public void testUpdate()
    {
        Order order = repository.findOne(id);
        Order newOrder = new Order
                .Builder(order.getDateModified())
                .copy(order)
                .totalPrice(300)
                .build();
        repository.save(newOrder);

        Order updatedOrder = repository.findOne(id);
        Assert.assertNotEquals(order.getTotalPrice(),updatedOrder.getTotalPrice());
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete()
    {
        Order order = repository.findOne(id);
        repository.delete(order);
        Order newOrder = repository.findOne(id);
        Assert.assertNull(newOrder);
    }

}
