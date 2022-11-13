package hw05.task1.database.mappers;

import hw05.task1.database.entities.Shop;

/**
 * @author YevhenKovalevskyi
 */
public class ShopMapper {
    
    public static Shop getForUpdate(Shop currentShop, Shop newShop, Integer id) {
        newShop.setCreatedAt(currentShop.getCreatedAt());
        
        return Shop.build(newShop, id);
    }
}
