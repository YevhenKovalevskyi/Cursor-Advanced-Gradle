package hw08.task1.dto;

import hw08.task1.entities.Shop;

import lombok.Builder;
import lombok.Data;

/**
 * @author YevhenKovalevskyi
 */
@Data
@Builder
public class ShopDto {
    
    private Integer id;
    private String name;
    private String city;
    private String address;
    
    public static ShopDto build(Shop shop) {
        return ShopDto.builder()
                .id(shop.getId())
                .name(shop.getName())
                .city(shop.getCity())
                .address(shop.getAddress())
                .build();
    }
}
