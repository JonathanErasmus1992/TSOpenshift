package toystore.conf;

import java.util.List;

import toystore.domain.Customer;
import toystore.domain.Orders;
import toystore.domain.Invoice;

public class CustomerFactory {

    public static Customer createCustomer(String userName,String password,String firstName, String lastName, String idNumber, String contact, List<Orders> orders, List<Invoice> invoices)
    {
        Customer customer = new Customer
                .Builder(userName)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .idNumber(idNumber)
                .contact(contact)
                .orders(orders)
                .invoices(invoices)
                .build();
        return customer;
    }
}