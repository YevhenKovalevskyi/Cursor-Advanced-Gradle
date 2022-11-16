package hw06.task1.services.impl;

import hw06.task1.entities.Country;
import hw06.task1.exceptions.CountryNotFoundException;
import hw06.task1.mappers.CountryMapper;
import hw06.task1.messages.Messages;
import hw06.task1.repositories.CountryRepository;
import hw06.task1.services.CountryService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@Service
@Slf4j
public class CountryServiceImpl implements CountryService {
    
    CountryRepository countryRepository;
    
    public Country checkFound(Integer id, Optional<Country> country) {
        return country.orElseThrow(() -> {
            log.error(Messages.COUNTRY_NOT_FOUND.getLogMessage(), id);
            throw new CountryNotFoundException(
                    String.format(Messages.COUNTRY_NOT_FOUND.getOutMessage(), id)
            );
        });
    }
    
    public Country save(Country newCountry) {
        return countryRepository.save(Country.build(newCountry));
    }
    
    public Country save(Integer id, Country newCountry) {
        Country currCountry = checkFound(id, countryRepository.findById(id));
        newCountry = CountryMapper.getForUpdate(id, currCountry, newCountry);
    
        return countryRepository.save(newCountry);
    }
    
    public void deleteById(Integer id) {
        checkFound(id, countryRepository.findById(id));
        countryRepository.deleteById(id);
    }
    
    public List<Country> findAll() {
        return (List<Country>) countryRepository.findAll();
    }
    
    public Country findById(Integer id) {
        return checkFound(id, countryRepository.findById(id));
    }
}
