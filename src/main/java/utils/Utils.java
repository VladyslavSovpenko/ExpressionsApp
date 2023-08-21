package utils;

import commands.AbstractCommand;
import commands.GetFromDBCommand;
import commands.ReadFromFileCommand;
import commands.SimpleExpressionCommand;

public interface Utils {
    String WELCOME_MESSAGE = "Welcome to ExpressionsApp.\n\n" +
            "Enter `/simpleExpression` and write expression for brace checking and if it spelled correctly save to DB\n" +
            "Example, `/simpleExpression 2*x+5=7 key=1`\n" +
            "Enter `/readFromFile` and write file name and put file in root, example `/readFromFile expressions.txt` \n" +
            "Enter `/getFromDBByKey` and write key, example `/getFromDBByKey 1`\n" +
            "If you want get by 2 or more key - write with space, example `/getFromDBByKey 1 2 3`\n\n" +
            "To leave - enter `exit`";

    AbstractCommand[] ABSTRACT_COMMANDS = {
            SimpleExpressionCommand.getInstance(),
            ReadFromFileCommand.getInstance(),
            GetFromDBCommand.getInstance()
    };

    String WAITING_ANSWER_FROM_BD = "Searching in the database by key. Wait...";

    String PATTERN = "^([-+]?(?:\\d*\\.?\\d+|x)\\s*)+$";
    String PATTERN_FOR_KEY = "^(?=.*[\\dxX+\\-*/]).*=\\s*[\\dxX]+\\s+key=\\s*[\\dxX]+\\s*$";
    String PATTERN_FOR_ORDINARY = "^(?:([-+]?\\s*\\d*\\.?\\d*\\*?x?\\s*)?[+\\-*/]?\\s*)+=\\s*[-+]?\\s*\\d*\\.?\\d*$";
    String PATTERN_FOR_KEY_TO_DB = "^(\\d+\\s+)*\\d+$";
    String PATTERN_VALID_FIRST_LAST_SIGN = "^[-+]?\\s*\\d*\\.?\\d*\\*?x?\\s*[+\\-*/]?\\s*";
    String EMPTY_LIST = "List is Empty";
}
