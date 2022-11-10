package hw04.task1;

import hw04.task1.messages.Messages;
import hw04.task1.services.AppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Main Class represents homework #4 #task1
 *
 * @author YevhenKovalevskyi
 * @version 1.0
 */
@Slf4j
@SpringBootApplication
public class Main implements CommandLineRunner {
    
    @Autowired
    private AppService appService;
    
    public static void main(String[] args) {
        log.info(Messages.START_PROGRAM.getLogMessage());
        System.out.println(Messages.START_PROGRAM.getOutMessage());
    
        SpringApplication.run(Main.class, args);
        
        log.info(Messages.END_PROGRAM.getLogMessage());
        System.out.println(Messages.END_PROGRAM.getOutMessage());
    }
    
    @Override
    public void run(String... args) {
        appService.startApp();
    }
}
