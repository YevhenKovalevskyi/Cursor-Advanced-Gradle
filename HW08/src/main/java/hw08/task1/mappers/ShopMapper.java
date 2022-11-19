package hw08.task1.mappers;

import hw08.task1.dto.ShopDto;
import hw08.task1.dto.ShopLightDto;
import hw08.task1.entities.Shop;

/**
 * @author YevhenKovalevskyi
 */
public class ShopMapper {
    
    public static Shop getForUpdate(Integer id, Shop currentShop, Shop newShop) {
        newShop.setCreatedAt(currentShop.getCreatedAt());
        
        return Shop.build(id, newShop);
    }
    
    public static ShopDto getForShow(Shop shop) {
        return ShopDto.build(shop);
    }
    
    public static ShopLightDto getForShowLight(Shop shop) {
        return ShopLightDto.build(shop);
    }
}
