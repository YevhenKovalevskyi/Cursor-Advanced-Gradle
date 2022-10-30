package hw02.task3.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The Messages Enum represents text messages for use and localization(if it needed)
 *
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
public enum Messages {
    START_PROGRAM("Start of the program...", "\nStart of the program...\n\n----------"),
    END_PROGRAM("End of the program.\n", "----------\n\nEnd of the program.\n"),
    NOW_PLAYING("Now playing: {} - {}", "Now playing: %s - %s"),
    ;
    
    @Getter private final String logMessage;
    @Getter private final String outMessage;
}
