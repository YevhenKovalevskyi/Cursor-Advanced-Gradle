package hw06.task1.services.impl;

import hw06.task1.dto.ProductEditDto;
import hw06.task1.dto.ProductDto;
import hw06.task1.entities.Product;
import hw06.task1.exceptions.ProductNotFoundException;
import hw06.task1.mappers.ProductMapper;
import hw06.task1.messages.Messages;
import hw06.task1.repositories.ProductRepository;
import hw06.task1.services.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    
    public Product findByIdIfExists(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> {
            log.error(Messages.PRODUCT_NOT_FOUND.getLogMessage(), id);
            throw new ProductNotFoundException(
                    String.format(Messages.PRODUCT_NOT_FOUND.getOutMessage(), id)
            );
        });
    }
    
    public ProductDto create(ProductEditDto productToCreate) {
        Product productCreated = productRepository.save(
                productMapper.toCreateEntity(productToCreate)
        );
        
        return productMapper.toDto(productCreated);
    }
    
    public ProductDto update(Integer id, ProductEditDto productToUpdate) {
        Product productCurrent = findByIdIfExists(id);
        Product productUpdated = productRepository.save(
                productMapper.toUpdateEntity(productCurrent, productToUpdate)
        );
        
        return productMapper.toDto(productUpdated);
    }
    
    public void deleteById(Integer id) {
        findByIdIfExists(id);
        productRepository.deleteById(id);
    }
    
    public List<ProductDto> findAll() {
        return ((List<Product>) productRepository.findAll())
                .stream()
                .map(product -> productMapper.toDto(product))
                .toList();
    }
    
    public ProductDto findById(Integer id) {
        return productMapper.toDto(findByIdIfExists(id));
    }
    
    public List<ProductDto> findByMaxUseBefore(int useBefore) {
        return ((List<Product>) productRepository.findByUseBeforeLessThan(useBefore))
                .stream()
                .map(product -> productMapper.toDto(product))
                .toList();
    }
    
    public List<ProductDto> findByMinPrice(BigDecimal price) {
        return ((List<Product>) productRepository.findByPriceGreaterThan(price))
                .stream()
                .map(product -> productMapper.toDto(product))
                .toList();
    }
    
    public List<ProductDto> findByBestBeforeDate(int manufactured, int useBefore) {
        return ((List<Product>) productRepository.findByBestBeforeDate(manufactured, useBefore))
                .stream()
                .map(product -> productMapper.toDto(product))
                .toList();
    }
}
