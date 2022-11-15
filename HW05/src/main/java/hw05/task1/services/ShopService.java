package hw05.task1.services;

import hw05.task1.entities.Employee;
import hw05.task1.entities.Shop;
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
    
    public Shop save(Shop newShop) {
        newShop = Shop.build(newShop);
        
        return shopRepository.save(newShop);
    }
    
    public Shop save(Integer id, Shop newShop) {
        Optional<Shop> currShop = shopRepository.findById(id);
        
        if (currShop.isEmpty()) {
            log.error(Messages.SHOP_NOT_FOUND.getLogMessage(), id);
            throw new ShopNotFoundException(
                    String.format(Messages.SHOP_NOT_FOUND.getOutMessage(), id)
            );
        }
    
        newShop = ShopMapper.getForUpdate(id, currShop.get(), newShop);
        
        return shopRepository.save(newShop);
    }

    public void deleteById(Integer id) {
        Optional<Shop> currShop = shopRepository.findById(id);
    
        if (currShop.isEmpty()) {
            log.error(Messages.SHOP_NOT_FOUND.getLogMessage(), id);
            throw new ShopNotFoundException(
                    String.format(Messages.SHOP_NOT_FOUND.getOutMessage(), id)
            );
        }
        
        shopRepository.deleteById(id);
    }
    
    public List<Shop> findAll() {
        return (List<Shop>) shopRepository.findAll();
    }
    
    public Shop findById(Integer id) {
        Optional<Shop> currShop = shopRepository.findById(id);
    
        if (currShop.isEmpty()) {
            log.error(Messages.SHOP_NOT_FOUND.getLogMessage(), id);
            throw new ShopNotFoundException(
                    String.format(Messages.SHOP_NOT_FOUND.getOutMessage(), id)
            );
        }
    
        return currShop.get();
    }
    
    public List<Employee> findEmployees(Integer id) {
        Optional<Shop> currShop = shopRepository.findById(id);
    
        if (currShop.isEmpty()) {
            log.error(Messages.SHOP_NOT_FOUND.getLogMessage(), id);
            throw new ShopNotFoundException(
                    String.format(Messages.SHOP_NOT_FOUND.getOutMessage(), id)
            );
        }
        
        return currShop.get().getEmployees();
    }
}
