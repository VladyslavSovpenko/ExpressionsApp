package commands;

public abstract class AbstractCommand {

    public String getCommandName() {
        return "default";
    }

    public String execute(String text){
        return null;
    }
}
