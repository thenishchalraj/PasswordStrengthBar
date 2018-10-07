package com.android.nishchalraj.passwordstrength;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

/**
 * Created by nishchalraj on 06.10.2018.
 */

public class PasswordStrengthBar extends LinearLayout{

//    protected View view;

    //UI
    protected LinearLayout plb;
    protected ProgressBar pb1;
    protected ProgressBar pb2;
    protected ProgressBar pb3;
    protected ProgressBar pb4;

    protected LayoutInflater mInflater;

    public PasswordStrengthBar(Context context) {
        super(context);
        init(context, null);
    }

    public PasswordStrengthBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PasswordStrengthBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);

    }

    protected void init(Context context, AttributeSet attrs){
        mInflater = LayoutInflater.from(context);

        //load view from xml
        View view = mInflater.inflate(R.layout.password_strength_bar,this,true);

        //init UI
        plb = view.findViewById(R.id.progressLinearBar);
        pb1 = view.findViewById(R.id.pBar1);
        pb2 = view.findViewById(R.id.pBar2);
        pb3 = view.findViewById(R.id.pBar3);
        pb4 = view.findViewById(R.id.pBar4);
    }

    //to get the maximum and minimum value to which the password strength can be calculated
    public int getMax(){
        return 100;
    }

    public int getMin(){
        return 0;
    }

    //to set the background of the bar
    public void setBackground(int backgroundColor){
        plb.setBackgroundColor(backgroundColor);
    }

    //to set the color of the bar
    public void setColor(int color1, int color2, int color3, int color4){
        pb1.setBackgroundColor(color1);
        pb2.setBackgroundColor(color2);
        pb3.setBackgroundColor(color3);
        pb4.setBackgroundColor(color4);
    }

    //to get the strength of the bar
    public int getStrength(){
        return (pb1.getProgress()+pb2.getProgress()+pb3.getProgress()+pb4.getProgress())/4 ;
    }

    //to set the strength of the bar
    public void setStrength(int strength){

    }


}
