package toystore.repository;

/**
 * Created by Thawhir on 2015/10/05.
 */
import org.springframework.data.repository.CrudRepository;

import toystore.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer,Long>{
}