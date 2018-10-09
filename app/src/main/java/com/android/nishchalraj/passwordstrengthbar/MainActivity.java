package com.android.nishchalraj.passwordstrengthbar;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.nishchalraj.passwordstrength.PasswordStrengthBar;

public class MainActivity extends AppCompatActivity {

    PasswordStrengthBar passwordStrengthBar;
    EditText passwordField;
    int mColor1 = 0;
    int mColor2 = 0;
    int mColor3 = 0;
    int mColor4 = 0;

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

        passwordStrengthBar.setStrengthColor(Color.LTGRAY, mColor1, mColor2, mColor3, mColor4);
        passwordStrengthBar.setMaxStrength(200);
        passwordStrengthBar.setStrength(50);
        Toast.makeText(this, ""+passwordStrengthBar.getStrength(), Toast.LENGTH_SHORT).show();
    }
}
