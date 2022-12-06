package hw07.task1.controllers;

import hw07.task1.dto.*;
import hw07.task1.entities.Teacher;
import hw07.task1.mappers.GroupMapper;
import hw07.task1.mappers.StudentMapper;
import hw07.task1.mappers.TeacherMapper;
import hw07.task1.services.TeacherService;

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
    public TeacherDto create(@RequestBody Teacher teacher) {
        return TeacherMapper.getForShow(teacherService.create(teacher));
    }
    
    /**
     * Update Teacher by ID from FormData
     */
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherDto update(@PathVariable Integer id, @RequestBody Teacher teacher) {
        return TeacherMapper.getForShow(teacherService.update(id, teacher));
    }
    
    /**
     * Delete Teacher by ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id) {
        teacherService.deleteById(id);
    }
    
    /**
     * Get all Teachers
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TeacherDto> getAll() {
        return teacherService.findAll()
                .stream().map(TeacherMapper::getForShow).toList();
    }
    
    /**
     * Get Teacher by ID
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherDto getOne(@PathVariable Integer id) {
        return TeacherMapper.getForShow(teacherService.findById(id));
    }
    
    /**
     * Get Teacher Groups by ID
     */
    @GetMapping("/{id}/groups")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupLightDto> getGroups(@PathVariable Integer id) {
        return teacherService.findGroups(id)
                .stream().map(GroupMapper::getForShowSingle).toList();
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
    public List<StudentLightDto> getStudents(@PathVariable Integer id) {
        return teacherService.findStudents(id)
                .stream().map(StudentMapper::getForShowSingle).toList();
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
