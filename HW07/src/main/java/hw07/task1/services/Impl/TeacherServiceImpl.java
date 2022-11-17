package hw07.task1.services.Impl;

import hw07.task1.entities.Teacher;
import hw07.task1.exceptions.TeacherNotFoundException;
import hw07.task1.mappers.TeacherMapper;
import hw07.task1.messages.Messages;
import hw07.task1.services.TeacherService;
import hw07.task1.repositories.TeacherRepository;

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
public class TeacherServiceImpl implements TeacherService {
    
    TeacherRepository teacherRepository;
    
    public Teacher checkFound(Integer id, Optional<Teacher> teacher) {
        return teacher.orElseThrow(() -> {
            log.error(Messages.TEACHER_NOT_FOUND.getLogMessage(), id);
            throw new TeacherNotFoundException(
                    String.format(Messages.TEACHER_NOT_FOUND.getOutMessage(), id)
            );
        });
    }
    
    public Teacher save(Teacher newTeacher) {
        return teacherRepository.save(Teacher.build(newTeacher));
    }
    
    public Teacher save(Integer id, Teacher newTeacher) {
        Teacher currTeacher = checkFound(id, teacherRepository.findById(id));
        newTeacher = TeacherMapper.getForUpdate(id, currTeacher, newTeacher);
        
        return teacherRepository.save(newTeacher);
    }
    
    public void deleteById(Integer id) {
        checkFound(id, teacherRepository.findById(id));
        teacherRepository.deleteById(id);
    }
    
    public List<Teacher> findAll() {
        return (List<Teacher>) teacherRepository.findAll();
    }
    
    public Teacher findById(Integer id) {
        return checkFound(id, teacherRepository.findById(id));
    }
}
