import handlers.CommandHandler;
import utils.Utils;

import java.util.Scanner;

public class ExpressionsApp {

    private static final CommandHandler commandHandler = CommandHandler.getHandler();

    public static void main(String[] args) {
        System.out.println(Utils.WELCOME_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        while (!"exit".equalsIgnoreCase(text)) {
            commandHandler.handleCommand(text);
            text = scanner.nextLine();
        }
    }
}
