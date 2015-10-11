package toystore.repository;

import org.springframework.data.repository.CrudRepository;

import toystore.domain.Orderline;

public interface OrderlineRepository extends CrudRepository<Orderline,Long>{
}
