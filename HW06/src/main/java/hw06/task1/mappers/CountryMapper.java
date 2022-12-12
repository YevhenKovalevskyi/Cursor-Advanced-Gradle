package hw06.task1.mappers;

import hw06.task1.dto.CountryEditDto;
import hw06.task1.dto.CountryDto;
import hw06.task1.entities.Country;

import org.mapstruct.Mapper;

/**
 * @author YevhenKovalevskyi
 */
@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryDto toDto(Country country);
    Country toCreateEntity(CountryEditDto countryToCreate);
    Country toUpdateEntity(Country countryCurrent, CountryEditDto countryToUpdate);
}
