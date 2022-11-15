package hw05.task1.mappers;

import hw05.task1.entities.Shop;

/**
 * @author YevhenKovalevskyi
 */
public class ShopMapper {
    
    public static Shop getForUpdate(Integer id, Shop currentShop, Shop newShop) {
        newShop.setCreatedAt(currentShop.getCreatedAt());
        
        return Shop.build(id, newShop);
    }
}
