package com.android.nishchalraj.passwordstrengthbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
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

        Toast.makeText(this, "passwordStrengthBar.getStrength()"+passwordStrengthBar.getStrength(), Toast.LENGTH_SHORT).show();

    }
}
