package hw04.task1;

import com.google.common.collect.ImmutableMap;
import hw04.task1.entities.User;
import hw04.task1.enums.Gender;
import hw04.task1.messages.Messages;
import hw04.task1.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    private UserService userService;
    
    public static void main(String[] args) {
        log.info(Messages.START_PROGRAM.getLogMessage());
        System.out.println(Messages.START_PROGRAM.getOutMessage());
    
        SpringApplication.run(Main.class, args);
        
        log.info(Messages.END_PROGRAM.getLogMessage());
        System.out.println(Messages.END_PROGRAM.getOutMessage());
    }
    
    @Override
    public void run(String... args) {
        /*Map<String, String> user1Params = ImmutableMap.of(
                "email", "kcocklie0@desdev.cn", "password", "EsEYv0kix", "firstName", "Kaila", "lastName", "Cocklie",
                "gender", Gender.FEMALE.getGender(), "age", "32"
        );
        Map<String, String> user2Params = ImmutableMap.of(
                "email", "rtreversh2@zimbio.com", "password", "3CzwrESph3", "firstName", "Rufus", "lastName", "Treversh",
                "gender", Gender.MALE.getGender(), "age", "32"
        );
        
        User user1 = User.build(user1Params, 11);
        User user2 = User.build(user2Params);
        
        userService.saveUser(user1);
        userService.createUser(user2);*/
    
        //List<User> users = userService.selectAll();
        Optional<User> user = userService.selectOne(13);
        
        if (user.isPresent()) {
            System.out.println("---");
            System.out.println(user.get());
            System.out.println("---");
        } else {
            System.out.println("---\nNot found!\n---");
        }
    }
}
