package toystore.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import toystore.repository.CustomerRepository;

@Service
public class DeleteCustomerService implements DeleteCustomerDetails{
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public boolean deleteCustomer(Long customerID)
    {

        return true;
    }
}
