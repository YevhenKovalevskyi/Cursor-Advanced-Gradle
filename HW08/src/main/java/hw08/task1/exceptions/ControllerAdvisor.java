package hw08.task1.exceptions;

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
    
    @ExceptionHandler(ShopNotFoundException.class)
    public ResponseEntity<Object> handleShopNotFoundException(
            ShopNotFoundException ex, HttpServletRequest request
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", "error");
        body.put("code", HttpStatus.NOT_FOUND.value());
        body.put("timestamp", TIMESTAMP);
        body.put("message", ex.getMessage());
        
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> handleEmployeeNotFoundException(
            EmployeeNotFoundException ex, HttpServletRequest request
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", "error");
        body.put("code", HttpStatus.NOT_FOUND.value());
        body.put("timestamp", TIMESTAMP);
        body.put("message", ex.getMessage());
        
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(RequestAlreadyTakenException.class)
    public ResponseEntity<Object> handleRequestAlreadyTakenException(
            RequestAlreadyTakenException ex, HttpServletRequest request
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", "error");
        body.put("code", HttpStatus.BAD_REQUEST.value());
        body.put("timestamp", TIMESTAMP);
        body.put("message", ex.getMessage());
        
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(PrintWriterException.class)
    public ResponseEntity<Object> handlePrintWriterException(
            PrintWriterException ex, HttpServletRequest request
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", "error");
        body.put("code", HttpStatus.BAD_REQUEST.value());
        body.put("timestamp", TIMESTAMP);
        body.put("message", ex.getMessage());
        
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
