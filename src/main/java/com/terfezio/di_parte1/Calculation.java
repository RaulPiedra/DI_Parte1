package com.terfezio.di_parte1.Practica6;

import org.mariuszgromada.math.mxparser.Expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculation {
    private final String expressionString;


    private final String numeralSystem;
    private final Expression expression;
    private final boolean isValid;

    public Calculation(String rawExpressionString) {
        this.expressionString = rawExpressionString;
        numeralSystem = "decimal";
        expression = new Expression(expressionString);
        isValid = expression.checkSyntax();
    }
        public Calculation(String rawExpressionString, String numeralSystem) {

        this.numeralSystem = numeralSystem;
        this.expressionString = adaptExpressionString(rawExpressionString);
        expression = new Expression(expressionString);
        isValid = expression.checkSyntax();
    }
    private String adaptExpressionString(String rawExpressionString) {
       switch (numeralSystem) {
           case "binary" -> {
               return rawExpressionString.replaceAll("[0-9.]+", "B.$0");
           }
           case "octal" -> {
               return  rawExpressionString.replaceAll("[0-9.]+", "O.$0");
           }
           case "hexadecimal" -> {
               return  rawExpressionString.replaceAll("[0-9A-F.]+", "H.$0");
           }
           default -> {
               return rawExpressionString;
           }
       }
    }


    public boolean isValid() {

        return isValid;
    }

    public String getErrors() {

        StringBuilder errors = new StringBuilder();
        String syntaxCheck = expression.getErrorMessage();
        Matcher matcher = Pattern.compile("\\[\\S*]\\slexical\\serror").matcher(syntaxCheck);
        while (matcher.find()) {

            errors.append(matcher.group());
        }
        return errors.toString();
    }

    public String[] getResult() {
        double result = expression.calculate();
        int resultDecimal = (int) result;
        String resultBinary = Integer.toBinaryString(resultDecimal);
        String resultOctal = Integer.toOctalString(resultDecimal);
        String resultHexadecimal = Integer.toHexString(resultDecimal).toUpperCase();
        return new String[]{resultBinary, resultOctal, String.valueOf(result), resultHexadecimal};
    }

    public String getExpressionString() {
        return expressionString;
    }
    public String getNumeralSystem() {
        return numeralSystem;
    }
}
