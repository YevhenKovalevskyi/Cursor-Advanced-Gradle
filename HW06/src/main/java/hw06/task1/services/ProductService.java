package hw06.task1.services;

import hw06.task1.dto.ProductEditDto;
import hw06.task1.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
public interface ProductService {
    
    ProductDto create(ProductEditDto productDto);
    ProductDto update(Integer id, ProductEditDto productDto);
    void deleteById(Integer id);
    List<ProductDto> findAll();
    ProductDto findById(Integer id);
    List<ProductDto> findByMaxUseBefore(int useBefore);
    List<ProductDto> findByMinPrice(BigDecimal price);
    List<ProductDto> findByBestBeforeDate(int manufactured, int useBefore);
}
