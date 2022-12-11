package hw06.task1.controllers;

import hw06.task1.dto.ProductEditDto;
import hw06.task1.dto.ProductDto;
import hw06.task1.services.ProductService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
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
    public ProductDto create(@RequestBody ProductEditDto productDto) {
        return productService.create(productDto);
    }
    
    /**
     * Update Product by ID from FormData
     */
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto update(@PathVariable Integer id, @RequestBody ProductEditDto productDto) {
        return productService.update(id, productDto);
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
        return productService.findAll();
    }
    
    /**
     * Get Product by ID
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto getOne(@PathVariable Integer id) {
        return productService.findById(id);
    }
    
    /*
     * Get all Products filtered by max useBefore
     */
    @GetMapping(value = "/filters/", params = {"max-use-before"})
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getByMaxUseBefore(@RequestParam("max-use-before") @Valid int useBefore) {
        return productService.findByMaxUseBefore(useBefore);
    }
    
    /*
     * Get all Products filtered by min price
     */
    @GetMapping(value = "/filters/", params = {"min-price"})
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getByMinPrice(@RequestParam("min-price") @Valid BigDecimal price) {
        return productService.findByMinPrice(price);
    }
    
    /*
     * Get all Products filtered by manufactured and use-before years
     */
    @GetMapping(value = "/filters/", params = {"manufactured", "use-before"})
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getByBestBeforeDate(
            @RequestParam("manufactured") @Valid int manufactured, @RequestParam("use-before") @Valid int useBefore
    ) {
        return productService.findByBestBeforeDate(manufactured, useBefore);
    }
}
