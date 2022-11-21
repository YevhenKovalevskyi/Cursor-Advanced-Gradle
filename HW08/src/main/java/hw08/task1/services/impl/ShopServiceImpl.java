package hw08.task1.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import hw08.task1.entities.Shop;
import hw08.task1.exceptions.RequestAlreadyTakenException;
import hw08.task1.exceptions.ShopNotFoundException;
import hw08.task1.mappers.ShopMapper;
import hw08.task1.messages.Messages;
import hw08.task1.repositories.ShopRepository;
import hw08.task1.services.ShopService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

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
    
    private Shop getEntityIfExists(Integer id) {
        return shopRepository.findById(id).orElseThrow(() -> {
            log.error(Messages.SHOP_NOT_FOUND.getLogMessage(), id);
            throw new ShopNotFoundException(
                    String.format(Messages.SHOP_NOT_FOUND.getOutMessage(), id)
            );
        });
    }
    
    private Shop getDataFromRequest(HttpServletRequest request) {
        try {
            return mapper.readValue(request.getInputStream(), Shop.class);
        } catch (IOException e) {
            log.error(Messages.REQUEST_ALREADY_TAKEN.getLogMessage());
            throw new RequestAlreadyTakenException(Messages.REQUEST_ALREADY_TAKEN.getOutMessage());
        }
    }
    
    public Shop save(HttpServletRequest request) {
        return shopRepository.save(
                Shop.build(getDataFromRequest(request))
        );
    }
    
    public Shop save(Integer id, HttpServletRequest request) {
        Shop currShop = getEntityIfExists(id);
        Shop newShop = getDataFromRequest(request);
    
        return shopRepository.save(
                ShopMapper.getForUpdate(id, currShop, newShop)
        );
    }
    
    public void deleteById(Integer id) {
        getEntityIfExists(id);
        shopRepository.deleteById(id);
    }
    
    public List<Shop> findAll() {
        return shopRepository.findAll();
    }
    
    public Shop findById(Integer id) {
        return getEntityIfExists(id);
    }
}
