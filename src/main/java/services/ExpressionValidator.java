package services;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import utils.Utils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Pattern;

public class ExpressionValidator {

    private static final ExpressionValidator validator = new ExpressionValidator();

    private final DoubleEvaluator evaluator;

    private String key;

    private final Pattern pattern = Pattern.compile(Utils.PATTERN);
    private final Pattern patternForKey = Pattern.compile(Utils.PATTERN_FOR_KEY);
    private final Pattern patternForOrdinary = Pattern.compile(Utils.PATTERN_FOR_ORDINARY);
    private final Pattern patternForKeyToDB = Pattern.compile(Utils.PATTERN_FOR_KEY_TO_DB);
    private final Pattern patternValidFirstLastSign = Pattern.compile(Utils.PATTERN_VALID_FIRST_LAST_SIGN);

    private ExpressionValidator() {
        evaluator = new DoubleEvaluator();
    }

    public static ExpressionValidator getInstance() {
        return validator;
    }

    public boolean validate(String text) {
        if (text.replace('(', '\0').length() == text.length() || text.replace(')', '\0').length() == text.length()) {
            if (isCorrectSigns(text)) {
                return text.contains("key=") ? isCorrectPlacement(text) && isValidatedKey(text) : isCalculate(text);
            }
        } else {
            return isValidBracket(text) && isCorrectSigns(text);
        }
        return false;
    }

    private boolean isCalculate(String expression) {
        if (expression.contains("=")) {
            String[] parts = expression.split("=");
            return evaluator.evaluate(parts[0]).equals(evaluator.evaluate(parts[1]));
        }
        return false;
    }

    public String getKey(String text) {
        int indexOfKey = text.indexOf("key=");
        key = text.substring(indexOfKey).replace("key=", "");
        return text.substring(0, indexOfKey).trim();
    }

    private boolean isValidatedKey(String expression) {
        expression = getKey(expression);
        expression = expression.replace("x", key);
        if (expression.contains("=")) {
            String[] parts = expression.split("=");
            return evaluator.evaluate(parts[0]).equals(evaluator.evaluate(parts[1]));
        }
        return false;
    }

    private boolean isCorrectPlacement(String str) {
        return patternForKey.matcher(str).matches() || patternForOrdinary.matcher(str).matches();
    }

    private boolean isCorrectSigns(String strNum) {
        String[] split = strNum.split("");

        if (!patternValidFirstLastSign.matcher(split[0]).matches() || !pattern.matcher(split[split.length - 1]).matches()) {
            return false;
        }
        for (int i = 0; i < split.length - 1; i++) {
            if (split[i].equals("+") ||
                    split[i].equals("-") ||
                    split[i].equals("*") ||
                    split[i].equals("=") ||
                    split[i].equals("/")) {
                if (!pattern.matcher(split[i + 1]).matches()) {
                    return false;
                }
            }
        }
        return true;
    }


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

    public boolean validateKey(String text) {
        return patternForKeyToDB.matcher(text).matches();
    }
}