package hw09.task1.services;

import hw09.task1.dto.GroupDto;
import hw09.task1.dto.StudentDto;
import hw09.task1.dto.TeacherDto;
import hw09.task1.dto.TeacherEditDto;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
public interface TeacherService {
    
    TeacherDto create(TeacherEditDto teacherToCreate);
    TeacherDto update(Integer id, TeacherEditDto teacherToUpdate);
    void deleteById(Integer id);
    List<TeacherDto> findAll();
    TeacherDto findById(Integer id);
    List<GroupDto> findGroups(Integer id);
    int findGroupsCount(Integer id);
    List<StudentDto> findStudents(Integer id);
    int findStudentsCount(Integer id);
}
