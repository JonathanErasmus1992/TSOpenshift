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
public class testRegistrationService extends AbstractTestNGSpringContextTests{
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    RegistrationService registrationService;
    Customer customer;
    private List<Orders> orders;
    private List<Invoice> invoices;


    @BeforeMethod
    public void setUp()
    {
        customer = CustomerFactory.createCustomer("JSME", "password", "Jonathan", "Erasmus", "12345", "12345", orders, invoices);
    }

    @Test
    public void testRegistration()
    {
        boolean bool;
        bool = registrationService.Register("JSME", "password", "Jonathan", "Erasmus", "12345", "12345");
        Assert.assertEquals(bool, true);
        bool = registrationService.Register("JSME", "password", "Jonathan", "Erasmus", "12345", "12345");
        Assert.assertEquals(bool, false);
    }

    @AfterMethod
    public void tearDown()
    {
        customerRepository.delete(customer);
    }
}
