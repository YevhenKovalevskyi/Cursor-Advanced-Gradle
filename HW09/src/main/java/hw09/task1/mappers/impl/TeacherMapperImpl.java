package hw09.task1.mappers.impl;

import hw09.task1.dto.TeacherDto;
import hw09.task1.dto.TeacherEditDto;
import hw09.task1.entities.Teacher;
import hw09.task1.mappers.TeacherMapper;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author YevhenKovalevskyi
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Component
public class TeacherMapperImpl implements TeacherMapper {
    
    private ModelMapper modelMapper;
    
    public TeacherDto toDto(Teacher teacher) {
        return modelMapper.map(teacher, TeacherDto.class);
    }
    
    public Teacher toCreateEntity(TeacherEditDto teacherToCreate) {
        Teacher teacher = modelMapper.map(teacherToCreate, Teacher.class);
        teacher.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        teacher.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        
        return teacher;
    }
    
    public Teacher toUpdateEntity(Teacher teacherCurrent, TeacherEditDto teacherToUpdate) {
        Teacher teacher = modelMapper.map(teacherToUpdate, Teacher.class);
        teacher.setId(teacherCurrent.getId());
        teacher.setCreatedAt(teacherCurrent.getCreatedAt());
        teacher.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        
        return teacher;
    }
}
