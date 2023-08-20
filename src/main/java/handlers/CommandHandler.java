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
            text = text.replace("/", "");
            int spaceIndex = text.indexOf(" ");
            String commandStr = text.substring(0, spaceIndex);
            AbstractCommand command = getCommand(commandStr);
            text=text.substring(spaceIndex);
            command.execute(text.trim());
        } else {
            System.out.println("Unknown command, command list is: \n" + getListOfCommands());
        }
    }

    private AbstractCommand getCommand(String command) {
        for (int i = 0; i < Utils.commands.length; i++) {
            if (command.equalsIgnoreCase(Utils.commands[i].getCommandName())) {
                return Utils.commands[i];
            }
        }
        throw new CommandNotFoundException();
    }

    private String getListOfCommands() {
        StringBuilder stringBuilder = new StringBuilder();
        for (AbstractCommand command : Utils.commands) {
            stringBuilder.append(command.getCommandName()).append("\n");
        }
        return stringBuilder.toString();
    }

    private boolean isCommand(String text) {
        return text.startsWith("/");
    }
}
