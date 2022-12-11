package hw07.task1.services;

import hw07.task1.dto.GroupDto;
import hw07.task1.dto.StudentDto;
import hw07.task1.dto.TeacherDto;
import hw07.task1.dto.TeacherEditDto;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
public interface TeacherService {
    
    TeacherDto create(TeacherEditDto teacherDto);
    TeacherDto update(Integer id, TeacherEditDto teacherDto);
    void deleteById(Integer id);
    List<TeacherDto> findAll();
    TeacherDto findById(Integer id);
    List<GroupDto> findGroups(Integer id);
    int findGroupsCount(Integer id);
    List<StudentDto> findStudents(Integer id);
    int findStudentsCount(Integer id);
}
