package Password;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by byamba on 3/14/17.
 */

public class Password {

    private int length;
    private int numCount;
    private int symbolCount;
    private String password;
    private final String SYMBOL_MAP = "!@#$%^&*()_+=-|}{[]\":;'?><,./~`";
    private boolean isValidPassword;

    public Password(int length, int numCount, int symbolCount) {
        assert (length >= 0);
        assert (numCount >= 0) && (numCount <= length);
        assert (symbolCount >= 0) && (symbolCount <= length);

        boolean isPositive = (length >= 0) && (numCount >= 0) && (symbolCount >= 0);
        if(isPositive && ((numCount + symbolCount) < length)){
            this.length = length;
            this.numCount = numCount;
            this.symbolCount = symbolCount;
            password = "";
        } else{
            isValidPassword = false;
            this.length = 0;
            this.numCount = 0;
            this.symbolCount = 0;
            password = "";
        }
    }

    /**
     * Generates a shuffled password based on a unique number of numbers, and symbols
     * @return the unique password
     */
    public String generatePassword() {
        if (length == 0) {
            return password;
        }

        password += generateNumbers();
        password += generateSymbols();
        password += generateLetters();

        shufflePassword();

        return password;
    }

    /**
     * Generates random letters A-Z and a-z if enough space is left in the password
     * @return the String containing all the random letters
     */
    public String generateLetters() {
        String letterList = "";
        int letterCount = length - (numCount + symbolCount);

        Random randomGenerator = new Random();
        for (int i = 0; i < letterCount; i++) {
            char passChar = (char) (randomGenerator.nextInt(26) + 'a');
            int upperCaseChance = randomGenerator.nextInt(2);
            if (upperCaseChance == 1) {
                passChar = Character.toUpperCase(passChar);
            }
            letterList += passChar;
        }

        return letterList;
    }

    /**
     * Generates random numbers 0-9 based on user given amount
     * @return the String containing the random numbers
     */
    public String generateNumbers() {
        String numberList = "";

        if (numCount == 0) {
            return "";
        }

        Random randomGenerator = new Random();
        for (int i = 0; i < numCount; i++) {
            numberList += randomGenerator.nextInt(10);
        }
        return numberList;
    }

    /**
     * Generates random symbols based on custom symbol map on user giver given amount
     * @return the String containing the random symbols
     */
    public String generateSymbols() {
        String symbolList = "";

        if (numCount == 0) {
            return "";
        }

        Random randomGenerator = new Random();
        for (int i = 0; i < symbolCount; i++) {
            symbolList += SYMBOL_MAP.charAt(randomGenerator.nextInt(SYMBOL_MAP.length()));
        }

        return symbolList;
    }

    /**
     * Shuffles the password
     */
    public void shufflePassword() {
        password = shuffleString(password);
    }

    /**
     * Helper function to shuffle a string
     * @param shuffleString
     */
    private String shuffleString(String shuffleString){
        List<String> stringAsList = Arrays.asList(shuffleString.split(""));
        Collections.shuffle(stringAsList);

        String shuffledString = "";
        for (String letter : stringAsList) {
            shuffledString += letter;
        }
        return shuffledString;
    }
}
