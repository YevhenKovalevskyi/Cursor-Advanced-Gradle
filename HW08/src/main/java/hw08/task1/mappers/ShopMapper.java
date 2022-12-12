package hw08.task1.mappers;

import hw08.task1.dto.ShopDto;
import hw08.task1.dto.ShopEditDto;
import hw08.task1.entities.Shop;

import org.mapstruct.Mapper;

/**
 * @author YevhenKovalevskyi
 */
@Mapper(componentModel = "spring")
public interface ShopMapper {
    
    ShopDto toDto(Shop shop);
    Shop toCreateEntity(ShopEditDto shopToCreate);
    Shop toUpdateEntity(Shop shopCurrent, ShopEditDto shopToUpdate);
}
