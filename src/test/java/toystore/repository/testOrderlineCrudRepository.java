package toystore.repository;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import org.junit.Assert;

import toystore.App;
import toystore.conf.OrderlineFactory;
import toystore.domain.Orderline;

/**
 * Created by Thawhir on 2015/10/05.
 */
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class testOrderlineCrudRepository extends AbstractTestNGSpringContextTests{
    private Long id;
    @Autowired
    OrderlineRepository repository;

    @Test
    public void testCreate()
    {
        Orderline orderline = OrderlineFactory.createOrderline(1);
        repository.save(orderline);
        id = orderline.getID();
        Assert.assertNotNull(orderline);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRead()
    {
        Orderline orderline = repository.findOne(id);
        Assert.assertEquals(orderline.getQuantity(), 1);
    }

    @Test(dependsOnMethods = "testRead")
    public void testUpdate()
    {
        Orderline orderline = repository.findOne(id);
        Orderline newOrderline = new Orderline
                .Builder(orderline.getQuantity())
                .copy(orderline)
                .quantity(2)
                .build();
        repository.save(newOrderline);

        Orderline updatedOrderline = repository.findOne(id);
        Assert.assertNotEquals(orderline.getQuantity(),updatedOrderline.getQuantity());
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete()
    {
        Orderline orderline = repository.findOne(id);
        repository.delete(orderline);
        Orderline newOrderline = repository.findOne(id);
        Assert.assertNull(newOrderline);
    }

}
