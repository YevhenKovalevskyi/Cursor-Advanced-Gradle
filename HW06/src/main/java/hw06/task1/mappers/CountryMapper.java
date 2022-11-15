package hw06.task1.mappers;

import hw06.task1.dto.CountryDto;
import hw06.task1.entities.Country;

/**
 * @author YevhenKovalevskyi
 */
public class CountryMapper {
    
    public static Country getForUpdate(Integer id, Country currentCountry, Country newCountry) {
        newCountry.setCreatedAt(currentCountry.getCreatedAt());
        
        return Country.build(id, newCountry);
    }
    
    public static CountryDto getForShow(Country country) {
        return CountryDto.build(country);
    }
}
