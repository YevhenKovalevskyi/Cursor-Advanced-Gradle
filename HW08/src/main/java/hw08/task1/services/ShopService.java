package hw08.task1.services;

import hw08.task1.entities.Shop;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
public interface ShopService {
    
    Shop save(HttpServletRequest request);
    Shop save(Integer id, HttpServletRequest request);
    void deleteById(Integer id);
    List<Shop> findAll();
    Shop findById(Integer id);
}
