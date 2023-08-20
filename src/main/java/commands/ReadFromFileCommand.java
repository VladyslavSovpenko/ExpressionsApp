package commands;

public class ReadFromFileCommand extends AbstractCommand{
    private static final ReadFromFileCommand readFromFileCommand = new ReadFromFileCommand();

    private ReadFromFileCommand() {
    }

    public static ReadFromFileCommand getInstance(){
        return readFromFileCommand;
    }

    @Override
    public String getCommandName() {
        return "readFromFile";
    }

    @Override
    public String execute(String text) {
        return super.execute(text);
    }
}
