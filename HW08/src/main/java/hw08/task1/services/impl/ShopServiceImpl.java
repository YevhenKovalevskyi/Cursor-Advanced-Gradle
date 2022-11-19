package hw08.task1.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import hw08.task1.entities.Shop;
import hw08.task1.exceptions.ShopNotFoundException;
import hw08.task1.mappers.ShopMapper;
import hw08.task1.messages.Messages;
import hw08.task1.repositories.ShopRepository;
import hw08.task1.services.ShopService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@Service
@Slf4j
public class ShopServiceImpl implements ShopService {
    
    private ShopRepository shopRepository;
    private ObjectMapper mapper;
    
    private Shop getIfExists(Integer id) {
        try {
            return shopRepository.getReferenceById(id);
        } catch (EntityNotFoundException e) {
            log.error(Messages.SHOP_NOT_FOUND.getLogMessage(), id);
            throw new ShopNotFoundException(
                    String.format(Messages.SHOP_NOT_FOUND.getOutMessage(), id)
            );
        }
    }
    
    private Shop getFromRequest(HttpServletRequest request) {
        try {
            return mapper.readValue(request.getInputStream(), Shop.class);
        } catch (IOException e) {
            log.error(Messages.REQUEST_ALREADY_TAKEN.getLogMessage());
            throw new ShopNotFoundException(Messages.REQUEST_ALREADY_TAKEN.getOutMessage());
        }
    }
    
    public Shop save(HttpServletRequest request) {
        return shopRepository.save(
                Shop.build(getFromRequest(request))
        );
    }
    
    public Shop save(Integer id, HttpServletRequest request) {
        Shop currShop = getIfExists(id);
        Shop newShop = getFromRequest(request);
    
        return shopRepository.save(
                ShopMapper.getForUpdate(id, currShop, newShop)
        );
    }
    
    public void deleteById(Integer id) {
        getIfExists(id);
        shopRepository.deleteById(id);
    }
    
    public List<Shop> findAll() {
        return shopRepository.findAll();
    }
    
    public Shop findById(Integer id) {
        return getIfExists(id);
    }
}
