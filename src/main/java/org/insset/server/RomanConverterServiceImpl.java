package org.insset.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.insset.client.service.RomanConverterService;

/**
 * Implementation of the RomanConverterService interface.
 * Provides methods to convert Arabic numbers to Roman numerals and vice versa.
 */
@SuppressWarnings("serial")
public class RomanConverterServiceImpl extends RemoteServiceServlet implements RomanConverterService {

    @Override
    public String convertDateYears(String nbr) throws IllegalArgumentException {
        if (nbr == null || nbr.isEmpty()) {
            throw new IllegalArgumentException("Input number cannot be null or empty");
        }

        try {
            int year = Integer.parseInt(nbr);
            return convertArabeToRoman(year);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format: " + nbr, e);
        }
    }

    @Override
    public Integer convertRomanToArabe(String nbr) throws IllegalArgumentException {
        if (nbr == null || nbr.isEmpty()) {
            throw new IllegalArgumentException("Input Roman numeral cannot be null or empty");
        }

        int result = 0;
        int prevValue = 0;
        
        for (int i = nbr.length() - 1; i >= 0; i--) {
            int value = romanCharToInt(nbr.charAt(i));
            if (value < prevValue) {
                result -= value; // If the current value is less than the previous value, subtract it
            } else {
                result += value; // Otherwise, add it
            }
            prevValue = value;
        }
        
        return result;
    }

    @Override
    public String convertArabeToRoman(Integer nbr) throws IllegalArgumentException {
        if (nbr == null || nbr <= 0 || nbr > 3000) {
            throw new IllegalArgumentException("Input number must be between 1 and 3000");
        }

        StringBuilder roman = new StringBuilder();
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] numerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int i = 0; i < values.length; i++) {
            while (nbr >= values[i]) {
                nbr -= values[i];
                roman.append(numerals[i]);
            }
        }
        
        return roman.toString();
    }

    // Helper method to convert Roman numeral characters to integer values
    private int romanCharToInt(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: throw new IllegalArgumentException("Invalid Roman numeral: " + c);
        }
    }
}
