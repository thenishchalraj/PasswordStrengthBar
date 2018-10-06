package com.android.nishchalraj.passwordstrength;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

/**
 * Created by nishchalraj on 06.10.2018.
 */

public class PasswordStrengthBar{

    private View view;
    private LinearLayout plb;
    private ProgressBar pb1;
    private ProgressBar pb2;
    private ProgressBar pb3;
    private ProgressBar pb4;

    public PasswordStrengthBar(Context context) {
        super();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.password_strength_bar, null);
        init();
    }

    private void init(){
        plb = view.findViewById(R.id.progressLinearBar);
        pb1 = view.findViewById(R.id.pBar1);
        pb2 = view.findViewById(R.id.pBar2);
        pb3 = view.findViewById(R.id.pBar3);
        pb4 = view.findViewById(R.id.pBar4);
    }

/*
setProgress()

 */



}
