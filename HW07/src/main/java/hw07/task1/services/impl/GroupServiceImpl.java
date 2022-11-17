package hw07.task1.services.impl;

import hw07.task1.entities.Group;
import hw07.task1.exceptions.GroupNotFoundException;
import hw07.task1.mappers.GroupMapper;
import hw07.task1.messages.Messages;
import hw07.task1.services.GroupService;
import hw07.task1.repositories.GroupRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@Service
@Slf4j
public class GroupServiceImpl implements GroupService {
    
    GroupRepository groupRepository;
    
    public Group checkFound(Integer id, Optional<Group> group) {
        return group.orElseThrow(() -> {
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
        Group currGroup = checkFound(id, groupRepository.findById(id));
        newGroup = GroupMapper.getForUpdate(id, currGroup, newGroup);
        
        return groupRepository.save(newGroup);
    }
    
    public void deleteById(Integer id) {
        checkFound(id, groupRepository.findById(id));
        groupRepository.deleteById(id);
    }
    
    public List<Group> findAll() {
        return (List<Group>) groupRepository.findAll();
    }
    
    public Group findById(Integer id) {
        return checkFound(id, groupRepository.findById(id));
    }
}
