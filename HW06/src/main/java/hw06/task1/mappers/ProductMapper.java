package hw06.task1.mappers;

import hw06.task1.dto.ProductEditDto;
import hw06.task1.dto.ProductDto;
import hw06.task1.entities.Product;

import org.mapstruct.Mapper;

/**
 * @author YevhenKovalevskyi
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {
    
    ProductDto toDto(Product product);
    Product toCreateEntity(ProductEditDto productToCreate);
    Product toUpdateEntity(Product productCurrent, ProductEditDto productToUpdate);
}
