package hw07.task1.controllers;

import hw07.task1.dto.StudentDto;
import hw07.task1.entities.Student;
import hw07.task1.mappers.StudentMapper;
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
    public StudentDto create(@RequestBody Student student) {
        return StudentMapper.getForShow(studentService.save(student));
    }
    
    /**
     * Update Student by ID from FormData
     */
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDto update(@PathVariable Integer id, @RequestBody Student student) {
        return StudentMapper.getForShow(studentService.save(id, student));
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
        return studentService.findAll()
                .stream().map(StudentMapper::getForShow).toList();
    }
    
    /**
     * Get Student by ID
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDto getOne(@PathVariable Integer id) {
        return StudentMapper.getForShow(studentService.findById(id));
    }
}
