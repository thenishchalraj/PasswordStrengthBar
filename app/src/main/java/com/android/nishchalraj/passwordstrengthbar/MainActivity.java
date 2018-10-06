package com.android.nishchalraj.passwordstrengthbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.nishchalraj.passwordstrength.PasswordStrengthBar;

public class MainActivity extends AppCompatActivity {

    PasswordStrengthBar passwordStrengthBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passwordStrengthBar = findViewById(R.id.progressLinearB);
    }
}
