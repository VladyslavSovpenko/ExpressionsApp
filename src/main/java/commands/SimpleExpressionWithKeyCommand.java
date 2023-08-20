package commands;

public class SimpleExpressionWithKeyCommand extends AbstractCommand{

    private static final SimpleExpressionWithKeyCommand simpleExpressionWithKey = new SimpleExpressionWithKeyCommand();

    private SimpleExpressionWithKeyCommand() {
    }

    public static SimpleExpressionWithKeyCommand getInstance(){
        return simpleExpressionWithKey;
    }

    @Override
    public String getCommandName() {
        return "simpleExpressionsWithKey";
    }

    @Override
    public void execute(String text) {

    }
}
