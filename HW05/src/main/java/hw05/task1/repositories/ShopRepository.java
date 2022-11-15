package hw05.task1.repositories;

import hw05.task1.entities.Shop;
import org.springframework.data.repository.CrudRepository;

/**
 * @author YevhenKovalevskyi
 */
public interface ShopRepository extends CrudRepository<Shop, Integer> {

}
