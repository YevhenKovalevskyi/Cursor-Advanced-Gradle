package hw04.task1.services;

import hw04.task1.database.entities.User;
import hw04.task1.database.enums.FieldPattern;
import hw04.task1.database.services.UserService;
import hw04.task1.helpers.PrintHelper;
import hw04.task1.messages.Messages;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author YevhenKovalevskyi
 */
@Slf4j
@Service
public class AppService {

    @Autowired
    private UserService userService;
    
    public void startApp() {
        PrintHelper.printGreeting();
    
        @Cleanup Scanner in = new Scanner(System.in);
    
        while (true) {
            String menuAction = ConsoleService.getConsoleAction(in, "action");

            switch (menuAction) {
                case "1" -> {
                    createUser(in);
                    in.nextLine();
                }
                case "2" -> {
                    updateUser(in);
                    in.nextLine();
                }
                case "3" -> {
                    deleteUser(in);
                    in.nextLine();
                }
                case "4" -> {
                    selectUserById(in);
                    in.nextLine();
                }
                case "5" -> selectUsersAll();
                case "menu" -> PrintHelper.printMenu();
                default -> {
                    System.out.println(Messages.INCORRECT_CHOICE.getOutMessage());
                    PrintHelper.printGreeting();
                }
            }
        }
    }
    
    private void createUser(Scanner in) {
        Map<String, String> userParams = new HashMap<>();
    
        userParams.put("email", ConsoleService.getConsoleString(in, "email", FieldPattern.EMAIL));
        userParams.put("password", ConsoleService.getConsoleString(in, "password", FieldPattern.PASSWORD));
        userParams.put("firstName", ConsoleService.getConsoleString(in, "firstName", FieldPattern.NAME));
        userParams.put("lastName", ConsoleService.getConsoleString(in, "lastName", FieldPattern.NAME));
        userParams.put("gender", ConsoleService.getConsoleString(in, "gender", FieldPattern.GENDER));
        userParams.put("age", String.valueOf(ConsoleService.getConsoleNumber(in, "age")));
    
        User user = userService.save(User.build(userParams));
    
        System.out.printf(Messages.USER_CREATED.getOutMessage(), user.getId());
        log.info(Messages.USER_CREATED.getLogMessage(), user.getId());
    
        PrintHelper.printUser(user);
    }
    
    private void updateUser(Scanner in) {
        int userId = ConsoleService.getConsoleNumber(in, "user id");
        User user = userService.findById(userId);

        if (user != null) {
            PrintHelper.printUser(user);
    
            in.nextLine();
            
            user.setEmail(ConsoleService.getConsoleString(in, "email", FieldPattern.EMAIL));
            user.setPassword(ConsoleService.getConsoleString(in, "password", FieldPattern.PASSWORD));
            user.setFirstName(ConsoleService.getConsoleString(in, "firstName", FieldPattern.NAME));
            user.setLastName(ConsoleService.getConsoleString(in, "lastName", FieldPattern.NAME));
            user.setGender(ConsoleService.getConsoleString(in, "gender", FieldPattern.GENDER));
            user.setAge(ConsoleService.getConsoleNumber(in, "age"));

            user = userService.save(user);
    
            System.out.printf(Messages.USER_SAVED.getOutMessage(), userId);
            log.info(Messages.USER_SAVED.getLogMessage(), userId);
    
            PrintHelper.printUser(user);
        } else {
            System.out.printf(Messages.USER_NOT_FOUND.getOutMessage(), userId);
            log.info(Messages.USER_NOT_FOUND.getLogMessage(), userId);
        }
    }
    
    private void deleteUser(Scanner in) {
        int userId = ConsoleService.getConsoleNumber(in, "user id");
        User user = userService.findById(userId);
    
        if (user != null) {
            userService.delete(user);
            System.out.printf(Messages.USER_DELETED.getOutMessage(), userId);
            log.info(Messages.USER_DELETED.getLogMessage(), userId);
        } else {
            System.out.printf(Messages.USER_NOT_FOUND.getOutMessage(), userId);
            log.info(Messages.USER_NOT_FOUND.getLogMessage(), userId);
        }
    }
    
    private void selectUserById(Scanner in) {
        int userId = ConsoleService.getConsoleNumber(in, "user id");
        User user = userService.findById(userId);
        
        if (user != null) {
            PrintHelper.printUser(user);
        } else {
            System.out.printf(Messages.USER_NOT_FOUND.getOutMessage(), userId);
            log.info(Messages.USER_NOT_FOUND.getLogMessage(), userId);
        }
    }
    
    private void selectUsersAll() {
        List<User> users = userService.findAll();

        if (users.isEmpty()) {
            System.out.println(Messages.USERS_NOT_FOUND.getOutMessage());
            log.info(Messages.USERS_NOT_FOUND.getLogMessage());
        } else {
            PrintHelper.printUsersList(users);
        }
    }
}
