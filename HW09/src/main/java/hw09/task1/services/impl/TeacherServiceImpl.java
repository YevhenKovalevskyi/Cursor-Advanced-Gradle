package hw09.task1.services.impl;

import hw09.task1.dto.GroupDto;
import hw09.task1.dto.StudentDto;
import hw09.task1.dto.TeacherDto;
import hw09.task1.dto.TeacherEditDto;
import hw09.task1.entities.Group;
import hw09.task1.entities.Student;
import hw09.task1.entities.Teacher;
import hw09.task1.exceptions.TeacherNotFoundException;
import hw09.task1.mappers.GroupMapper;
import hw09.task1.mappers.StudentMapper;
import hw09.task1.mappers.TeacherMapper;
import hw09.task1.messages.Messages;
import hw09.task1.services.TeacherService;
import hw09.task1.repositories.TeacherRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {
    
    private TeacherRepository teacherRepository;
    private TeacherMapper teacherMapper;
    private GroupMapper groupMapper;
    private StudentMapper studentMapper;
    
    public Teacher findByIdIfExists(Integer id) {
        return teacherRepository.findById(id).orElseThrow(() -> {
            log.error(Messages.TEACHER_NOT_FOUND.getLogMessage(), id);
            throw new TeacherNotFoundException(
                    String.format(Messages.TEACHER_NOT_FOUND.getOutMessage(), id)
            );
        });
    }
    
    public TeacherDto create(TeacherEditDto teacherToCreate) {
        Teacher teacherCreated = teacherRepository.save(
                teacherMapper.toCreateEntity(teacherToCreate)
        );
    
        return teacherMapper.toDto(teacherCreated);
    }
    
    public TeacherDto update(Integer id, TeacherEditDto teacherToUpdate) {
        Teacher teacherCurrent = findByIdIfExists(id);
        Teacher teacherUpdated = teacherRepository.save(
                teacherMapper.toUpdateEntity(teacherCurrent, teacherToUpdate)
        );
    
        return teacherMapper.toDto(teacherUpdated);
    }
    
    public void deleteById(Integer id) {
        findByIdIfExists(id);
        teacherRepository.deleteById(id);
    }
    
    public List<TeacherDto> findAll() {
        return ((List<Teacher>) teacherRepository.findAll())
                .stream()
                .map(teacher -> teacherMapper.toDto(teacher))
                .toList();
    }
    
    public TeacherDto findById(Integer id) {
        return teacherMapper.toDto(findByIdIfExists(id));
    }
    
    public List<GroupDto> findGroups(Integer id) {
        return findByIdIfExists(id).getGroups()
                .stream()
                .map(group -> groupMapper.toDto(group))
                .toList();
    }
    
    public int findGroupsCount(Integer id) {
        return findGroups(id).size();
    }
    
    public List<StudentDto> findStudents(Integer id) {
        List<Group> groupsByTeacher = findByIdIfExists(id).getGroups();
        List<Student> studentsByTeacher = new ArrayList<>();
        
        if (!groupsByTeacher.isEmpty()) {
            for (Group group: groupsByTeacher) {
                studentsByTeacher.addAll(group.getStudents());
            }
        }
    
        return studentsByTeacher
                .stream()
                .map(student -> studentMapper.toDto(student))
                .toList();
    }
    
    public int findStudentsCount(Integer id) {
        return findStudents(id).size();
    }
}
