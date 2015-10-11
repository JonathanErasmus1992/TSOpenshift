package toystore.repository;


import org.springframework.data.repository.CrudRepository;

import toystore.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer,Long>{
}