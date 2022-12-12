package hw06.task1.services;

import hw06.task1.dto.CountryEditDto;
import hw06.task1.dto.CountryDto;
import hw06.task1.dto.ProductDto;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
public interface CountryService {
    
    CountryDto create(CountryEditDto countryToCreate);
    CountryDto update(Integer id, CountryEditDto countryToUpdate);
    void deleteById(Integer id);
    List<CountryDto> findAll();
    CountryDto findById(Integer id);
    List<ProductDto> findProducts(Integer id);
}
