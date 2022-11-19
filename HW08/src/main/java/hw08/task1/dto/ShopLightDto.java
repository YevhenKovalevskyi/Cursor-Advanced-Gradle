package hw08.task1.dto;

import hw08.task1.entities.Shop;

import lombok.Builder;
import lombok.Data;

/**
 * @author YevhenKovalevskyi
 */
@Data
@Builder

public class ShopLightDto {
    
    private Integer id;
    private String name;
    
    public static ShopLightDto build(Shop shop) {
        return ShopLightDto.builder()
                .id(shop.getId())
                .name(shop.getName())
                .build();
    }
}
