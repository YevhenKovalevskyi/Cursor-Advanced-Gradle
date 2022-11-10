package hw04.task1.helpers;

import hw04.task1.database.entities.User;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
public class PrintHelper {
    
    public static final String HEADER = "╔==========================================================================" +
            "================================╗";
    public static final String FOOTER = "╚==========================================================================" +
            "================================╝";
    
    public static void printGreeting() {
        System.out.println();
        System.out.println("╔======================================╗");
        System.out.println("║   Welcome to the Users Application!  ║");
        System.out.println("╟--------------------------------------╢");
        System.out.println("║      To see MENU - type \"menu\"!      ║");
        System.out.println("╟--------------------------------------╢");
        System.out.println("║ To choose ACTION - type menu number! ║");
        System.out.println("╟--------------------------------------╢");
        System.out.println("║        To EXIT - type \"exit\"!        ║");
        System.out.println("╚======================================╝");
        System.out.println();
    }
    
    public static void printMenu() {
        System.out.println();
        System.out.println("╔====================╗");
        System.out.println("║ 1.   Create User   ║");
        System.out.println("╟--------------------╢");
        System.out.println("║ 2.   Update User   ║");
        System.out.println("╟--------------------╢");
        System.out.println("║ 3.   Delete User   ║");
        System.out.println("╟--------------------╢");
        System.out.println("║ 4.   Select User   ║");
        System.out.println("╟--------------------╢");
        System.out.println("║ 5. Show all Users  ║");
        System.out.println("╟--------------------╢");
        System.out.println("║        menu        ║");
        System.out.println("╟--------------------╢");
        System.out.println("║        exit        ║");
        System.out.println("╚====================╝");
        System.out.println();
    }
    
    public static void printUsersList(List<User> users) {
        System.out.println(HEADER);
        users.forEach(System.out::println);
        System.out.println(FOOTER);
    }
    
    public static void printUser(User user) {
        System.out.println(HEADER);
        System.out.println(user);
        System.out.println(FOOTER);
    }
}
