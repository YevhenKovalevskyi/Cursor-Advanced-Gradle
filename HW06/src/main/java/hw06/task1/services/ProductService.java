package hw06.task1.services;

import hw06.task1.entities.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
public interface ProductService {
    
    Product save(Product product);
    Product save(Integer id, Product product);
    void deleteById(Integer id);
    List<Product> findAll();
    Product findById(Integer id);
    List<Product> findByMaxUseBefore(int useBefore);
    List<Product> findByMinPrice(BigDecimal price);
    List<Product> findByBestBeforeDate(int manufactured, int useBefore);
}
