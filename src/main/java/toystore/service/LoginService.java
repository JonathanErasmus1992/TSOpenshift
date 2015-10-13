package toystore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import toystore.domain.Customer;
import toystore.repository.CustomerRepository;


@Service
public class LoginService implements LoginDetails {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer Login(String userName, String password)
    {
        Iterable<Customer> icustomers = customerRepository.findAll();
        for(Customer cust: icustomers)
        {
            if(cust.getUserName().equalsIgnoreCase(userName) && cust.getPassword().equals(password))
                return cust;
        }
        return null;
    }

}
