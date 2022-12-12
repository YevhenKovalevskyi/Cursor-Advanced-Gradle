package hw09.task1.services;

import hw09.task1.dto.GroupDto;
import hw09.task1.dto.GroupEditDto;
import hw09.task1.dto.StudentDto;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
public interface GroupService {
    
    GroupDto create(GroupEditDto groupToCreate);
    GroupDto update(Integer id, GroupEditDto groupToUpdate);
    void deleteById(Integer id);
    List<GroupDto> findAll();
    GroupDto findById(Integer id);
    List<StudentDto> findStudents(Integer id);
    int findStudentsCount(Integer id);
}
