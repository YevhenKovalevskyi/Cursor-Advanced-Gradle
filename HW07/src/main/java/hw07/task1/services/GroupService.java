package hw07.task1.services;

import hw07.task1.entities.Group;
import hw07.task1.entities.Student;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
public interface GroupService {
    
    Group create(Group group);
    Group update(Integer id, Group group);
    void deleteById(Integer id);
    List<Group> findAll();
    Group findById(Integer id);
    List<Student> findStudents(Integer id);
    int findStudentsCount(Integer id);
}
