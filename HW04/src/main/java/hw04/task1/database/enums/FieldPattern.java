package hw04.task1.database.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author YevhenKovalevskyi
 */
@RequiredArgsConstructor
public enum FieldPattern {
    EMAIL("^(.+)@(.+)$"), PASSWORD("[\\w-]+"), NAME("[a-zA-Z- ]+"), GENDER("male|female");
    
    @Getter
    private final String pattern;
}
