package hw06.task1.mappers;

import hw06.task1.dto.ProductDto;
import hw06.task1.entities.Product;

/**
 * @author YevhenKovalevskyi
 */
public class ProductMapper {
    
    public static Product getForUpdate(Integer id, Product currentProduct, Product newProduct) {
        newProduct.setCreatedAt(currentProduct.getCreatedAt());
        
        return Product.build(id, newProduct);
    }
    
    public static ProductDto getForShow(Product product) {
        return ProductDto.build(product);
    }
}
