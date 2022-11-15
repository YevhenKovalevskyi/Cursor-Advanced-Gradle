package hw06.task1.dto;

import hw06.task1.entities.Country;
import lombok.Builder;
import lombok.Data;

/**
 * @author YevhenKovalevskyi
 */
@Data
@Builder
public class CountryDto {
    
    private Integer id;
    private String name;
    
    public static CountryDto build(Country country) {
        return CountryDto.builder()
                .id(country.getId())
                .name(country.getName())
                .build();
    }
}
