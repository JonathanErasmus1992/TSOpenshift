package toystore.repository;

import org.springframework.data.repository.CrudRepository;

import toystore.domain.Item;


/**
 * Created by Thawhir on 2015/10/05.
 */
public interface ItemRepository extends CrudRepository<Item,Long> {
}
