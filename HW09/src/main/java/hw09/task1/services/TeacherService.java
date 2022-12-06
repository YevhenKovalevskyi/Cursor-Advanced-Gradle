package hw09.task1.services;

import hw09.task1.entities.Student;
import hw09.task1.entities.Group;
import hw09.task1.entities.Teacher;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
public interface TeacherService {
    
    Teacher create(Teacher teacher);
    Teacher update(Integer id, Teacher teacher);
    void deleteById(Integer id);
    List<Teacher> findAll();
    Teacher findById(Integer id);
    List<Group> findGroups(Integer id);
    int findGroupsCount(Integer id);
    List<Student> findStudents(Integer id);
    int findStudentsCount(Integer id);
}
