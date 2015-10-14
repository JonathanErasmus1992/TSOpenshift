package toystore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

import toystore.App;
import toystore.conf.CustomerFactory;
import toystore.domain.Customer;
import toystore.domain.Invoice;
import toystore.domain.Orders;
import toystore.repository.CustomerRepository;

@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class testChangePasswordService extends AbstractTestNGSpringContextTests{
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ChangePasswordService changePasswordService;

    private List<Orders> orders;
    private List<Invoice> invoices;
    private Customer customer;
    private Customer updatedCustomer;
    private boolean bool;
    @BeforeMethod
    public void setUp()
    {
        customer = CustomerFactory.createCustomer("JSME","password","Jonathan","Erasmus","12345","12345",orders,invoices);
        customerRepository.save(customer);
    }

    @Test
    public void testChangePassword()
    {
        bool = changePasswordService.changePassword(customer.getID(),"newPassword");
        Assert.assertEquals(bool, true);
        updatedCustomer = customerRepository.findOne(customer.getID());
        Assert.assertNotEquals(customer.getPassword(),updatedCustomer.getPassword());
    }

    @AfterMethod
    public void tearDown()
    {
        customerRepository.delete(updatedCustomer);
    }

}
