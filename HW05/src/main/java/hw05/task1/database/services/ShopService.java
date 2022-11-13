package hw05.task1.database.services;

import hw05.task1.database.entities.Shop;
import hw05.task1.database.repositories.ShopRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@Slf4j
@Service
public class ShopService {
    
    @Autowired
    private ShopRepository shopRepository;
    
    public Shop save(Shop shop) {
        return shopRepository.save(shop);
    }

    public void deleteById(Integer id) {
        shopRepository.deleteById(id);
    }
    
    public List<Shop> findAll() {
        return (List<Shop>) shopRepository.findAll();
    }
    
    public Shop findById(Integer id) {
        return shopRepository.findById(id).orElse(null);
    }
}
