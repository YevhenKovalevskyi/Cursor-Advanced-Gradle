package hw07.task1.controllers;

import hw07.task1.dto.StudentDto;
import hw07.task1.dto.StudentEditDto;
import hw07.task1.services.StudentService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {
    
    private StudentService studentService;
    
    /**
     * Create Student from FormData
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDto create(@RequestBody StudentEditDto studentDto) {
        return studentService.create(studentDto);
    }
    
    /**
     * Update Student by ID from FormData
     */
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDto update(@PathVariable Integer id, @RequestBody StudentEditDto studentDto) {
        return studentService.update(id, studentDto);
    }
    
    /**
     * Delete Student by ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id) {
        studentService.deleteById(id);
    }
    
    /**
     * Get all Students
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDto> getAll() {
        return studentService.findAll();
    }
    
    /**
     * Get Student by ID
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDto getOne(@PathVariable Integer id) {
        return studentService.findById(id);
    }
}
