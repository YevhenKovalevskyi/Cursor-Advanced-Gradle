package hw05.task1.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The Messages Enum represents text messages for use and localization(if it needed)
 */
@AllArgsConstructor
public enum Messages {
    SHOP_NOT_FOUND("Shop ID[{}] not found!", "Shop ID[%s] not found!"),
    EMPLOYEE_NOT_FOUND("Employee ID[{}] not found!", "Employee ID[%s] not found!"),

    ;
    
    @Getter private final String logMessage;
    @Getter private final String outMessage;
}
