package toystore.domain;

import toystore.conf.CustomerFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.util.List;

public class testCustomer{
    private Customer customer;
    private List<Orders> orders;
    private List<Invoice> invoices;

    @Before
    public void setUp()
    {
        customer = CustomerFactory.createCustomer("Redc","password","Thawhir","Jakoet","12345","12345",orders,invoices);
    }

    @Test
    public void testCreate() throws Exception
    {
        Assert.assertEquals(customer.getUserName(), "Redc");
    }

    @Test
    public void testUpdate()
    {
        Customer newCustomer = new Customer
                                    .Builder(customer.getUserName())
                                    .copy(customer)
                                    .lastName("Samsodien")
                                    .build();
        Assert.assertEquals(newCustomer.getContact(),customer.getContact());
        Assert.assertNotEquals(newCustomer.getLastName(), customer.getLastName());
    }

    @After
    public void tearDown()
    {

    }

}
