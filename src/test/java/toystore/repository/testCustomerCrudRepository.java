package toystore.repository;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import org.junit.Assert;

import java.util.List;

import toystore.App;
import toystore.conf.CustomerFactory;
import toystore.domain.Customer;
import toystore.domain.Order;

/**
 * Created by Thawhir on 2015/10/05.
 */
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class testCustomerCrudRepository extends AbstractTestNGSpringContextTests{
    private Long id;
    private List<Order> orders;
    @Autowired
    CustomerRepository repository;

    @Test
    public void testCreate()
    {
        Customer customer = CustomerFactory.createCustomer("Redc","password","Thawhir","Jakoet","12345","12345",orders);
        repository.save(customer);
        id = customer.getID();
        Assert.assertNotNull(customer);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRead()
    {
        Customer customer = repository.findOne(id);
        Assert.assertEquals(customer.getUserName(), "Redc");
    }

    @Test(dependsOnMethods = "testRead")
    public void testUpdate()
    {
        Customer customer = repository.findOne(id);
        Customer newCustomer = new Customer
                                    .Builder(customer.getUserName())
                                    .copy(customer)
                                    .lastName("Samsodien")
                                    .build();
        repository.save(newCustomer);

        Customer updatedCustomer = repository.findOne(id);
        Assert.assertNotEquals(customer.getLastName(),updatedCustomer.getLastName());
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete()
    {
        Customer customer = repository.findOne(id);
        repository.delete(customer);
        Customer newCustomer = repository.findOne(id);
        Assert.assertNull(newCustomer);
    }

}
