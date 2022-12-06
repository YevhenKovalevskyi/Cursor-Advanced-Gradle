package hw08.task1.services;

import hw08.task1.entities.Employee;
import hw08.task1.entities.Shop;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
public interface ShopService {
    
    Shop create(Shop shop);
    Shop update(Integer id, Shop shop);
    void deleteById(Integer id);
    List<Shop> findAll();
    Shop findById(Integer id);
    List<Employee> findEmployees(Integer id);
    int findEmployeesCount(Integer id);
}
