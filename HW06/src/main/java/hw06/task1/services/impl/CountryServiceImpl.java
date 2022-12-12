package hw06.task1.services.impl;

import hw06.task1.dto.CountryEditDto;
import hw06.task1.dto.CountryDto;
import hw06.task1.dto.ProductDto;
import hw06.task1.entities.Country;
import hw06.task1.exceptions.CountryNotFoundException;
import hw06.task1.mappers.CountryMapper;
import hw06.task1.mappers.ProductMapper;
import hw06.task1.messages.Messages;
import hw06.task1.repositories.CountryRepository;
import hw06.task1.services.CountryService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@Service
@Slf4j
public class CountryServiceImpl implements CountryService {
    
    private CountryRepository countryRepository;
    private CountryMapper countryMapper;
    private ProductMapper productMapper;
    
    public Country findByIdIfExists(Integer id) {
        return countryRepository.findById(id).orElseThrow(() -> {
            log.error(Messages.COUNTRY_NOT_FOUND.getLogMessage(), id);
            throw new CountryNotFoundException(
                    String.format(Messages.COUNTRY_NOT_FOUND.getOutMessage(), id)
            );
        });
    }
    
    public CountryDto create(CountryEditDto countryToCreate) {
        Country countryCreated = countryRepository.save(
                countryMapper.toCreateEntity(countryToCreate)
        );
        
        return countryMapper.toDto(countryCreated);
    }
    
    public CountryDto update(Integer id, CountryEditDto countryToUpdate) {
        Country countryCurrent = findByIdIfExists(id);
        Country countryUpdated = countryRepository.save(
                countryMapper.toUpdateEntity(countryCurrent, countryToUpdate)
        );
        
        return countryMapper.toDto(countryUpdated);
    }
    
    public void deleteById(Integer id) {
        findByIdIfExists(id);
        countryRepository.deleteById(id);
    }
    
    public List<CountryDto> findAll() {
        return ((List<Country>) countryRepository.findAll())
                .stream()
                .map(country -> countryMapper.toDto(country))
                .toList();
    }
    
    public CountryDto findById(Integer id) {
        return countryMapper.toDto(findByIdIfExists(id));
    }
    
    public List<ProductDto> findProducts(Integer id) {
        return findByIdIfExists(id).getProducts()
                .stream()
                .map(product -> productMapper.toDto(product))
                .toList();
    }
}
