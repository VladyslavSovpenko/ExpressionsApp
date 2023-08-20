package services;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import utils.Utils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Pattern;

public class ExpressionValidator {

    private static final ExpressionValidator validator = new ExpressionValidator();

    private final DoubleEvaluator evaluator;

    private ExpressionValidator() {
        evaluator = new DoubleEvaluator();
    }

    public static ExpressionValidator getInstance() {
        return validator;
    }

    public boolean validate(String text) {
        if (text.replace('(', '\0').length() == text.length() || text.replace(')', '\0').length() == text.length()) {
            return isCorrectSigns(text);
        } else {
            return isValidBracket(text) && isCorrectSigns(text);
        }
    }

    public boolean isCorrectSigns(String strNum) {
        String[] split = strNum.split("");
        for (int i = 0; i < split.length; i++) {
            if (split[i].equalsIgnoreCase("+") ||
                    split[i].equalsIgnoreCase("-") ||
                    split[i].equalsIgnoreCase("*") ||
                    split[i].equalsIgnoreCase("=") ||
                    split[i].equalsIgnoreCase("/")) {
                if (!pattern.matcher(split[i+1]).matches()){
                    return false;
                }
            }
        }
        return true;
    }

    private final Pattern pattern = Pattern.compile(Utils.pattern);


    private boolean isValidBracket(String text) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < text.length(); i++) {
            char x = text.charAt(i);

            if (x == '(') {
                stack.push(x);
                continue;
            }
            if (stack.isEmpty()) {
                return false;
            }
            if (x == ')' && stack.peek() == '(') {
                stack.pop();
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean checkWithKey(String expression) {
        String[] parts = expression.split("=");
        Double resultLeft = evaluator.evaluate(parts[0]);
        Double resultRight = evaluator.evaluate(parts[1]);
        return resultLeft.equals(resultRight);
    }
}