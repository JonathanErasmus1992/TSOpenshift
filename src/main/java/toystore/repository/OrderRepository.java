package toystore.repository;

import org.springframework.data.repository.CrudRepository;

import toystore.domain.Order;

/**
 * Created by Thawhir on 2015/10/05.
 */
public interface OrderRepository extends CrudRepository<Order,Long> {
}
