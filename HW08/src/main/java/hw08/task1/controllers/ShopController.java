package hw08.task1.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import hw08.task1.actions.RequestAction;
import hw08.task1.actions.ResponseAction;
import hw08.task1.dto.EmployeeLightDto;
import hw08.task1.dto.ShopDto;
import hw08.task1.entities.Shop;
import hw08.task1.mappers.EmployeeMapper;
import hw08.task1.mappers.ShopMapper;
import hw08.task1.services.ShopService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@RestController
@RequestMapping("/shops")
public class ShopController {
    
    private ShopService shopService;
    private ObjectMapper mapper;
    
    /**
     * Create Shop from FormData
     */
    @PostMapping
    public void create(HttpServletRequest request, HttpServletResponse response) {
        Shop shop = RequestAction.getRequestBody(request, mapper, Shop.class);
        ShopDto shopDto = ShopMapper.getForShow(shopService.save(shop));

        ResponseAction.setResponse(response, mapper, HttpStatus.CREATED, shopDto);
    }
    
    /**
     * Update Shop by ID from FormData
     */
    @PostMapping("/{id}")
    public void update(HttpServletRequest request, HttpServletResponse response) {
        Integer shopId = Integer.valueOf(RequestAction.getRequestParam(request, "id"));
        Shop shop = RequestAction.getRequestBody(request, mapper, Shop.class);
        ShopDto shopDto = ShopMapper.getForShow(shopService.save(shopId, shop));
    
        ResponseAction.setResponse(response, mapper, HttpStatus.OK, shopDto);
    }
    
    /**
     * Delete Shop by ID
     */
    @DeleteMapping("/{id}")
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        Integer shopId = Integer.valueOf(RequestAction.getRequestParam(request, "id"));

        shopService.deleteById(shopId);
        ResponseAction.setResponse(response, mapper, HttpStatus.OK, "Deleted!");
    }
    
    /**
     * Get all Shops
     */
    @GetMapping
    public void getAll(HttpServletResponse response) {
        List<ShopDto> shopsDto = shopService.findAll()
                .stream().map(ShopMapper::getForShow).toList();
    
        ResponseAction.setResponse(response, mapper, HttpStatus.OK, shopsDto);
    }
    
    /**
     * Get Shop by ID
     */
    @GetMapping("/{id}")
    public void getOne(HttpServletRequest request, HttpServletResponse response) {
        Integer shopId = Integer.valueOf(RequestAction.getRequestParam(request, "id"));
        ShopDto shopDto = ShopMapper.getForShow(shopService.findById(shopId));
    
        ResponseAction.setResponse(response, mapper, HttpStatus.OK, shopDto);
    }
    
    /**
     * Get Shop Employees by ID
     */
    @GetMapping("/{id}/employees")
    public void getEmployees(HttpServletRequest request, HttpServletResponse response) {
        Integer shopId = Integer.valueOf(RequestAction.getRequestParam(request, "id"));
        List<EmployeeLightDto> employeesDto = shopService.findById(shopId).getEmployees()
                .stream().map(EmployeeMapper::getForShowLight).toList();
    
        ResponseAction.setResponse(response, mapper, HttpStatus.OK, employeesDto);
    }
    
    /**
     * Get Shop Employees count by ID
     */
    @GetMapping("/{id}/employees/count")
    @ResponseStatus(HttpStatus.OK)
    public void getEmployeesCount(HttpServletRequest request, HttpServletResponse response) {
        Integer shopId = Integer.valueOf(RequestAction.getRequestParam(request, "id"));
        Integer employeesCount = shopService.findById(shopId).getEmployees().size();
    
        ResponseAction.setResponse(response, mapper, HttpStatus.OK, employeesCount);
    }
}
