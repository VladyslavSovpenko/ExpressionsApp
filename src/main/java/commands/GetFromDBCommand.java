package commands;

public class GetFromDBCommand extends AbstractCommand{

    private static final GetFromDBCommand getFromDBCommand = new GetFromDBCommand();

    private GetFromDBCommand() {
    }

    public static GetFromDBCommand getInstance(){
        return getFromDBCommand;
    }

    @Override
    public String getCommandName() {
        return "GetFromDBByKey";
    }

    @Override
    public void execute(String text) {

    }
}
