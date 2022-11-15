package hw05.task1.controllers;

import hw05.task1.entities.Employee;
import hw05.task1.entities.Shop;
import hw05.task1.services.ShopService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@RestController
@RequestMapping("/shops")
public class ShopController {

    private ShopService shopService;
    
    /**
     * Create Shop from FormData
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Shop create(@RequestBody Shop shop) {
        return shopService.save(shop);
    }
    
    /**
     * Update Shop by ID from FormData
     */
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Shop update(@PathVariable Integer id, @RequestBody Shop shop) {
        return shopService.save(id, shop);
    }
    
    /**
     * Delete Shop by ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id) {
        shopService.deleteById(id);
    }
    
    /**
     * Get all Shops
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Shop> getAll() {
        return shopService.findAll();
    }
    
    /**
     * Get Shop by ID
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Shop getOne(@PathVariable Integer id) {
        return shopService.findById(id);
    }
    
    /**
     * Get Shop Employees by ID
     */
    @GetMapping("/{id}/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployees(@PathVariable Integer id) {
        return shopService.findEmployees(id);
    }
}
