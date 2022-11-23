package hw06.task1.services;

import hw06.task1.entities.Product;

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
    List<Product> findByMinPrice(int price);
    List<Product> findByBestBeforeDate(int manufactured, int useBefore);
}
