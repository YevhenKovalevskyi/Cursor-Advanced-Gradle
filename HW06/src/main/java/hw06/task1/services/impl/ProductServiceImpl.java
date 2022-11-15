package hw06.task1.services.impl;

import hw06.task1.entities.Product;
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

    ProductRepository productRepository;
    
    public Product save(Product newProduct) {
        newProduct = Product.build(newProduct);
        
        return productRepository.save(newProduct);
    }
    
    public Product save(Integer id, Product newProduct) {
        Optional<Product> currProduct = productRepository.findById(id);
    
        if (currProduct.isEmpty()) {
            log.error(Messages.PRODUCT_NOT_FOUND.getLogMessage(), id);
            throw new ProductNotFoundException(
                    String.format(Messages.PRODUCT_NOT_FOUND.getOutMessage(), id)
            );
        }
        
        newProduct = ProductMapper.getForUpdate(id, currProduct.get(), newProduct);
        
        return productRepository.save(newProduct);
    }
    
    public void deleteById(Integer id) {
        Optional<Product> currProduct = productRepository.findById(id);
    
        if (currProduct.isEmpty()) {
            log.error(Messages.PRODUCT_NOT_FOUND.getLogMessage(), id);
            throw new ProductNotFoundException(
                    String.format(Messages.PRODUCT_NOT_FOUND.getOutMessage(), id)
            );
        }
        
        productRepository.deleteById(id);
    }
    
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }
    
    public Product findById(Integer id) {
        Optional<Product> currProduct = productRepository.findById(id);
    
        if (currProduct.isEmpty()) {
            log.error(Messages.PRODUCT_NOT_FOUND.getLogMessage(), id);
            throw new ProductNotFoundException(
                    String.format(Messages.PRODUCT_NOT_FOUND.getOutMessage(), id)
            );
        }
        
        return currProduct.get();
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
