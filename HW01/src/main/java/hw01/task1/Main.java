package hw01.task1;

import hw01.task1.database.entities.User;
import hw01.task1.messages.Messages;
import hw01.task1.services.UserService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * The Main Class represents homework #1 #task1
 *
 * @author YevhenKovalevskyi
 * @version 1.0
 */
@Slf4j
public class Main {
    
    public static void main(String[] args) {
        log.info(Messages.START_PROGRAM.getLogMessage());
        System.out.println(Messages.START_PROGRAM.getOutMessage());
        
        //UserService userService = new UserService("mysql"); /* works */
        UserService userService = new UserService("pgsql"); /* works */
        
        // 1. Users with age less than 18
        List<User> mysqlUsersTask1 = userService.getUsersFilteredByAgeLess(18, true);
        printUsersList(mysqlUsersTask1, Messages.GETTING_USERS_AGE_LESS.getOutMessage());

        System.out.println("----------");
        
        // 2. Users with name ends in "o"
        List<User> mysqlUsersTask2 = userService.getUsersFilteredByNameEnds("o");
        printUsersList(mysqlUsersTask2, Messages.GETTING_USERS_NAME_ENDS.getOutMessage());
    
        System.out.println("----------");
        
        // 3. Users with age between 18 and 60
        List<User> mysqlUsersTask3 = userService.getUsersFilteredByAgeBetween(18, 60);
        printUsersList(mysqlUsersTask3, Messages.GETTING_USERS_AGE_BETWEEN.getOutMessage());
    
        System.out.println("----------");

        // 4. The count of Users with name contains "a"
        int mysqlUsersTask4 = userService.getUsersCountFilteredByNameContains("a");
        printUsersCount(mysqlUsersTask4, Messages.GETTING_USERS_COUNT_NAME_CONT.getOutMessage());
    
        System.out.println("----------");
    
        // 5. The count of Users with age more than 18
        int mysqlUsersTask5 = userService.getUsersCountFilteredByAgeMore(18, false);
        printUsersCount(mysqlUsersTask5, Messages.GETTING_USERS_COUNT_ADULT.getOutMessage());

        log.info(Messages.END_PROGRAM.getLogMessage());
        System.out.println(Messages.END_PROGRAM.getOutMessage());
    }
    
    private static void printUsersList(List<User> users, String title) {
        System.out.printf("%s:\n", title);
        
        if (users.isEmpty()) {
            System.out.println(Messages.USERS_LIST_EMPTY.getOutMessage());
        } else {
            users.forEach(System.out::println);
        }
    }
    
    private static void printUsersCount(int users, String title) {
        System.out.printf("%s:\n", title);
        System.out.printf(Messages.USERS_COUNT.getOutMessage(), users);
    }
}
