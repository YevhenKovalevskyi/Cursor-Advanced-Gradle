package hw07.task1.services.impl;

import hw07.task1.entities.Group;
import hw07.task1.entities.Student;
import hw07.task1.entities.Teacher;
import hw07.task1.exceptions.TeacherNotFoundException;
import hw07.task1.mappers.TeacherMapper;
import hw07.task1.messages.Messages;
import hw07.task1.services.TeacherService;
import hw07.task1.repositories.TeacherRepository;

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
    
    public Teacher findByIdIfExists(Integer id) {
        return teacherRepository.findById(id).orElseThrow(() -> {
            log.error(Messages.TEACHER_NOT_FOUND.getLogMessage(), id);
            throw new TeacherNotFoundException(
                    String.format(Messages.TEACHER_NOT_FOUND.getOutMessage(), id)
            );
        });
    }
    
    public Teacher create(Teacher newTeacher) {
        return teacherRepository.save(Teacher.build(newTeacher));
    }
    
    public Teacher update(Integer id, Teacher newTeacher) {
        Teacher currTeacher = findByIdIfExists(id);
        newTeacher = TeacherMapper.getForUpdate(id, currTeacher, newTeacher);
        
        return teacherRepository.save(newTeacher);
    }
    
    public void deleteById(Integer id) {
        findByIdIfExists(id);
        teacherRepository.deleteById(id);
    }
    
    public List<Teacher> findAll() {
        return (List<Teacher>) teacherRepository.findAll();
    }
    
    public Teacher findById(Integer id) {
        return findByIdIfExists(id);
    }
    
    public List<Group> findGroups(Integer id) {
        return findByIdIfExists(id).getGroups();
    }
    
    public int findGroupsCount(Integer id) {
        return findGroups(id).size();
    }
    
    public List<Student> findStudents(Integer id) {
        List<Group> groups = findByIdIfExists(id).getGroups();
        List<Student> students = new ArrayList<>();
        
        if (!groups.isEmpty()) {
            for (Group group: groups) {
                students.addAll(group.getStudents());
            }
        }
    
        return students;
    }
    
    public int findStudentsCount(Integer id) {
        return findStudents(id).size();
    }
}
