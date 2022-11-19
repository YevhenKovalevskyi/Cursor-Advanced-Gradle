package hw08.task1.controllers;

import hw08.task1.dto.EmployeeLightDto;
import hw08.task1.dto.ShopDto;
import hw08.task1.mappers.EmployeeMapper;
import hw08.task1.mappers.ShopMapper;
import hw08.task1.services.ShopService;

import hw08.task1.utils.HttpServletRequestUtil;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public ShopDto create(HttpServletRequest request) {
        return ShopMapper.getForShow(shopService.save(request));
    }
    
    /**
     * Update Shop by ID from FormData
     */
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ShopDto update(HttpServletRequest request) {
        Integer id = Integer.valueOf(HttpServletRequestUtil.getAttributeByKey(request, "id"));
        
        return ShopMapper.getForShow(shopService.save(id, request));
    }
    
    /**
     * Delete Shop by ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(HttpServletRequest request) {
        Integer id = Integer.valueOf(HttpServletRequestUtil.getAttributeByKey(request, "id"));
        shopService.deleteById(id);
    }
    
    /**
     * Get all Shops
     */
    @GetMapping
    public List<ShopDto> getAll() {
        return shopService.findAll()
                .stream().map(ShopMapper::getForShow).toList();
    }
    
    /**
     * Get Shop by ID
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ShopDto getOne(HttpServletRequest request) {
        Integer id = Integer.valueOf(HttpServletRequestUtil.getAttributeByKey(request, "id"));
        
        return ShopMapper.getForShow(shopService.findById(id));
    }
    
    /**
     * Get Shop Employees by ID
     */
    @GetMapping("/{id}/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeLightDto> getEmployees(HttpServletRequest request) {
        Integer id = Integer.valueOf(HttpServletRequestUtil.getAttributeByKey(request, "id"));
        
        return shopService.findById(id).getEmployees()
                .stream().map(EmployeeMapper::getForShowLight).toList();
    }
    
    /**
     * Get Shop Employees count by ID
     */
    @GetMapping("/{id}/employees/count")
    @ResponseStatus(HttpStatus.OK)
    public int getEmployeesCount(HttpServletRequest request) {
        Integer id = Integer.valueOf(HttpServletRequestUtil.getAttributeByKey(request, "id"));
        
        return shopService.findById(id).getEmployees().size();
    }
}
