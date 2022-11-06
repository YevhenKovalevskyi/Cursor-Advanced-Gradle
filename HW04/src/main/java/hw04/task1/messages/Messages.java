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
    USERS_COUNT("Users count: %d", "Users count: %d\n"),
    USERS_LIST_EMPTY("There is no Users!", "There is no Users!"),
    GETTING_USER_BY_ID("Getting User by id", "Getting User by id"),
    GETTING_USER_BY_CRED("Getting User by login and password", "Getting User by login and password"),
    GETTING_USERS_ALL("Getting all Users", "Getting all Users"),
    GETTING_USERS_AGE_LESS("1. Getting all Users with age less than 18", "1. Getting all Users with age less than 18"),
    GETTING_USERS_NAME_ENDS("2. Getting all Users with name ending in \"o\"", "2. Getting all Users with name ending in \"o\""),
    GETTING_USERS_AGE_BETWEEN("3. Getting all Users between the ages of 18 and 60", "3. Getting all Users between the ages of 18 and 60"),
    GETTING_USERS_COUNT_NAME_CONT("4. Getting the count of all Users with name containing \"a\"", "4. Getting the count of all Users with name containing \"a\""),
    GETTING_USERS_COUNT_ADULT("5. Getting the count of all adult Users", "5. Getting the count of all adult Users"),
    ;
    
    @Getter private final String logMessage;
    @Getter private final String outMessage;
}
