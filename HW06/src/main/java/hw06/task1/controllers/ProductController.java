package hw06.task1.controllers;

import hw06.task1.dto.ProductDto;
import hw06.task1.entities.Product;
import hw06.task1.mappers.ProductMapper;
import hw06.task1.services.ProductService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    
    private ProductService productService;
    
    /**
     * Create Product from FormData
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto create(@RequestBody Product product) {
        return ProductMapper.getForShow(productService.save(product));
    }
    
    /**
     * Update Product by ID from FormData
     */
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto update(@PathVariable Integer id, @RequestBody Product product) {
        return ProductMapper.getForShow(productService.save(id, product));
    }
    
    /**
     * Delete Product by ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id) {
        productService.deleteById(id);
    }
    
    /**
     * Get all Products
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getAll() {
        return productService.findAll()
                .stream().map(ProductMapper::getForShow).toList();
    }
    
    /**
     * Get Product by ID
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto getOne(@PathVariable Integer id) {
        return ProductMapper.getForShow(productService.findById(id));
    }
    
    /*
     * Get all Products filtered by max useBefore
     */
    @GetMapping(value = "/filters/", params = {"max-use-before"})
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getByMaxUseBefore(@RequestParam("max-use-before") int useBefore) {
        return productService.findByMaxUseBefore(useBefore)
                .stream().map(ProductMapper::getForShow).toList();
    }
    
    /*
     * Get all Products filtered by min price
     */
    @GetMapping(value = "/filters/", params = {"min-price"})
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getByMinPrice(@RequestParam("min-price") int price) {
        return productService.findByMinPrice(price)
                .stream().map(ProductMapper::getForShow).toList();
    }
    
    /*
     * Get all Products filtered by manufactured and use-before years
     */
    @GetMapping(value = "/filters/", params = {"manufactured", "use-before"})
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getByBestBeforeDate(
            @RequestParam("manufactured") int manufactured, @RequestParam("use-before") int useBefore
    ) {
        return productService.findByBestBeforeDate(manufactured, useBefore)
                .stream().map(ProductMapper::getForShow).toList();
    }
}
