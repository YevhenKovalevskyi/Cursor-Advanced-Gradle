package hw08.task1.repositories;

import hw08.task1.entities.Shop;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author YevhenKovalevskyi
 */
public interface ShopRepository extends JpaRepository<Shop, Integer> {

}
