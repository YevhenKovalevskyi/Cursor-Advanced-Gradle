package hw06.task1.dto;

import hw06.task1.entities.Product;
import hw06.task1.mappers.CountryMapper;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author YevhenKovalevskyi
 */
@Data
@Builder
public class ProductDto {
    
    private Integer id;
    private String name;
    private BigDecimal price;
    private String description;
    private Integer manufactured;
    private Integer useBefore;
    private CountryDto country;
    
    public static ProductDto build(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .country(
                        CountryMapper.getForShow(product.getCountry())
                )
                .manufactured(product.getManufactured())
                .useBefore(product.getUseBefore())
                .build();
    }
}
