package hw04.task1.services;

import hw04.task1.database.enums.FieldPattern;
import hw04.task1.helpers.ScannerHelper;
import hw04.task1.messages.Messages;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author YevhenKovalevskyi
 */
@Slf4j
public class ConsoleService {
    
    /**
     * The method takes, validates and returns string data from the console
     *
     * @param in Scanner instance
     * @param field String field name
     * @return string console data
     */
    public static String getConsoleAction(Scanner in, String field) {
        boolean isCorrect;
        String string, message;
        
        do {
            System.out.printf(Messages.CHOOSE_ACTION.getOutMessage(), field);
            string = in.nextLine();
            isCorrect = string.matches("[\\w]+");
            
            ScannerHelper.checkForExitScannerString(in, string);
            
            message = isCorrect ? Messages.CONSOLE_OK.getOutMessage() : Messages.CONSOLE_NOK.getOutMessage();
            
            if (!isCorrect) {
                System.out.printf("-> %s\n", message);
            }
    
            log.info(Messages.GOT_FROM_CONSOLE.getLogMessage(), field, string, message);
        } while (!isCorrect);
        
        return string;
    }
    
    public static String getConsoleString(Scanner in, String field, FieldPattern pattern) {
        boolean isCorrect;
        String string, message;
        
        do {
            System.out.printf(Messages.ENTER_VALUE.getOutMessage(), field);
            string = in.nextLine();
            isCorrect = string.matches(pattern.getPattern());
            
            ScannerHelper.checkForExitScannerString(in, string);
            
            message = isCorrect ? Messages.CONSOLE_OK.getOutMessage() : Messages.CONSOLE_NOK.getOutMessage();
            
            if (!isCorrect) {
                System.out.printf("-> %s\n", message);
            }
            
            log.info(Messages.GOT_FROM_CONSOLE.getLogMessage(), field, string, message);
        } while (!isCorrect);
        
        return string;
    }
    
    /**
     * The method takes, validates and returns number data from the console
     *
     * @param in Scanner instance
     * @param field String field name
     * @return integer console data
     */
    public static int getConsoleNumber(Scanner in, String field) {
        boolean isCorrect;
        int number = 0;
        String message;
        
        do {
            System.out.printf(Messages.ENTER_VALUE.getOutMessage(), field);
            isCorrect = in.hasNextInt();
            
            if (isCorrect) {
                number = in.nextInt();
                message = Messages.CONSOLE_OK.getOutMessage();
            } else {
                ScannerHelper.checkForExitScannerNext(in);
                message = Messages.CONSOLE_NOK.getOutMessage();
                System.out.printf("-> %s\n", message);
                in.nextLine();
            }
            
            log.info(Messages.GOT_FROM_CONSOLE.getLogMessage(), field, number, message);
        } while (!isCorrect);
        
        return number;
    }
}
