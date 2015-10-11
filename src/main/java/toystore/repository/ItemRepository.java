package toystore.repository;

import org.springframework.data.repository.CrudRepository;

import toystore.domain.Item;


public interface ItemRepository extends CrudRepository<Item,Long> {
}
