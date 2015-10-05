package toystore.conf;

import java.util.List;

import toystore.domain.Customer;
import toystore.domain.Order;

/**
 * Created by Thawhir on 2015/10/05.
 */
public class CustomerFactory {

    public static Customer createCustomer(String userName,Long ID,String password,String firstName, String lastName, String idNumber, String contact, List<Order> orders)
    {
        Customer customer = new Customer
                .Builder(userName)
                .ID(ID)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .idNumber(idNumber)
                .contact(contact)
                .orders(orders)
                .build();
        return customer;
    }
}