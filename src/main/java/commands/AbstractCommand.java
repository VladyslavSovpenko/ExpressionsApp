package commands;

public abstract class AbstractCommand {

    public String getCommandName() {
        return "default";
    }

    public void execute(String text) {
    }
}
