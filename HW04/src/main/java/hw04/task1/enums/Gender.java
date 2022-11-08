package hw04.task1.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author YevhenKovalevskyi
 */
@RequiredArgsConstructor
public enum Gender {
    MALE("male"), FEMALE("female");
    
    @Getter
    private final String gender;
}
