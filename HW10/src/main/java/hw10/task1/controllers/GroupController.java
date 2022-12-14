package hw10.task1.controllers;

import hw10.task1.dto.GroupDto;
import hw10.task1.dto.GroupEditDto;
import hw10.task1.dto.StudentDto;
import hw10.task1.services.GroupService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@RestController
@RequestMapping("/groups")
public class GroupController {
    
    private GroupService groupService;
    
    /**
     * Create Group from FormData
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroupDto create(@RequestBody GroupEditDto groupToCreate) {
        return groupService.create(groupToCreate);
    }
    
    /**
     * Update Group by ID from FormData
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GroupDto update(@PathVariable Integer id, @RequestBody GroupEditDto groupToUpdate) {
        return groupService.update(id, groupToUpdate);
    }
    
    /**
     * Delete Group by ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        groupService.deleteById(id);
    }
    
    /**
     * Get all Countries
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDto> getAll() {
        return groupService.findAll();
    }
    
    /**
     * Get Group by ID
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GroupDto getOne(@PathVariable Integer id) {
        return groupService.findById(id);
    }
    
    /**
     * Get Group Students by ID
     */
    @GetMapping("/{id}/students")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDto> getStudents(@PathVariable Integer id) {
        return groupService.findStudents(id);
    }
    
    /**
     * Get Group Students count by ID
     */
    @GetMapping("/{id}/students/count")
    @ResponseStatus(HttpStatus.OK)
    public int getStudentsCount(@PathVariable Integer id) {
        return groupService.findStudentsCount(id);
    }
}
