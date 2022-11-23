package hw07.task1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author YevhenKovalevskyi
 */
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    
    private static final String TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    
    @ExceptionHandler(TeacherNotFoundException.class)
    public ResponseEntity<Object> handleTeacherNotFoundException(
            TeacherNotFoundException ex, HttpServletRequest request
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", "error");
        body.put("code", HttpStatus.NOT_FOUND.value());
        body.put("timestamp", TIMESTAMP);
        body.put("message", ex.getMessage());
        
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<Object> handleGroupNotFoundException(
            GroupNotFoundException ex, HttpServletRequest request
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", "error");
        body.put("code", HttpStatus.NOT_FOUND.value());
        body.put("timestamp", TIMESTAMP);
        body.put("message", ex.getMessage());
        
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Object> handleStudentNotFoundException(
            StudentNotFoundException ex, HttpServletRequest request
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", "error");
        body.put("code", HttpStatus.NOT_FOUND.value());
        body.put("timestamp", TIMESTAMP);
        body.put("message", ex.getMessage());
        
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleDataNotFoundException(
            DataNotFoundException ex, HttpServletRequest request
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", "error");
        body.put("code", HttpStatus.NOT_FOUND.value());
        body.put("timestamp", TIMESTAMP);
        body.put("message", ex.getMessage());
        
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
