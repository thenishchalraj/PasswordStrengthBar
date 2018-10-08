package com.android.nishchalraj.passwordstrengthbar;

import android.graphics.Color;
import android.graphics.PorterDuff;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passwordStrengthBar = findViewById(R.id.passwordBarCheck);
        passwordField = findViewById(R.id.passwordFieldCheck);

        passwordStrengthBar.setNoStrengthColor(Color.BLUE);
        passwordStrengthBar.setStrengthColor(Color.BLACK,Color.BLUE,Color.GRAY,Color.MAGENTA);
        passwordStrengthBar.setStrength(10);
    }
}
