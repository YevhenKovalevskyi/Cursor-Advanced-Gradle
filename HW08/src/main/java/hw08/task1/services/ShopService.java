package hw08.task1.services;

import hw08.task1.dto.EmployeeDto;
import hw08.task1.dto.ShopDto;
import hw08.task1.dto.ShopEditDto;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
public interface ShopService {
    
    ShopDto create(ShopEditDto shopToCreate);
    ShopDto update(Integer id, ShopEditDto shopToUpdate);
    void deleteById(Integer id);
    List<ShopDto> findAll();
    ShopDto findById(Integer id);
    List<EmployeeDto> findEmployees(Integer id);
    int findEmployeesCount(Integer id);
}
