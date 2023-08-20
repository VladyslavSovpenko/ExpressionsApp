package commands;

public class SimpleExpressionCommand extends AbstractCommand{
    private static final SimpleExpressionCommand simpleExpressionCommand = new SimpleExpressionCommand();
    private SimpleExpressionCommand() {
    }

    public static SimpleExpressionCommand getInstance(){
        return simpleExpressionCommand;
    }

    @Override
    public String getCommandName() {
        return "simpleExpression";
    }

    @Override
    public String execute(String text) {
        return null;

    }
}
