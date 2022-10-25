package hw02.task1;

import hw02.task1.messages.Messages;
import lombok.extern.slf4j.Slf4j;

/**
 * The Main Class represents homework #2 #task1
 *
 * @author YevhenKovalevskyi
 * @version 1.0
 */
@Slf4j
public class Main {
    
    public static void main(String[] args) {
        log.info(Messages.START_PROGRAM.getLogMessage());
        System.out.println(Messages.START_PROGRAM.getOutMessage());

        
        
        log.info(Messages.END_PROGRAM.getLogMessage());
        System.out.println(Messages.END_PROGRAM.getOutMessage());
    }
}
