package hw09.task1.services;

import hw09.task1.entities.Group;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
public interface GroupService {
    
    Group save(Group group);
    Group save(Integer id, Group group);
    void deleteById(Integer id);
    List<Group> findAll();
    Group findById(Integer id);
}
