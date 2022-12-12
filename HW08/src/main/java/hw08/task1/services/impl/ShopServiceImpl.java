package hw08.task1.services.impl;

import hw08.task1.dto.EmployeeDto;
import hw08.task1.dto.ShopDto;
import hw08.task1.dto.ShopEditDto;
import hw08.task1.entities.Shop;
import hw08.task1.exceptions.ShopNotFoundException;
import hw08.task1.mappers.EmployeeMapper;
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
    private ShopMapper shopMapper;
    private EmployeeMapper employeeMapper;
    
    private Shop findByIdIfExists(Integer id) {
        return shopRepository.findById(id).orElseThrow(() -> {
            log.error(Messages.SHOP_NOT_FOUND.getLogMessage(), id);
            throw new ShopNotFoundException(
                    String.format(Messages.SHOP_NOT_FOUND.getOutMessage(), id)
            );
        });
    }
    
    public ShopDto create(ShopEditDto shopToCreate) {
        Shop shopCreated = shopRepository.save(
                shopMapper.toCreateEntity(shopToCreate)
        );
    
        return shopMapper.toDto(shopCreated);
    }
    
    public ShopDto update(Integer id, ShopEditDto shopToUpdate) {
        Shop shopCurrent = findByIdIfExists(id);
        Shop shopUpdated = shopRepository.save(
                shopMapper.toUpdateEntity(shopCurrent, shopToUpdate)
        );
        
        return shopMapper.toDto(shopUpdated);
    }
    
    public void deleteById(Integer id) {
        findByIdIfExists(id);
        shopRepository.deleteById(id);
    }
    
    public List<ShopDto> findAll() {
        return shopRepository.findAll()
                .stream()
                .map(shop -> shopMapper.toDto(shop))
                .toList();
    }
    
    public ShopDto findById(Integer id) {
        return shopMapper.toDto(findByIdIfExists(id));
    }
    
    public List<EmployeeDto> findEmployees(Integer id) {
        return findByIdIfExists(id).getEmployees()
                .stream()
                .map(employee -> employeeMapper.toDto(employee))
                .toList();
    }
    
    public int findEmployeesCount(Integer id) {
        return findEmployees(id).size();
    }
}
