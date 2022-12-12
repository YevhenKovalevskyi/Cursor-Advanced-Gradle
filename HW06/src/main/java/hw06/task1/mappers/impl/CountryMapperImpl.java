package hw06.task1.mappers.impl;

import hw06.task1.dto.CountryEditDto;
import hw06.task1.dto.CountryDto;
import hw06.task1.entities.Country;
import hw06.task1.mappers.CountryMapper;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@Component
public class CountryMapperImpl implements CountryMapper {
    
    private ModelMapper modelMapper;
    
    public CountryDto toDto(Country country) {
        return modelMapper.map(country, CountryDto.class);
    }
    
    public Country toCreateEntity(CountryEditDto countryToCreate) {
        Country country = modelMapper.map(countryToCreate, Country.class);
        country.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        country.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        
        return country;
    }
    
    public Country toUpdateEntity(Country countryCurrent, CountryEditDto countryToUpdate) {
        Country country = modelMapper.map(countryToUpdate, Country.class);
        country.setId(countryCurrent.getId());
        country.setCreatedAt(countryCurrent.getCreatedAt());
        country.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        
        return country;
    }
}
