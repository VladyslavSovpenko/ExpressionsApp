package handlers;

import commands.AbstractCommand;
import exceptions.CommandNotFoundException;
import utils.Utils;

public class CommandHandler {

    private static final CommandHandler commandHandler = new CommandHandler();

    private CommandHandler() {
    }

    public static CommandHandler getHandler() {
        return commandHandler;
    }

    public void handleCommand(String text) {
        if (isCommand(text)) {
            AbstractCommand command = getCommand(getCommandName(text));
            text = text.substring(text.indexOf(" "));
            command.execute(text.trim());
        } else {
            System.out.println("Unknown command, command list is: \n" + getListOfCommands());
        }
    }

    private AbstractCommand getCommand(String command) {
        for (int i = 0; i < Utils.ABSTRACT_COMMANDS.length; i++) {
            if (command.equalsIgnoreCase(Utils.ABSTRACT_COMMANDS[i].getCommandName())) {
                return Utils.ABSTRACT_COMMANDS[i];
            }
        }
        throw new CommandNotFoundException();
    }

    private String getCommandName(String text) {
        text = text.replace("/", "");
        return text.substring(0, text.indexOf(" "));
    }

    private String getListOfCommands() {
        StringBuilder stringBuilder = new StringBuilder();
        for (AbstractCommand command : Utils.ABSTRACT_COMMANDS) {
            stringBuilder.append(command.getCommandName()).append("\n");
        }
        return stringBuilder.toString();
    }

    private boolean isCommand(String text) {
        return text.startsWith("/");
    }
}
