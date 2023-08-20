package commands;

import entity.Expression;
import services.DBConnector;
import services.ExpressionValidator;

public class SimpleExpressionCommand extends AbstractCommand {

    private static final SimpleExpressionCommand simpleExpressionCommand = new SimpleExpressionCommand();

    ExpressionValidator validator = ExpressionValidator.getInstance();
    DBConnector connector = DBConnector.getInstance();

    private SimpleExpressionCommand() {
    }

    public static SimpleExpressionCommand getInstance() {
        return simpleExpressionCommand;
    }

    @Override
    public String getCommandName() {
        return "simpleExpression";
    }

    @Override
    public void execute(String text) {
        if (validator.validate(text)) {
            connector.saveToDB(new Expression(text));
            System.out.println("Expression was saved in DB");
        } else {
            System.out.println("Expression was not saved in DB");
        }
    }
}
