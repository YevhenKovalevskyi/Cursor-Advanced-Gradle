package hw02.task3;

import hw02.task3.services.MusicPlayerService;
import hw02.task3.messages.Messages;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * The Main Class represents homework #2 #task3
 *
 * @author YevhenKovalevskyi
 */
@Slf4j
public class Main {
    
    public static void main(String[] args) {
        log.info(Messages.START_PROGRAM.getLogMessage());
        System.out.println(Messages.START_PROGRAM.getOutMessage());
    
        @Cleanup
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        
        MusicPlayerService musicPlayer = context.getBean("musicPlayer", MusicPlayerService.class);
        musicPlayer.playClassicMusic();
        //musicPlayer.playRockMusic();
        
        log.info(Messages.END_PROGRAM.getLogMessage());
        System.out.println(Messages.END_PROGRAM.getOutMessage());
    }
}
