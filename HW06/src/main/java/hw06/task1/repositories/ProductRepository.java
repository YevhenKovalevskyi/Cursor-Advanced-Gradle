package hw06.task1.repositories;

import hw06.task1.entities.Product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

/**
 * @author YevhenKovalevskyi
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {
    
    Iterable<Product> findByUseBeforeLessThan(int useBefore);
    
    Iterable<Product> findByPriceGreaterThan(BigDecimal price);
    
    @Query("select p from #{#entityName} p where p.manufactured = ?1 and p.useBefore = ?2")
    Iterable<Product> findByBestBeforeDate(int manufactured, int useBefore);
}
