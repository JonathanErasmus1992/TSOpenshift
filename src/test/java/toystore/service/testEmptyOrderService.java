package toystore.service;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import toystore.App;
import toystore.conf.ItemFactory;
import toystore.conf.OrderFactory;
import toystore.domain.Item;
import toystore.domain.Orderline;
import toystore.domain.Orders;
import toystore.repository.ItemRepository;
import toystore.repository.OrderRepository;
import toystore.repository.OrderlineRepository;

@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class testEmptyOrderService extends AbstractTestNGSpringContextTests{
    @Autowired
    EmptyOrderService emptyOrderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderlineRepository orderlineRepository;

    private Orders order;
    private Item item;
    private Orderline orderline;
    private List<Orderline> orderlinesblank;
    private boolean bool = false;
    private Date date = new Date();

    public testEmptyOrderService()
    {

    }

    @Before
    public void setUp()
    {
        orderlinesblank = new ArrayList<Orderline>();
    }

    @Test
    public void testAddOrderline()
    {
        orderline = new Orderline.Builder(1).build();
        orderlineRepository.save(orderline);
    }

    @Test(dependsOnMethods = "testAddOrderline")
    public void testAddItem()
    {
        item = ItemFactory.createItem("Teddybear", "Kiddies", 1, 200, orderlinesblank);
        Assert.assertNotNull(item);
        itemRepository.save(item);
        item = itemRepository.findOne(item.getID());
        List<Orderline> orderlinesi = item.getOrderlines();
        orderlinesi.add(orderline);
        item = new Item
                .Builder(item.getName())
                .copy(item)
                .orderlines(orderlinesi)
                .build();
        itemRepository.save(item);
    }
    @Test(dependsOnMethods = "testAddItem")
    public void testAddOrder()
    {
        order = OrderFactory.createOrder(date, 200, false, orderlinesblank);
        Assert.assertNotNull(order);
        orderRepository.save(order);
        order = orderRepository.findOne(order.getID());
        List<Orderline> orderlineso = order.getOrderlines();
        orderlineso.add(orderline);
        order = new Orders
                .Builder(order.getDateModified())
                .copy(order)
                .orderlines(orderlineso)
                .build();
        orderRepository.save(order);
    }
    @Test(dependsOnMethods = "testAddOrder")
    public void testEmptyOrder()
    {
        order = orderRepository.findOne(order.getID());
        Assert.assertEquals(order.getOrderlines().size(),1);
        bool = emptyOrderService.emptyOrder(order.getID());
        Assert.assertTrue(bool);
        order = orderRepository.findOne(order.getID());
        Assert.assertEquals(order.getOrderlines().size(),0);
    }

    @After
    public void tearDown()
    {
        orderlineRepository.delete(orderline);
        itemRepository.delete(item);
        orderRepository.delete(order);
    }
}
