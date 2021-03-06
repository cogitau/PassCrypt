package Password;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by byamba on 3/14/17.
 */
public class PasswordTest {

    String letterMap = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    char letterMapArr[] = letterMap.toCharArray();
    String numberMap = "0123456789";
    char numberMapArr[] = numberMap.toCharArray();
    String symbolMap = "!@#$%^&*()_+=-|}{[]\":;'?><,./~`";
    char symbolMapArr[] = symbolMap.toCharArray();

    @Test
    public void generatePassword() throws Exception {
        Password testPassword = new Password(5, 2, 1);
        String password = testPassword.generatePassword();
        assertEquals(password.length(), 5);

        //Converts password into List of characters
        char passwordArr[] = password.toCharArray();
        List<Character> passwordList = new ArrayList<>();
        for (char c : passwordArr) {
            passwordList.add(c);
        }

        int letterCount = 0;
        for (char c : letterMapArr){
            letterCount += Collections.frequency(passwordList, c);
        }

        int numberCount = 0;
        for (char c : numberMapArr){
            numberCount += Collections.frequency(passwordList, c);
        }

        int symbolCount = 0;
        for (char c : symbolMapArr){
            symbolCount += Collections.frequency(passwordList, c);
        }

        assertEquals(letterCount, 2);
        assertEquals(numberCount, 2);
        assertEquals(symbolCount, 1);
    }

    @Test
    public void generatePasswordInvalid() throws Exception {
        Password testPassword = new Password(-10, -10, 23);
        String password = testPassword.generatePassword();
        System.out.println(password);
        assertEquals(password.length(), 0);

        //Converts password into List of characters
        char passwordArr[] = password.toCharArray();
        List<Character> passwordList = new ArrayList<>();
        for (char c : passwordArr) {
            passwordList.add(c);
        }

        int letterCount = 0;
        for (char c : letterMapArr){
            letterCount += Collections.frequency(passwordList, c);
        }

        int numberCount = 0;
        for (char c : numberMapArr){
            numberCount += Collections.frequency(passwordList, c);
        }

        int symbolCount = 0;
        for (char c : symbolMapArr){
            symbolCount += Collections.frequency(passwordList, c);
        }

        assertEquals(letterCount, 0);
        assertEquals(numberCount, 0);
        assertEquals(symbolCount, 0);
    }

    @Test
    public void generateLetters() throws Exception {
        Password testPassword = new Password(5, 2, 1);
        List<Character> characterMapList = new ArrayList<>();
        for (char c : letterMapArr) {
            characterMapList.add(c);
        }

        char letters[] = testPassword.generateLetters().toCharArray();
        assertEquals(letters.length, 2);

        for (char character : letters){
            assertTrue(characterMapList.contains(character));
        }
    }

    @Test
    public void generateLettersInvalid() throws Exception {
        Password testPassword = new Password(-1, 5, -2);
        List<Character> characterMapList = new ArrayList<>();
        for (char c : letterMapArr) {
            characterMapList.add(c);
        }

        char letters[] = testPassword.generateLetters().toCharArray();
        assertEquals(letters.length, 0);

        for (char character : letters){
            assertTrue(characterMapList.contains(character));
        }
    }

    @Test
    public void generateNumbers() throws Exception {
        Password testPassword = new Password(5, 2, 1);
        List<Character> characterMapList = new ArrayList<Character>();
        for (char c : numberMapArr) {
            characterMapList.add(c);
        }

        char numbers[] = testPassword.generateNumbers().toCharArray();
        assertEquals(numbers.length, 2);

        for (char character : numbers){
            assertTrue(characterMapList.contains(character));
        }
    }

    @Test
    public void generateNumbersInvalid() throws Exception {
        Password testPassword = new Password(0, 21, 11);
        List<Character> characterMapList = new ArrayList<Character>();
        for (char c : numberMapArr) {
            characterMapList.add(c);
        }

        char numbers[] = testPassword.generateNumbers().toCharArray();
        assertEquals(numbers.length, 0);

        for (char character : numbers){
            assertTrue(characterMapList.contains(character));
        }
    }

    @Test
    public void generateSymbols() throws Exception {
        Password testPassword = new Password(5, 2, 1);
        List<Character> characterMapList = new ArrayList<>();
        for (char c : symbolMapArr) {
            characterMapList.add(c);
        }

        char symbols[] = testPassword.generateSymbols().toCharArray();
        assertEquals(symbols.length, 1);

        for (char character : symbols){
            assertTrue(characterMapList.contains(character));
        }
    }

    @Test
    public void generateSymbolsInvalid() throws Exception {
        Password testPassword = new Password(0, 5, 12);
        List<Character> characterMapList = new ArrayList<>();
        for (char c : symbolMapArr) {
            characterMapList.add(c);
        }

        char symbols[] = testPassword.generateSymbols().toCharArray();
        assertEquals(symbols.length, 0);

        for (char character : symbols){
            assertTrue(characterMapList.contains(character));
        }
    }


}