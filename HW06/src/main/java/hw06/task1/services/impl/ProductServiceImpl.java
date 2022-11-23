package hw06.task1.services.impl;

import hw06.task1.entities.Product;
import hw06.task1.exceptions.DataNotFoundException;
import hw06.task1.exceptions.ProductNotFoundException;
import hw06.task1.mappers.ProductMapper;
import hw06.task1.messages.Messages;
import hw06.task1.repositories.ProductRepository;
import hw06.task1.services.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    
    private ProductRepository productRepository;
    
    public Product checkFound(Integer id, Optional<Product> product) {
        return product.orElseThrow(() -> {
            log.error(Messages.PRODUCT_NOT_FOUND.getLogMessage(), id);
            throw new ProductNotFoundException(
                    String.format(Messages.PRODUCT_NOT_FOUND.getOutMessage(), id)
            );
        });
    }
    
    public Product save(Product newProduct) {
        return productRepository.save(Product.build(newProduct));
    }
    
    public Product save(Integer id, Product newProduct) {
        Product currProduct = checkFound(id, productRepository.findById(id));
        newProduct = ProductMapper.getForUpdate(id, currProduct, newProduct);
        
        return productRepository.save(newProduct);
    }
    
    public void deleteById(Integer id) {
        checkFound(id, productRepository.findById(id));
        productRepository.deleteById(id);
    }
    
    public List<Product> findAll() {
        List<Product> products = (List<Product>) productRepository.findAll();
        
        if (products.isEmpty()) {
            log.error(Messages.DATA_NOT_FOUND.getLogMessage());
            throw new DataNotFoundException(Messages.DATA_NOT_FOUND.getOutMessage());
        }
        
        return products;
    }
    
    public Product findById(Integer id) {
        return checkFound(id, productRepository.findById(id));
    }
    
    public List<Product> findByMaxUseBefore(int useBefore) {
        return (List<Product>) productRepository.findByUseBeforeLessThan(useBefore);
    }
    
    public List<Product> findByMinPrice(Float price) {
        return (List<Product>) productRepository.findByPriceGreaterThan(price);
    }
    
    public List<Product> findByBestBeforeDate(int manufactured, int useBefore) {
        return (List<Product>) productRepository.findByBestBeforeDate(manufactured, useBefore);
    }
}
