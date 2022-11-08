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
    
    USER_DELETED("Deleting user result: user deleted!", " : Deleting user result: user deleted!"),
    USER_NOT_DELETED("Deleting user result: user not found!", " : Deleting user result: user not found!"),
    USER_UPDATED("Updating user result: user updated!", " : Updating user result: user updated!"),
    USER_INSERTED("Inserting user result: user inserted!", " : Inserting user result: user inserted!"),
    ;
    
    @Getter private final String logMessage;
    @Getter private final String outMessage;
}
