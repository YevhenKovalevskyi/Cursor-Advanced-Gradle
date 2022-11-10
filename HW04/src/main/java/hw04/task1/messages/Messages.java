package hw04.task1.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The Messages Enum represents text messages for use and localization(if it needed)
 */
@AllArgsConstructor
public enum Messages {
    START_PROGRAM("Start of the program...", "\nStart of the program...\n\n----------"),
    END_PROGRAM("End of the program.\n", "----------\n\nEnd of the program.\n"),
    
    INCORRECT_CHOICE("Incorrect choose!", "-> Incorrect choice!"),
    USER_CREATED("User id {} created!", "User id %s created!\n"),
    USER_SAVED("User id {} saved!", "User id %s saved!\n"),
    USER_DELETED("User id {} deleted!", "User id %s deleted!\n"),
    USER_NOT_FOUND("User id {} not found!", "User id %s not found!\n"),
    USERS_NOT_FOUND("Users not found!", "Users not found!"),
    CONSOLE_OK("OK!", "OK!"),
    CONSOLE_NOK("NOT OK! Value is incorrect!", "NOT OK! Value is incorrect!"),
    GOT_FROM_CONSOLE("Got {} from console: [{}]. Validation: {}", "Got %s from console: [%s]. Validation: %s"),
    CHOOSE_ACTION("-> Choose {}: ", "-> Choose %s: "),
    ENTER_VALUE("-> Enter {}: ", "-> Enter %s: "),
    ;
    
    @Getter private final String logMessage;
    @Getter private final String outMessage;
}
