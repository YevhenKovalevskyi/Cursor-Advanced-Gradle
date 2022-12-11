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
    
    public Country toCreateEntity(CountryEditDto countryDto) {
        Country country = modelMapper.map(countryDto, Country.class);
        country.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        country.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        
        return country;
    }
    
    public Country toUpdateEntity(Country currentCountry, CountryEditDto countryDto) {
        Country country = modelMapper.map(countryDto, Country.class);
        country.setId(currentCountry.getId());
        country.setCreatedAt(currentCountry.getCreatedAt());
        country.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        
        return country;
    }
}
