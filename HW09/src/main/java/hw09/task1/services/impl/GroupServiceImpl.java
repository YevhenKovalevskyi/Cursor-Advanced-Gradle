package hw09.task1.services.impl;

import hw09.task1.entities.Group;
import hw09.task1.entities.Student;
import hw09.task1.exceptions.GroupNotFoundException;
import hw09.task1.mappers.GroupMapper;
import hw09.task1.messages.Messages;
import hw09.task1.services.GroupService;
import hw09.task1.repositories.GroupRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@Service
@Slf4j
public class GroupServiceImpl implements GroupService {
    
    private GroupRepository groupRepository;
    
    public Group findByIdIfExists(Integer id) {
        return groupRepository.findById(id).orElseThrow(() -> {
            log.error(Messages.GROUP_NOT_FOUND.getLogMessage(), id);
            throw new GroupNotFoundException(
                    String.format(Messages.GROUP_NOT_FOUND.getOutMessage(), id)
            );
        });
    }
    
    public Group save(Group newGroup) {
        return groupRepository.save(Group.build(newGroup));
    }
    
    public Group save(Integer id, Group newGroup) {
        Group currGroup = findByIdIfExists(id);
        newGroup = GroupMapper.getForUpdate(id, currGroup, newGroup);
        
        return groupRepository.save(newGroup);
    }
    
    public void deleteById(Integer id) {
        findByIdIfExists(id);
        groupRepository.deleteById(id);
    }
    
    public List<Group> findAll() {
        return (List<Group>) groupRepository.findAll();
    }
    
    public Group findById(Integer id) {
        return findByIdIfExists(id);
    }
    
    public List<Student> findStudents(Integer id) {
        return findByIdIfExists(id).getStudents();
    }
    
    public int findStudentsCount(Integer id) {
        return findStudents(id).size();
    }
}
