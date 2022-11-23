package hw08.task1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author YevhenKovalevskyi
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PrintWriterException extends RuntimeException {
    
    public PrintWriterException(String message) {
        super(message);
    }
}
