package com.example.bryan.password;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Password.Password;

public class MainActivity extends AppCompatActivity {

    private EditText passwordLength;
    private EditText symbolCount;
    private EditText numberCount;
    private EditText password;
    private Button clipboardButton;
    private TextView passwordStrength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passwordLength = (EditText) findViewById(R.id.passLength);
        symbolCount = (EditText) findViewById(R.id.symbolCount);
        numberCount = (EditText) findViewById(R.id.numCount);
        password = (EditText) findViewById(R.id.password);
        passwordStrength = (TextView) findViewById(R.id.passwordStrength);

        final Button generateButton = (Button) findViewById(R.id.generateButton);
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generatePassword();
            }
        });

        clipboardButton = (Button) findViewById(R.id.clipboardButton);
        clipboardButton.setEnabled(false);
        clipboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyToClipboard();
            }
        });

    }

    /**
     * Generates a random password based on user input
     */
    private void generatePassword(){
        int passwordLengthInt = 0;
        int numberCountInt = 0;
        int symbolCountInt = 0;

        boolean isValidCombination = true;
        String blankEntries = "";

        //Initializes password length
        if(isEmpty(passwordLength)){
            blankEntries += "• Password Length is empty \n";
        } else{
            passwordLengthInt = Integer.parseInt(passwordLength.getText().toString());
            if(passwordLengthInt > 30){
                isValidCombination = false;
                blankEntries += "• Password is too long! \n";
            }
        }

        //Initializes number count
        if(!isEmpty(numberCount)){
            numberCountInt = Integer.parseInt(numberCount.getText().toString());
            if(numberCountInt > passwordLengthInt){
                isValidCombination = false;
                blankEntries += "• Invalid entry. Number count is too high!\n";
            }
        }

        //Initializes symbol count
        if(!isEmpty(symbolCount)){
            symbolCountInt = Integer.parseInt(symbolCount.getText().toString());
            if(symbolCountInt > (passwordLengthInt - numberCountInt)){
                isValidCombination = false;
                blankEntries += "• Invalid entry. Symbol count is too high! \n";
            }
        }

        //Creates toast messages
        if(!blankEntries.isEmpty()){
            Toast.makeText(getApplicationContext(), blankEntries, Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(getApplicationContext(), "Generated!", Toast.LENGTH_SHORT).show();
            updatePasswordStrength(passwordLengthInt, numberCountInt, symbolCountInt);
        }

        if(isValidCombination){
            final Password newPassword =
                    new Password(passwordLengthInt, numberCountInt, symbolCountInt);
            password.setText(newPassword.generatePassword());
        }

        if(password.getText().toString().trim().isEmpty()){
            clipboardButton.setEnabled(false);
        } else{
            clipboardButton.setEnabled(true);
        }
    }

    /**
     * Copies current password to clipboard
     */
    public void copyToClipboard(){
        String generatedPassword = password.getText().toString();
        ClipboardManager clipboard = (ClipboardManager)
                getSystemService(getApplicationContext().CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Generated Password", generatedPassword);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(),
                "Password copied to clipboard!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Checks if EditText is empty
     * @param etText the EditText object to check if it is empty
     * @return true if EditText is empty, false otherwise
     */
    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    /**
     * Updates the passwordStrength text based on password attributes
     * @param length the length of the password
     * @param numCount the count of Numbers in a password
     * @param symCount the count of Symbols in a password
     */
    private void updatePasswordStrength(int length, int numCount, int symCount) {
        if((length >= 20) || ((length >= 8) && (numCount > 0) && (symCount > 0)) ||
                (length >= 16 && numCount > 0) || (length >= 16 && symCount > 0)){
            passwordStrength.setText("Strong Password :)");
            passwordStrength.setTextColor(Color.GREEN);
        } else if((length >= 6)){
            passwordStrength.setText("Okay Password :|");
            passwordStrength.setTextColor(Color.YELLOW);
        } else{
            passwordStrength.setText("Weak Password :(");
            passwordStrength.setTextColor(Color.RED);
        }

    }
}
