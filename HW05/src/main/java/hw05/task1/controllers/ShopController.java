package hw05.task1.controllers;

import hw05.task1.database.entities.Shop;
import hw05.task1.database.mappers.ShopMapper;
import hw05.task1.database.services.ShopService;
import hw05.task1.messages.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author YevhenKovalevskyi
 */
@RestController
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;
    
    /**
     * Create Shop from FormData
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Shop forCreate) {
        forCreate = Shop.build(forCreate);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(shopService.save(forCreate));
    }
    
    /**
     * Update Shop by ID from FormData
     */
    @PostMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Shop forUpdate) {
        Shop shop = shopService.findById(id);

        if (shop == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    String.format(Messages.SHOP_NOT_FOUND.getOutMessage(), id)
            );
        }
        
        forUpdate = ShopMapper.getForUpdate(shop, forUpdate, id);
    
        return ResponseEntity.status(HttpStatus.OK).body(shopService.save(forUpdate));
    }
    
    /**
     * Delete Shop by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Shop shop = shopService.findById(id);
        
        if (shop == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    String.format(Messages.SHOP_NOT_FOUND.getOutMessage(), id)
            );
        }
        
        shopService.deleteById(id);
        
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    /**
     * Get all Shops
     */
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(shopService.findAll());
    }
    
    /**
     * Get Shop by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        Shop shop = shopService.findById(id);
        
        if (shop == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    String.format(Messages.SHOP_NOT_FOUND.getOutMessage(), id)
            );
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(shop);
    }
    
    /**
     * Get Shop Employees by ID
     */
    @GetMapping("/{id}/employees")
    public ResponseEntity<?> getEmployees(@PathVariable Integer id) {
        Shop shop = shopService.findById(id);
        
        if (shop == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    String.format(Messages.SHOP_NOT_FOUND.getOutMessage(), id)
            );
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(shop.getEmployees());
    }
}
