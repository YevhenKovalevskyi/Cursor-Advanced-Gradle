package hw04.task1.helpers;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author YevhenKovalevskyi
 */
@Slf4j
public class ScannerHelper {
    
    /**
     * Check for Exit from Scanner
     *
     * @param in Scanner instance
     */
    public static void checkForExitScannerNext(Scanner in) {
        if (in.next().equals("exit")) {
            log.info("Exit program.");
            ScannerHelper.runExitFull(in);
        }
    }
    
    /**
     * Check for Exit from Scanner
     *
     * @param in Scanner instance
     */
    public static void checkForExitScannerString(Scanner in, String string) {
        if (string.equals("exit")) {
            log.info("Exit program.");
            ScannerHelper.runExitFull(in);
        }
    }
    
    /**
     * Exit from Scanner
     *
     * @param in Scanner instance
     */
    public static void runExitScanner(Scanner in) {
        System.out.println("-> Good bye!");
        in.close();
    }
    
    /**
     * Exit from the program
     *
     * @param in Scanner instance
     */
    public static void runExitFull(Scanner in) {
        System.out.println("-> Good bye!");
        in.close();
        System.exit(0);
    }
}
