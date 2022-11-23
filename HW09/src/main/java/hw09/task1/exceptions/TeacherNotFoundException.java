package hw09.task1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author YevhenKovalevskyi
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeacherNotFoundException extends RuntimeException {
    
    public TeacherNotFoundException(String message) {
        super(message);
    }
}
