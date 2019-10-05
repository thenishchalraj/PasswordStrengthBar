package com.android.nishchalraj.passwordstrengthbar;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.nishchalraj.passwordstrength.PasswordStrengthBar;

public class MainActivity extends AppCompatActivity {

    PasswordStrengthBar passwordStrengthBar;
    EditText passwordField;
    int mColor1 = 0;
    int mColor2 = 0;
    int mColor3 = 0;
    int mColor4 = 0;
    TextView check;
    Button see;

    Boolean visible = false;

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
        see = findViewById(R.id.visibilityButton);

        passwordStrengthBar.setStrengthColor(Color.LTGRAY, mColor1, mColor2, mColor3, mColor4);

        passwordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculation(s.toString());
                if (s.length() != 0) {
                    see.setVisibility(View.VISIBLE);
                } else {
                    see.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!visible){
                    passwordField.setTransformationMethod(new PasswordTransformationMethod());
                    visible = false;
                    see.setText(R.string.see_password);
                }
                else if(visible == false){
                    passwordField.setTransformationMethod(null);
                    visible = true;
                    see.setText(R.string.hide_password);
                }
            }
        });

    }

    //the below method calculates the strength of the password and this can be different for different applications
    @SuppressLint("ResourceAsColor")
    protected void calculation(String data) {

        int length = 0, uppercase = 0, lowercase = 0, digits = 0, symbols = 0, bonus = 0, requirements = 0;

        int lettersOnly = 0, numbersOnly = 0, cuc = 0, clc = 0;

        length = data.length();
        for (int i = 0; i < data.length(); i++) {
            if (Character.isUpperCase(data.charAt(i)))
                uppercase++;
            else if (Character.isLowerCase(data.charAt(i)))
                lowercase++;
            else if (Character.isDigit(data.charAt(i)))
                digits++;

            symbols = length - uppercase - lowercase - digits;

        }

        for (int j = 1; j < data.length() - 1; j++) {

            if (Character.isDigit(data.charAt(j)))
                bonus++;

        }

        for (int k = 0; k < data.length(); k++) {

            if (Character.isUpperCase(data.charAt(k))) {
                k++;

                if (k < data.length()) {

                    if (Character.isUpperCase(data.charAt(k))) {

                        cuc++;
                        k--;

                    }

                }

            }

        }

        for (int l = 0; l < data.length(); l++) {

            if (Character.isLowerCase(data.charAt(l))) {
                l++;

                if (l < data.length()) {

                    if (Character.isLowerCase(data.charAt(l))) {

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
            lettersOnly = 1;
        }

        if (lowercase == 0 && uppercase == 0 && symbols == 0) {
            numbersOnly = 1;
        }

        int Total = (length * 4) + ((length - uppercase) * 2)
                + ((length - lowercase) * 2) + (digits * 4) + (symbols * 6)
                + (bonus * 2) + (requirements * 2) - (lettersOnly * length * 2)
                - (numbersOnly * length * 3) - (cuc * 2) - (clc * 2);

        if (Total >= 1 && Total <= 50) {
            check.setText(R.string.bad);
            passwordStrengthBar.setStrength(Total/2);
        } else if (Total >= 51 && Total <= 70) {
            check.setText(R.string.average);
            passwordStrengthBar.setStrength((Total*5)/7);
        } else if (Total >= 71 && Total <= 80) {
            check.setText(R.string.good);
            passwordStrengthBar.setStrength((Total*7)/8);
        } else if (Total >= 81) {
            check.setText(R.string.best);
            passwordStrengthBar.setStrength(Total);
        } else {
            check.setText("");
            passwordStrengthBar.setStrength(passwordStrengthBar.getMinStrength());
        }

    }
}
