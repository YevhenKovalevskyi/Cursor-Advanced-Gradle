package hw10.task1.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import hw10.task1.WatchmanExtension;
import hw10.task1.controllers.TeacherController;
import hw10.task1.dto.GroupDto;
import hw10.task1.dto.StudentDto;
import hw10.task1.dto.TeacherDto;
import hw10.task1.dto.TeacherEditDto;
import hw10.task1.exceptions.TeacherNotFoundException;
import hw10.task1.messages.Messages;
import hw10.task1.services.TeacherService;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author YevhenKovalevskyi
 */
@Slf4j
@ExtendWith(WatchmanExtension.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(TeacherController.class)
public class TeacherControllerTest {
    
    private static final int ID = 1;
    private static final int TEACHER_DTO_SIZE = 2;
    private static final String TEACHER_NOT_FOUND = String.format(Messages.TEACHER_NOT_FOUND.getOutMessage(), ID);
    
    private static final TeacherDto TEACHER_DTO = new TeacherDto();
    private static final TeacherEditDto TEACHER_EDIT_DTO = new TeacherEditDto();
    private static final GroupDto GROUP_DTO = new GroupDto();
    private static final StudentDto STUDENT_DTO = new StudentDto();
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private TeacherService teacherService;
    
    @InjectMocks
    ObjectMapper objectMapper;
    
    @Test
    @DisplayName("API. Teacher. POST '/teachers'. Valid. Created teacher")
    void createReturnValidResponse() throws Exception {
        when(teacherService.create(TEACHER_EDIT_DTO)).thenReturn(TEACHER_DTO);
        
        mockMvc.perform(
                post("/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(TEACHER_EDIT_DTO))
                )
                .andExpectAll(
                        status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.size()").value(TEACHER_DTO_SIZE)
                );
    }
    
    @Test
    @DisplayName("API. Teacher. PUT '/teachers/{id}'. Valid. Updated teacher")
    void updateReturnValidResponse() throws Exception {
        when(teacherService.update(ID, TEACHER_EDIT_DTO)).thenReturn(TEACHER_DTO);
        
        mockMvc.perform(
                put("/teachers/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(TEACHER_EDIT_DTO))
                )
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.size()").value(TEACHER_DTO_SIZE)
                );
    }
    
    @Test
    @DisplayName("API. Teacher. PUT '/teachers/{id}'. Exception. Unknown teacher by ID")
    void updateReturnException() throws Exception {
        when(teacherService.update(ID, TEACHER_EDIT_DTO)).thenThrow(new TeacherNotFoundException(TEACHER_NOT_FOUND));
        
        mockMvc.perform(
                put("/teachers/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(TEACHER_EDIT_DTO))
                )
                .andExpectAll(
                        status().isNotFound(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.message").value(TEACHER_NOT_FOUND)
                );
    }
    
    @Test
    @DisplayName("API. Teacher. DELETE '/teachers/{id}'. Valid. Deleted teacher")
    void deleteReturnValidResponse() throws Exception {
        mockMvc.perform(delete("/teachers/{id}", ID))
                .andExpectAll(
                        status().isNoContent()
                );
    }
    
    @Test
    @DisplayName("API. Teacher. DELETE '/teachers/{id}'. Exception. Unknown teacher by ID")
    void deleteReturnException() throws Exception {
        doThrow(new TeacherNotFoundException(TEACHER_NOT_FOUND)).when(teacherService).deleteById(ID);
        
        mockMvc.perform(delete("/teachers/{id}", ID))
                .andExpectAll(
                        status().isNotFound(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.message").value(TEACHER_NOT_FOUND)
                );
    }
    
    @Test
    @DisplayName("API. Teacher. GET '/teachers'. Valid. List of all teachers")
    void getAllReturnValidResponse() throws Exception {
        when(teacherService.findAll()).thenReturn(List.of(TEACHER_DTO));
        
        mockMvc.perform(get("/teachers"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON)
                );
    }
    
    @Test
    @DisplayName("API. Teacher. GET '/teachers'. Valid. Empty list")
    void getAllEmptyDataReturnValidResponse() throws Exception {
        when(teacherService.findAll()).thenReturn(new ArrayList<>());
        
        mockMvc.perform(get("/teachers"))
                .andExpect(
                        jsonPath("$", hasSize(0))
                );
    }
    
    @Test
    @DisplayName("API. Teacher. GET '/teachers/{id}'. Valid. Certain teacher by ID")
    void getOneReturnValidResponse() throws Exception {
        when(teacherService.findById(ID)).thenReturn(TEACHER_DTO);
        
        mockMvc.perform(get("/teachers/{id}", ID))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.size()").value(TEACHER_DTO_SIZE)
                );
    }
    
    @Test
    @DisplayName("API. Teacher. GET '/teachers/{id}'. Exception. Unknown teacher by ID")
    void getOneReturnException() throws Exception {
        when(teacherService.findById(ID)).thenThrow(new TeacherNotFoundException(TEACHER_NOT_FOUND));
        
        mockMvc.perform(get("/teachers/{id}", ID))
                .andExpectAll(
                        status().isNotFound(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.message").value(TEACHER_NOT_FOUND)
                );
    }
    
    @Test
    @DisplayName("API. Teacher. GET '/teachers/{id}/groups'. Valid. List of all groups by teacher ID")
    public void getGroupsReturnValidResponse() throws Exception {
        when(teacherService.findGroups(ID)).thenReturn(List.of(GROUP_DTO));
    
        mockMvc.perform(get("/teachers/{id}/groups", ID))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON)
                );
    }
    
    @Test
    @DisplayName("API. Teacher. GET '/teachers/{id}/groups'. Exception. Unknown teacher by ID")
    public void getGroupsReturnException() throws Exception {
        when(teacherService.findGroups(ID)).thenThrow(new TeacherNotFoundException(TEACHER_NOT_FOUND));
    
        mockMvc.perform(get("/teachers/{id}/groups", ID))
                .andExpectAll(
                        status().isNotFound(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.message").value(TEACHER_NOT_FOUND)
                );
    }
    
    @Test
    @DisplayName("API. Teacher. GET '/teachers/{id}/groups/count'. Valid. Count of all groups by teacher ID")
    public void getGroupsCountReturnValidResponse() throws Exception {
        int count = anyInt();
        
        when(teacherService.findGroupsCount(ID)).thenReturn(count);
    
        mockMvc.perform(get("/teachers/{id}/groups/count", ID))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(String.valueOf(count))
                );
    }
    
    @Test
    @DisplayName("API. Teacher. GET '/teachers/{id}/groups/count'. Exception. Unknown teacher by ID")
    public void getGroupsCountReturnException() throws Exception {
        when(teacherService.findGroupsCount(ID)).thenThrow(new TeacherNotFoundException(TEACHER_NOT_FOUND));
    
        mockMvc.perform(get("/teachers/{id}/groups/count", ID))
                .andExpectAll(
                        status().isNotFound(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.message").value(TEACHER_NOT_FOUND)
                );
    }
    
    @Test
    @DisplayName("API. Teacher. GET '/teachers/{id}/students'. Valid. List of all students by teacher ID")
    public void getStudentsReturnValidResponse() throws Exception {
        when(teacherService.findStudents(ID)).thenReturn(List.of(STUDENT_DTO));
    
        mockMvc.perform(get("/teachers/{id}/students", ID))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON)
                );
    }
    
    @Test
    @DisplayName("API. Teacher. GET '/teachers/{id}/students'. Exception. Unknown teacher by ID")
    public void getStudentsReturnException() throws Exception {
        when(teacherService.findStudents(ID)).thenThrow(new TeacherNotFoundException(TEACHER_NOT_FOUND));
    
        mockMvc.perform(get("/teachers/{id}/students", ID))
                .andExpectAll(
                        status().isNotFound(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.message").value(TEACHER_NOT_FOUND)
                );
    }
    
    @Test
    @DisplayName("API. Teacher. GET '/teachers/{id}/students/count'. Valid. Count of all students by teacher ID")
    public void getStudentsCountReturnValidResponse() throws Exception {
        int count = anyInt();
        
        when(teacherService.findStudentsCount(ID)).thenReturn(count);
    
        mockMvc.perform(get("/teachers/{id}/students/count", ID))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(String.valueOf(count))
                );
    }
    
    @Test
    @DisplayName("API. Teacher. GET '/teachers/{id}/students/count'. Exception. Unknown teacher by ID")
    public void getStudentsCountReturnException() throws Exception {
        when(teacherService.findStudentsCount(ID)).thenThrow(new TeacherNotFoundException(TEACHER_NOT_FOUND));
    
        mockMvc.perform(get("/teachers/{id}/students/count", ID))
                .andExpectAll(
                        status().isNotFound(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.message").value(TEACHER_NOT_FOUND)
                );
    }
}
