package org.insset.client.division;

public class Division {

    public static double divide(double number1, double number2) throws ArithmeticException {
        if (number2 == 0) {
            throw new ArithmeticException("Division par zéro");
        }
        return number1 / number2;
    }
}
