package toystore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

import toystore.App;
import toystore.conf.OrderFactory;
import toystore.domain.Orderline;
import toystore.domain.Orders;
import toystore.repository.OrderRepository;

@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class testCheckoutOrderService extends AbstractTestNGSpringContextTests{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CheckoutOrderService checkoutOrderService;

    private Orders order;
    private List<Orderline> orderlines;
    private Long id;
    @BeforeMethod
    public void setUp()
    {
        order = OrderFactory.createOrder(new Date(), 200, false, orderlines);
        orderRepository.save(order);
        id = order.getID();
    }

    @Test
    public void testCheckoutOrder()
    {
        boolean bool = false;
        bool = checkoutOrderService.checkoutOrder(id);
        Assert.assertTrue(bool);
        order = orderRepository.findOne(id);
        Assert.assertTrue(order.getCheckout());
    }

    @AfterMethod
    public void tearDown()
    {
        orderRepository.delete(order);
    }
}
