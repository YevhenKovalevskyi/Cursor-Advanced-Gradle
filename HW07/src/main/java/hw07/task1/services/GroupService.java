package hw07.task1.services;

import hw07.task1.dto.GroupDto;
import hw07.task1.dto.GroupEditDto;
import hw07.task1.dto.StudentDto;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
public interface GroupService {
    
    GroupDto create(GroupEditDto groupDto);
    GroupDto update(Integer id, GroupEditDto groupDto);
    void deleteById(Integer id);
    List<GroupDto> findAll();
    GroupDto findById(Integer id);
    List<StudentDto> findStudents(Integer id);
    int findStudentsCount(Integer id);
}
