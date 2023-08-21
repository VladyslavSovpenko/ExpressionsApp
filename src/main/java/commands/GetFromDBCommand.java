package commands;

import services.DBConnector;
import services.ExpressionValidator;
import utils.Utils;

public class GetFromDBCommand extends AbstractCommand {

    private static final GetFromDBCommand getFromDBCommand = new GetFromDBCommand();
    DBConnector connector = DBConnector.getInstance();
    ExpressionValidator validator = ExpressionValidator.getInstance();

    private GetFromDBCommand() {
    }

    public static GetFromDBCommand getInstance() {
        return getFromDBCommand;
    }

    @Override
    public String getCommandName() {
        return "getFromDBByKey";
    }

    @Override
    public void execute(String text) {
        if (validator.validateKey(text)) {
            System.out.println(Utils.WAITING_ANSWER_FROM_BD);
            String[] keys = text.split(" ");
            StringBuilder builder = new StringBuilder();
            String sql;
            if (keys.length != 1) {
                for (String key : keys) {
                    builder.append("'").append(key).append("', ");
                }
                builder.delete(builder.length() - 2, builder.length());
                sql = builder.toString();
            } else {
                sql = builder.append("'").append(text).append("'").toString();
            }
            if (connector.getFromDB(sql).isEmpty()){
                System.out.println(Utils.EMPTY_LIST);
            }
        }
    }
}
