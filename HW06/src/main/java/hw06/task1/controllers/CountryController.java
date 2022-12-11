package hw06.task1.controllers;

import hw06.task1.dto.CountryEditDto;
import hw06.task1.dto.CountryDto;
import hw06.task1.dto.ProductDto;
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
    public CountryDto create(@RequestBody CountryEditDto countryDto) {
        return countryService.create(countryDto);
    }
    
    /**
     * Update Country by ID from FormData
     */
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CountryDto update(@PathVariable Integer id, @RequestBody CountryEditDto countryDto) {
        return countryService.update(id, countryDto);
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
    @ResponseStatus(HttpStatus.OK)
    public List<CountryDto> getAll() {
        return countryService.findAll();
    }
    
    /**
     * Get Country by ID
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CountryDto getOne(@PathVariable Integer id) {
        return countryService.findById(id);
    }
    
    /**
     * Get Country Products by ID
     */
    @GetMapping("/{id}/products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getProducts(@PathVariable Integer id) {
        return countryService.findProducts(id);
    }
}
