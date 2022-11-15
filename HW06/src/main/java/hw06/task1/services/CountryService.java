package hw06.task1.services;

import hw06.task1.entities.Country;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
public interface CountryService {
    
    Country save(Country country);
    Country save(Integer id, Country country);
    void deleteById(Integer id);
    List<Country> findAll();
    Country findById(Integer id);
}
