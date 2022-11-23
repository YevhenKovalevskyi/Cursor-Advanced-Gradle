package hw08.task1.services.impl;

import hw08.task1.entities.Shop;
import hw08.task1.exceptions.DataNotFoundException;
import hw08.task1.exceptions.ShopNotFoundException;
import hw08.task1.mappers.ShopMapper;
import hw08.task1.messages.Messages;
import hw08.task1.repositories.ShopRepository;
import hw08.task1.services.ShopService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@Service
@Slf4j
public class ShopServiceImpl implements ShopService {
    
    private ShopRepository shopRepository;
    
    private Shop findByIdIfExists(Integer id) {
        return shopRepository.findById(id).orElseThrow(() -> {
            log.error(Messages.SHOP_NOT_FOUND.getLogMessage(), id);
            throw new ShopNotFoundException(
                    String.format(Messages.SHOP_NOT_FOUND.getOutMessage(), id)
            );
        });
    }
    
    public Shop save(Shop shop) {
        return shopRepository.save(
                Shop.build(shop)
        );
    }
    
    public Shop save(Integer id, Shop newShop) {
        Shop currShop = findByIdIfExists(id);
    
        return shopRepository.save(
                ShopMapper.getForUpdate(id, currShop, newShop)
        );
    }
    
    public void deleteById(Integer id) {
        findByIdIfExists(id);
        shopRepository.deleteById(id);
    }
    
    public List<Shop> findAll() {
        List<Shop> shops = shopRepository.findAll();
        
        if (shops.isEmpty()) {
            log.error(Messages.DATA_NOT_FOUND.getLogMessage());
            throw new DataNotFoundException(Messages.DATA_NOT_FOUND.getOutMessage());
        }
        
        return shops;
    }
    
    public Shop findById(Integer id) {
        return findByIdIfExists(id);
    }
}
