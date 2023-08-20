package utils;

import commands.*;

public interface Utils {
    String welcomeMessage = "Welcome to ExpressionsApp.\n" +
            "Enter `/simpleExpression` and write expression for brace checking and if it spelled correctly save to DB\n" +
            "Enter `/readFromFile` and write file name for brace checking and if expression spelled correctly save to DB\n" +
            "Enter `/SimpleExpressionWithKey` and write expression and key for brace checking and if it spelled correctly save to DB\n" +
            "Enter `/GetFromDBByKey` and write expression and key for brace checking and if it spelled correctly save to DB\n\n" +

            "To leave - enter `exit`";

    AbstractCommand[] commands = {
            SimpleExpressionCommand.getInstance(),
            ReadFromFileCommand.getInstance(),
            SimpleExpressionWithKeyCommand.getInstance(),
            GetFromDBCommand.getInstance()
    };
}
