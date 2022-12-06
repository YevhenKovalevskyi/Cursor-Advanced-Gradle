package hw06.task1.services;

import hw06.task1.entities.Country;
import hw06.task1.entities.Product;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
public interface CountryService {
    
    Country create(Country country);
    Country update(Integer id, Country country);
    void deleteById(Integer id);
    List<Country> findAll();
    Country findById(Integer id);
    List<Product> findProducts(Integer id);
}
