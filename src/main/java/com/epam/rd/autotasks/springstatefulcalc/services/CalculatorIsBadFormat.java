package com.epam.rd.autotasks.springstatefulcalc.services;

/**
 * @author Denys Parshutkin
 * @version 1.0.0
 */
public class CalculatorIsBadFormat {
    public static boolean isBadFormatExpression(String expression) {
        return !(expression.contains("+")||expression.contains("-")||
                expression.contains("/")||expression.contains("*"));
    }

    public static boolean isBadFormatValue(String value) {
        return value == null ||
                ( (value.charAt(0) >= '0' &&
                        value.charAt(0) <= '9') || value.charAt(0) == '-')
                        && (Integer.parseInt(value) >10000 || Integer.parseInt(value) < -10000);
    }

    public static boolean isBadFormatVariable(String variable){
        return variable.matches("[\\d\\s\\WA-Z]");
    }

}
