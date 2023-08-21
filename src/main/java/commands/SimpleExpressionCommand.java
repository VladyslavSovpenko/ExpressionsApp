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
            System.out.println("Expression: " + text + " will save in DB. Wait...");
            connector.saveToDB(getEntity(text));
            System.out.println("Done!");
        } else {
            System.out.println("Expression was not saved in DB");
        }
    }

    private Expression getEntity(String text) {
        Expression expression = new Expression();
        if (text.contains("key=")) {
            int indexOfKey = text.indexOf("key=");
            expression.setKey(text.substring(indexOfKey).replace("key=", ""));
            expression.setExpression(text.substring(0, indexOfKey).trim());
        } else {
            expression.setExpression(text);
        }
        return expression;
    }
}
