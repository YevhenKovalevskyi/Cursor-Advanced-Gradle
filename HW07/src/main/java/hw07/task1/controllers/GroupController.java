package hw07.task1.controllers;

import hw07.task1.dto.GroupDto;
import hw07.task1.dto.StudentLightDto;
import hw07.task1.entities.Group;
import hw07.task1.mappers.GroupMapper;
import hw07.task1.mappers.StudentMapper;
import hw07.task1.services.GroupService;

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
    public GroupDto create(@RequestBody Group group) {
        return GroupMapper.getForShow(groupService.save(group));
    }
    
    /**
     * Update Group by ID from FormData
     */
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GroupDto update(@PathVariable Integer id, @RequestBody Group group) {
        return GroupMapper.getForShow(groupService.save(id, group));
    }
    
    /**
     * Delete Group by ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id) {
        groupService.deleteById(id);
    }
    
    /**
     * Get all Countries
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDto> getAll() {
        return groupService.findAll()
                .stream().map(GroupMapper::getForShow).toList();
    }
    
    /**
     * Get Group by ID
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GroupDto getOne(@PathVariable Integer id) {
        return GroupMapper.getForShow(groupService.findById(id));
    }
    
    /**
     * Get Group Students by ID
     */
    @GetMapping("/{id}/students")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentLightDto> getStudents(@PathVariable Integer id) {
        return groupService.findById(id).getStudents()
                .stream().map(StudentMapper::getForShowSingle).toList();
    }
    
    /**
     * Get Group Students count by ID
     */
    @GetMapping("/{id}/students/count")
    @ResponseStatus(HttpStatus.OK)
    public int getStudentsCount(@PathVariable Integer id) {
        return groupService.findById(id).getStudents().size();
    }
}
