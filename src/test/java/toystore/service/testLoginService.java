package toystore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import toystore.App;
import toystore.conf.CustomerFactory;
import toystore.domain.Customer;
import toystore.domain.Invoice;
import toystore.domain.Orders;
import toystore.repository.CustomerRepository;

@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class testLoginService extends AbstractTestNGSpringContextTests {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    LoginService loginService;

    private List<Orders> orders;
    private List<Invoice> invoices;
    private Customer customer;

    @BeforeMethod
    public void setUp()
    {
        customer = CustomerFactory.createCustomer("JSME","password","Jonathan","Erasmus","12345","12345",orders,invoices);
        customerRepository.save(customer);
    }

    @Test
    public void testLogin()
    {
        Customer lcustomer = loginService.Login("JSME","password");
        Assert.assertNotNull(lcustomer);
        Assert.assertEquals(customer.getFirstName(), lcustomer.getFirstName());
    }

    @AfterMethod
    public void tearDown()
    {
        customerRepository.delete(customer);
    }

}
