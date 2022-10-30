package hw02.task2;

import hw02.task2.services.MusicPlayerService;
import hw02.task2.messages.Messages;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The Main Class represents homework #2 #task2
 *
 * @author YevhenKovalevskyi
 */
@Slf4j
public class Main {
    
    public static void main(String[] args) {
        log.info(Messages.START_PROGRAM.getLogMessage());
        System.out.println(Messages.START_PROGRAM.getOutMessage());
    
        @Cleanup
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("task2/applicationContext.xml");
    
        MusicPlayerService musicPlayer = context.getBean("musicPlayer", MusicPlayerService.class);
        musicPlayer.playClassicMusic();
        //musicPlayer.playRockMusic();
        
        log.info(Messages.END_PROGRAM.getLogMessage());
        System.out.println(Messages.END_PROGRAM.getOutMessage());
    }
}
