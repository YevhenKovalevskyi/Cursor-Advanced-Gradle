package hw08.task1.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import hw08.task1.actions.RequestAction;
import hw08.task1.actions.ResponseAction;
import hw08.task1.dto.EmployeeDto;
import hw08.task1.dto.ShopDto;
import hw08.task1.dto.ShopEditDto;
import hw08.task1.services.ShopService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        ShopEditDto shopToCreate = RequestAction.getRequestBody(request, mapper, ShopEditDto.class);
        
        ShopDto shopCreated = shopService.create(shopToCreate);
        
        ResponseAction.setResponse(response, mapper, HttpStatus.CREATED, shopCreated);
    }
    
    /**
     * Update Shop by ID from FormData
     */
    @PostMapping("/{id}")
    public void update(HttpServletRequest request, HttpServletResponse response) {
        Integer shopId = Integer.valueOf(RequestAction.getRequestParam(request, "id"));
        ShopEditDto shopToUpdate = RequestAction.getRequestBody(request, mapper, ShopEditDto.class);
        
        ShopDto shopUpdated = shopService.update(shopId, shopToUpdate);

        ResponseAction.setResponse(response, mapper, HttpStatus.OK, shopUpdated);
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
        List<ShopDto> shopsAll = shopService.findAll();
    
        ResponseAction.setResponse(response, mapper, HttpStatus.OK, shopsAll);
    }
    
    /**
     * Get Shop by ID
     */
    @GetMapping("/{id}")
    public void getOne(HttpServletRequest request, HttpServletResponse response) {
        Integer shopId = Integer.valueOf(RequestAction.getRequestParam(request, "id"));
        
        ShopDto shopById = shopService.findById(shopId);
    
        ResponseAction.setResponse(response, mapper, HttpStatus.OK, shopById);
    }
    
    /**
     * Get Shop Employees by ID
     */
    @GetMapping("/{id}/employees")
    public void getEmployees(HttpServletRequest request, HttpServletResponse response) {
        Integer shopId = Integer.valueOf(RequestAction.getRequestParam(request, "id"));
        
        List<EmployeeDto> employeesByShop = shopService.findEmployees(shopId);
    
        ResponseAction.setResponse(response, mapper, HttpStatus.OK, employeesByShop);
    }
    
    /**
     * Get Shop Employees count by ID
     */
    @GetMapping("/{id}/employees/count")
    @ResponseStatus(HttpStatus.OK)
    public void getEmployeesCount(HttpServletRequest request, HttpServletResponse response) {
        Integer shopId = Integer.valueOf(RequestAction.getRequestParam(request, "id"));
        
        Integer employeesByShopCount = shopService.findEmployeesCount(shopId);
    
        ResponseAction.setResponse(response, mapper, HttpStatus.OK, employeesByShopCount);
    }
}
