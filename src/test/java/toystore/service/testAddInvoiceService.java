package toystore.service;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

import toystore.App;
import toystore.conf.CustomerFactory;
import toystore.conf.OrderFactory;
import toystore.domain.Customer;
import toystore.domain.Invoice;
import toystore.domain.Orderline;
import toystore.domain.Orders;
import toystore.repository.CustomerRepository;
import toystore.repository.InvoiceRepository;
import toystore.repository.OrderRepository;

@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class testAddInvoiceService extends AbstractTestNGSpringContextTests{
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    AddInvoiceService addInvoiceService;

    private Customer customer;
    private Orders order;
    private Invoice invoice;
    private Long customerid;
    private Long orderid;
    private List<Orders> orders;
    private List<Invoice> invoices;
    private List<Orderline> orderlines;

    @Before
    public void setUp()
    {

    }

    @Test
    public void testCreateCustomer()
    {
        customer = CustomerFactory.createCustomer("Redc", "password", "Thawhir", "Jakoet", "12345", "12345", orders, invoices);
        customerRepository.save(customer);
        Assert.assertNotNull(customer.getID());
        customerid = customer.getID();
    }

    @Test(dependsOnMethods = "testCreateCustomer")
    public void testCreateOrder()
    {
        order = OrderFactory.createOrder(new Date(), 200, false, orderlines);
        orderRepository.save(order);
        Assert.assertNotNull(order.getID());
        orderid = order.getID();
    }

    @Test(dependsOnMethods = "testCreateOrder")
    public void testAddInvoice()
    {
        Long invoiceid = addInvoiceService.addInvoice(customerid, orderid);
        Assert.assertNotNull(invoiceid);
        invoice = invoiceRepository.findOne(invoiceid);
        Assert.assertEquals(invoice.getTotalPrice(), 200, 0.001);
    }

    @Test(dependsOnMethods = "testAddInvoice")
    public void deleteThem()
    {
        orderRepository.delete(order);
        invoiceRepository.delete(invoice);
        customerRepository.delete(customer);
        Assert.assertTrue(true);
    }

    @AfterMethod
    public void tearDown()
    {

    }
}
