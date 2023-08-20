import utils.Utils;
import handlers.CommandHandler;

import java.util.Scanner;

public class ExpressionsApp {

    private static CommandHandler commandHandler = CommandHandler.getHandler();

    public static void main(String[] args) {
        System.out.println(Utils.welcomeMessage);
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        while (!"exit".equalsIgnoreCase(text)){
            commandHandler.handleCommand(text);
            text=scanner.nextLine();
        }
    }
}
