package commands;

import entity.Expression;
import services.DBConnector;
import services.ExpressionValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFromFileCommand extends AbstractCommand {
    private static final ReadFromFileCommand readFromFileCommand = new ReadFromFileCommand();
    ExpressionValidator validator = ExpressionValidator.getInstance();
    DBConnector connector = DBConnector.getInstance();

    private ReadFromFileCommand() {
    }

    public static ReadFromFileCommand getInstance() {
        return readFromFileCommand;
    }

    @Override
    public String getCommandName() {
        return "readFromFile";
    }

    @Override
    public void execute(String fileName) {
        ArrayList<String> expressions = readFromFile(fileName);
        if (!expressions.isEmpty()) {
            expressions.forEach(expression -> {
                if (validator.validate(expression)) {
                    connector.saveToDB(new Expression(expression));
                    System.out.println("Expression: " + expression + " was saved in DB");
                    System.out.println("Done!");
                } else {
                    System.out.println("Expression: " + expression + " was not saved in DB");
                }
            });
        }
    }

    public ArrayList<String> readFromFile(String fileName) {
        ArrayList<String> expressionFromFile = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                expressionFromFile.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Не найден файл blacklist.txt. Уточните путь файла.");
            e.printStackTrace();
        }
        return expressionFromFile;
    }
}