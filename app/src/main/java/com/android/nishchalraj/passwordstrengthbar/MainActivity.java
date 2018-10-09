package com.android.nishchalraj.passwordstrengthbar;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.nishchalraj.passwordstrength.PasswordStrengthBar;

public class MainActivity extends AppCompatActivity {

    PasswordStrengthBar passwordStrengthBar;
    EditText passwordField;
    int mColor1 = 0;
    int mColor2 = 0;
    int mColor3 = 0;
    int mColor4 = 0;
    TextView check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mColor1 = ContextCompat.getColor(this, R.color.colorRed);
        mColor2 = ContextCompat.getColor(this, R.color.colorOrange);
        mColor3 = ContextCompat.getColor(this, R.color.colorLightGreen);
        mColor4 = ContextCompat.getColor(this, R.color.colorDarkGreen);

        passwordStrengthBar = findViewById(R.id.passwordBarCheck);
        passwordField = findViewById(R.id.passwordFieldCheck);

        check = findViewById(R.id.strengthText);

        passwordStrengthBar.setStrengthColor(Color.LTGRAY, mColor1, mColor2, mColor3, mColor4);

        passwordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculate(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

    }

    //the below method calculates the strength of the password and this can be different for different applications
    @SuppressLint("ResourceAsColor")
    protected void calculate(String data) {

        String temp = data;

        int length = 0, uppercase = 0, lowercase = 0, digits = 0, symbols = 0, bonus = 0, requirements = 0;

        int lettersonly = 0, numbersonly = 0, cuc = 0, clc = 0;

        length = temp.length();
        for (int i = 0; i < temp.length(); i++) {
            if (Character.isUpperCase(temp.charAt(i)))
                uppercase++;
            else if (Character.isLowerCase(temp.charAt(i)))
                lowercase++;
            else if (Character.isDigit(temp.charAt(i)))
                digits++;

            symbols = length - uppercase - lowercase - digits;

        }

        for (int j = 1; j < temp.length() - 1; j++) {

            if (Character.isDigit(temp.charAt(j)))
                bonus++;

        }

        for (int k = 0; k < temp.length(); k++) {

            if (Character.isUpperCase(temp.charAt(k))) {
                k++;

                if (k < temp.length()) {

                    if (Character.isUpperCase(temp.charAt(k))) {

                        cuc++;
                        k--;

                    }

                }

            }

        }

        for (int l = 0; l < temp.length(); l++) {

            if (Character.isLowerCase(temp.charAt(l))) {
                l++;

                if (l < temp.length()) {

                    if (Character.isLowerCase(temp.charAt(l))) {

                        clc++;
                        l--;

                    }

                }

            }

        }

        if (length > 7) {
            requirements++;
        }

        if (uppercase > 0) {
            requirements++;
        }

        if (lowercase > 0) {
            requirements++;
        }

        if (digits > 0) {
            requirements++;
        }

        if (symbols > 0) {
            requirements++;
        }

        if (bonus > 0) {
            requirements++;
        }

        if (digits == 0 && symbols == 0) {
            lettersonly = 1;
        }

        if (lowercase == 0 && uppercase == 0 && symbols == 0) {
            numbersonly = 1;
        }

        int Total = (length * 4) + ((length - uppercase) * 2)
                + ((length - lowercase) * 2) + (digits * 4) + (symbols * 6)
                + (bonus * 2) + (requirements * 2) - (lettersonly * length * 2)
                - (numbersonly * length * 3) - (cuc * 2) - (clc * 2);

        if (Total >= 1 && Total <= 50) {
            check.setText("BAD");
            passwordStrengthBar.setStrength(Total/2);
        } else if (Total >= 51 && Total <= 70) {
            check.setText("AVERAGE");
            passwordStrengthBar.setStrength((Total*5)/7);
        } else if (Total >= 71 && Total <= 80) {
            check.setText("GOOD");
            passwordStrengthBar.setStrength((Total*7)/8);
        } else if (Total >= 81) {
            check.setText("BEST");
            passwordStrengthBar.setStrength(Total);
        } else {
            check.setText("");
            passwordStrengthBar.setStrength(passwordStrengthBar.getMinStrength());
        }

    }
}
