package hw10.task1.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hw10.task1.WatchmanExtension;

import hw10.task1.controllers.StudentController;
import hw10.task1.dto.StudentDto;
import hw10.task1.dto.StudentEditDto;
import hw10.task1.exceptions.StudentNotFoundException;
import hw10.task1.messages.Messages;
import hw10.task1.services.StudentService;

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
@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    
    private static final int ID = 1;
    private static final int STUDENT_DTO_SIZE = 3;
    private static final String STUDENT_NOT_FOUND = String.format(Messages.STUDENT_NOT_FOUND.getOutMessage(), ID);
    
    private static final StudentDto STUDENT_DTO = new StudentDto();
    private static final StudentEditDto STUDENT_EDIT_DTO = new StudentEditDto();
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private StudentService studentService;
    
    @InjectMocks
    ObjectMapper objectMapper;
    
    @Test
    @DisplayName("API. Student. POST '/students'. Valid. Created student")
    void createReturnValidResponse() throws Exception {
        when(studentService.create(STUDENT_EDIT_DTO)).thenReturn(STUDENT_DTO);
        
        mockMvc.perform(
                post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(STUDENT_EDIT_DTO))
                )
                .andExpectAll(
                        status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.size()").value(STUDENT_DTO_SIZE)
                );
    }
    
    @Test
    @DisplayName("API. Student. PUT '/students/{id}'. Valid. Updated student")
    void updateReturnValidResponse() throws Exception {
        when(studentService.update(ID, STUDENT_EDIT_DTO)).thenReturn(STUDENT_DTO);
        
        mockMvc.perform(
                put("/students/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(STUDENT_EDIT_DTO))
                )
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.size()").value(STUDENT_DTO_SIZE)
                );
    }
    
    @Test
    @DisplayName("API. Student. PUT '/students/{id}'. Exception. Unknown student by ID")
    void updateReturnException() throws Exception {
        when(studentService.update(ID, STUDENT_EDIT_DTO)).thenThrow(new StudentNotFoundException(STUDENT_NOT_FOUND));
    
        mockMvc.perform(
                put("/students/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(STUDENT_EDIT_DTO))
                )
                .andExpectAll(
                        status().isNotFound(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.message").value(STUDENT_NOT_FOUND)
                );
    }
    
    @Test
    @DisplayName("API. Student. DELETE '/students/{id}'. Valid. Deleted Student")
    void deleteReturnValidResponse() throws Exception {
        mockMvc.perform(delete("/students/{id}", ID))
                .andExpectAll(
                        status().isNoContent()
                );
    }
    
    @Test
    @DisplayName("API. Student. DELETE '/students/{id}'. Exception. Unknown student by ID")
    void deleteReturnException() throws Exception {
        doThrow(new StudentNotFoundException(STUDENT_NOT_FOUND)).when(studentService).deleteById(ID);
        
        mockMvc.perform(delete("/students/{id}", ID))
                .andExpectAll(
                        status().isNotFound(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.message").value(STUDENT_NOT_FOUND)
                );
    }
    
    @Test
    @DisplayName("API. Student. GET '/students'. Valid. List of all students")
    void getAllReturnValidResponse() throws Exception {
        when(studentService.findAll()).thenReturn(List.of(STUDENT_DTO));
        
        mockMvc.perform(get("/students"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON)
                );
    }
    
    @Test
    @DisplayName("API. Student. GET '/students'. Valid. Empty list")
    void getAllEmptyDataReturnValidResponse() throws Exception {
        when(studentService.findAll()).thenReturn(new ArrayList<>());
        
        mockMvc.perform(get("/students"))
                .andExpect(
                        jsonPath("$", hasSize(0))
                );
    }
    
    @Test
    @DisplayName("API. Student. GET '/students/{id}'. Valid. Certain student by ID")
    void getOneReturnValidResponse() throws Exception {
        when(studentService.findById(ID)).thenReturn(STUDENT_DTO);
        
        mockMvc.perform(get("/students/{id}", ID))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.size()").value(STUDENT_DTO_SIZE)
                );
    }
    
    @Test
    @DisplayName("API. Student. GET '/students/{id}'. Exception. Unknown student by ID")
    void getOneReturnException() throws Exception {
        when(studentService.findById(ID)).thenThrow(new StudentNotFoundException(STUDENT_NOT_FOUND));
        
        mockMvc.perform(get("/students/{id}", ID))
                .andExpectAll(
                        status().isNotFound(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.message").value(STUDENT_NOT_FOUND)
                );
    }
}
