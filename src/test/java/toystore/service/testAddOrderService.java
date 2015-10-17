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
import java.util.List;

import toystore.App;
import toystore.conf.CustomerFactory;
import toystore.domain.Customer;
import toystore.domain.Invoice;
import toystore.domain.Orders;
import toystore.repository.CustomerRepository;
import toystore.repository.OrderRepository;

@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class testAddOrderService extends AbstractTestNGSpringContextTests{
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    AddOrderService addOrderService;

    private Customer customer;
    private List<Orders> orders;
    private List<Invoice> invoices;
    @Before
    public void setUp()
    {
        orders = new ArrayList<Orders>();
        invoices = new ArrayList<Invoice>();
    }

    @Test
    public void testCreateCustomer()
    {
        customer = CustomerFactory.createCustomer("Redc","password","Thawhir","Jakoet","12345","12345",orders,invoices);
        customerRepository.save(customer);
    }

    @Test(dependsOnMethods = "testCreateCustomer")
    public void testAddOrder()
    {
        boolean bool = false;
        bool = addOrderService.addOrder(customer.getID());
        Assert.assertTrue(bool);
        customer = customerRepository.findOne(customer.getID());
        Assert.assertEquals(customer.getOrders().size(),1);
    }
    //turned this into a test so that I could use TestNG-sorting, this is actually the tearDown() method
    @Test(dependsOnMethods = "testAddOrder")
    public void deleteThem()
    {
        customer = customerRepository.findOne(customer.getID());
        List<Orders> ordersc = customer.getOrders();
        for(Orders orderi: ordersc)
            orderRepository.delete(orderi);
        customerRepository.delete(customer);
        Assert.assertTrue(true);
    }

    @After
    public void tearDown()
    {

    }


}
