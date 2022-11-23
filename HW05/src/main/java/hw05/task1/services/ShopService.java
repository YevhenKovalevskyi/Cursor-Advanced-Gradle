package hw05.task1.services;

import hw05.task1.entities.Employee;
import hw05.task1.entities.Shop;
import hw05.task1.exceptions.DataNotFoundException;
import hw05.task1.exceptions.ShopNotFoundException;
import hw05.task1.mappers.ShopMapper;
import hw05.task1.messages.Messages;
import hw05.task1.repositories.ShopRepository;

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
public class ShopService {
    
    private ShopRepository shopRepository;
    
    public Shop checkFound(Integer id, Optional<Shop> shop) {
        return shop.orElseThrow(() -> {
            log.error(Messages.SHOP_NOT_FOUND.getLogMessage(), id);
            throw new ShopNotFoundException(
                    String.format(Messages.SHOP_NOT_FOUND.getOutMessage(), id)
            );
        });
    }
    
    public Shop save(Shop newShop) {
        newShop = Shop.build(newShop);
        
        return shopRepository.save(newShop);
    }
    
    public Shop save(Integer id, Shop newShop) {
        Shop currShop = checkFound(id, shopRepository.findById(id));
        newShop = ShopMapper.getForUpdate(id, currShop, newShop);

        return shopRepository.save(newShop);
    }

    public void deleteById(Integer id) {
        checkFound(id, shopRepository.findById(id));
        shopRepository.deleteById(id);
    }
    
    public List<Shop> findAll() {
        List<Shop> shops = (List<Shop>) shopRepository.findAll();
    
        if (shops.isEmpty()) {
            log.error(Messages.DATA_NOT_FOUND.getLogMessage());
            throw new DataNotFoundException(Messages.DATA_NOT_FOUND.getOutMessage());
        }
    
        return shops;
    }
    
    public Shop findById(Integer id) {
        return checkFound(id, shopRepository.findById(id));
    }
    
    public List<Employee> findEmployees(Integer id) {
        return checkFound(id, shopRepository.findById(id)).getEmployees();
    }
}
