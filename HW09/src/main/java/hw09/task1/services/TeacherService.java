package hw09.task1.services;

import hw09.task1.entities.Teacher;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
public interface TeacherService {
    
    Teacher save(Teacher teacher);
    Teacher save(Integer id, Teacher teacher);
    void deleteById(Integer id);
    List<Teacher> findAll();
    Teacher findById(Integer id);
}
