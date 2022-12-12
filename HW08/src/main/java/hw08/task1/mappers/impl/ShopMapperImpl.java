package hw08.task1.mappers.impl;

import hw08.task1.dto.ShopDto;
import hw08.task1.dto.ShopEditDto;
import hw08.task1.entities.Shop;
import hw08.task1.mappers.ShopMapper;

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
public class ShopMapperImpl implements ShopMapper {
    
    private ModelMapper modelMapper;
    
    public ShopDto toDto(Shop shop) {
        return modelMapper.map(shop, ShopDto.class);
    }
    
    public Shop toCreateEntity(ShopEditDto shopToCreate) {
        Shop shop = modelMapper.map(shopToCreate, Shop.class);
        shop.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        shop.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        
        return shop;
    }
    
    public Shop toUpdateEntity(Shop shopCurrent, ShopEditDto shopToUpdate) {
        Shop shop = modelMapper.map(shopToUpdate, Shop.class);
        shop.setId(shopCurrent.getId());
        shop.setCreatedAt(shopCurrent.getCreatedAt());
        shop.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        
        return shop;
    }
}
