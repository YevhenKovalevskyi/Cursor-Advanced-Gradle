package hw05.task1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author YevhenKovalevskyi
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShopNotFoundException extends RuntimeException {
    
    public ShopNotFoundException(String message) {
        super(message);
    }
}
