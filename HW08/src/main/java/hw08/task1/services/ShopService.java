package hw08.task1.services;

import hw08.task1.entities.Shop;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
public interface ShopService {
    
    Shop save(Shop shop);
    Shop save(Integer id, Shop shop);
    void deleteById(Integer id);
    List<Shop> findAll();
    Shop findById(Integer id);
}
