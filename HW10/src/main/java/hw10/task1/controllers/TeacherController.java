package hw10.task1.controllers;

import hw10.task1.dto.TeacherDto;
import hw10.task1.dto.TeacherEditDto;
import hw10.task1.dto.GroupDto;
import hw10.task1.dto.StudentDto;
import hw10.task1.services.TeacherService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@RestController
@RequestMapping("/teachers")
public class TeacherController {
    
    private TeacherService teacherService;
    
    /**
     * Create Teacher from FormData
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherDto create(@RequestBody TeacherEditDto teacherToCreate) {
        return teacherService.create(teacherToCreate);
    }
    
    /**
     * Update Teacher by ID from FormData
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherDto update(@PathVariable Integer id, @RequestBody TeacherEditDto teacherToUpdate) {
        return teacherService.update(id, teacherToUpdate);
    }
    
    /**
     * Delete Teacher by ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        teacherService.deleteById(id);
    }
    
    /**
     * Get all Teachers
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TeacherDto> getAll() {
        return teacherService.findAll();
    }
    
    /**
     * Get Teacher by ID
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherDto getOne(@PathVariable Integer id) {
        return teacherService.findById(id);
    }
    
    /**
     * Get Teacher Groups by ID
     */
    @GetMapping("/{id}/groups")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDto> getGroups(@PathVariable Integer id) {
        return teacherService.findGroups(id);
    }
    
    /**
     * Get Teacher Groups count by ID
     */
    @GetMapping("/{id}/groups/count")
    @ResponseStatus(HttpStatus.OK)
    public int getGroupsCount(@PathVariable Integer id) {
        return teacherService.findGroupsCount(id);
    }
    
    /**
     * Get Teacher Students by ID
     */
    @GetMapping("/{id}/students")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDto> getStudents(@PathVariable Integer id) {
        return teacherService.findStudents(id);
    }
    
    /**
     * Get Teacher Students count by ID
     */
    @GetMapping("/{id}/students/count")
    @ResponseStatus(HttpStatus.OK)
    public int getStudentsCount(@PathVariable Integer id) {
        return teacherService.findStudentsCount(id);
    }
}
