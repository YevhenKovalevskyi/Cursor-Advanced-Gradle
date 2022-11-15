package hw06.task1.controllers;

import hw06.task1.dto.ProductDto;
import hw06.task1.entities.Country;
import hw06.task1.mappers.ProductMapper;
import hw06.task1.services.CountryService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@RestController
@RequestMapping("/countries")
public class CountryController {
    
    private CountryService countryService;
    
    /**
     * Create Country from FormData
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Country create(@RequestBody Country country) {
        return countryService.save(country);
    }
    
    /**
     * Update Country by ID from FormData
     */
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Country update(@PathVariable Integer id, @RequestBody Country country) {
        return countryService.save(country);
    }
    
    /**
     * Delete Country by ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id) {
        countryService.deleteById(id);
    }
    
    /**
     * Get all Countries
     */
    @GetMapping
    public List<Country> getAll() {
        return countryService.findAll();
    }
    
    /**
     * Get Country by ID
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Country getOne(@PathVariable Integer id) {
        return countryService.findById(id);
    }
    
    /**
     * Get Country Products by ID
     */
    @GetMapping("/{id}/products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getProducts(@PathVariable Integer id) {
        return countryService.findById(id).getProducts()
                .stream().map(ProductMapper::getForShow).toList();
    }
}
