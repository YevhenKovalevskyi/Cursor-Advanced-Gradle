package hw07.task1.services.impl;

import hw07.task1.dto.GroupDto;
import hw07.task1.dto.GroupEditDto;
import hw07.task1.dto.StudentDto;
import hw07.task1.entities.Group;
import hw07.task1.exceptions.GroupNotFoundException;
import hw07.task1.mappers.GroupMapper;
import hw07.task1.mappers.StudentMapper;
import hw07.task1.messages.Messages;
import hw07.task1.services.GroupService;
import hw07.task1.repositories.GroupRepository;

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
    private GroupMapper groupMapper;
    private StudentMapper studentMapper;
    
    public Group findByIdIfExists(Integer id) {
        return groupRepository.findById(id).orElseThrow(() -> {
            log.error(Messages.GROUP_NOT_FOUND.getLogMessage(), id);
            throw new GroupNotFoundException(
                    String.format(Messages.GROUP_NOT_FOUND.getOutMessage(), id)
            );
        });
    }
    
    public GroupDto create(GroupEditDto groupDto) {
        Group group = groupRepository.save(
                groupMapper.toCreateEntity(groupDto)
        );
    
        return groupMapper.toDto(group);
    }
    
    public GroupDto update(Integer id, GroupEditDto groupDto) {
        Group currentGroup = findByIdIfExists(id);
        Group updatedGroup = groupRepository.save(
                groupMapper.toUpdateEntity(currentGroup, groupDto)
        );
    
        return groupMapper.toDto(updatedGroup);
    }
    
    public void deleteById(Integer id) {
        findByIdIfExists(id);
        groupRepository.deleteById(id);
    }
    
    public List<GroupDto> findAll() {
        return ((List<Group>) groupRepository.findAll())
                .stream()
                .map(group -> groupMapper.toDto(group))
                .toList();
    }
    
    public GroupDto findById(Integer id) {
        return groupMapper.toDto(findByIdIfExists(id));
    }
    
    public List<StudentDto> findStudents(Integer id) {
        return findByIdIfExists(id).getStudents()
                .stream()
                .map(student -> studentMapper.toDto(student))
                .toList();
    }
    
    public int findStudentsCount(Integer id) {
        return findStudents(id).size();
    }
}
