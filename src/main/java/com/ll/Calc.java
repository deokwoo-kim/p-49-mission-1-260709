package com.ll;

import java.lang.reflect.InvocationTargetException;

public class Calc {
    public static int run(String expression) {
        if (expression.startsWith("(") && expression.endsWith(")")) {
            return run(expression.substring(1, expression.length() - 1));
        }

        int close = expression.indexOf(")");
        int open = expression.lastIndexOf("(", close);
        if (close != -1) {
            String innerExpression = expression.substring(open + 1, close);
            int innerResult = run(innerExpression);

            String newExpression =
                    expression.substring(0, open)
                            + innerResult
                            + expression.substring(close + 1);

            return run(newExpression);
        }
        String[] bits = expression.split(" ");
        int sum = 0;
        int result = Integer.parseInt(bits[0]);

        for (int i = 1; i < bits.length; i += 2) {
            String operator = bits[i];
            int number = Integer.parseInt(bits[i + 1]);
            if (operator.equals("*")) {
                result *= number;
            }
            else if (operator.equals("+")) {
                sum += result;
                result = number;
            }
            else if(operator.equals("-")) {
                sum += result;
                result = -number;
            }
        }
        return sum + result;
    }
}
