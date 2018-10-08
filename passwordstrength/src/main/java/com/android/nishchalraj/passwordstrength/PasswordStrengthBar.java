package com.android.nishchalraj.passwordstrength;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

/**
 * Created by nishchalraj on 06.10.2018.
 */

public class PasswordStrengthBar extends LinearLayout{

    //UI
    protected LinearLayout plb;
    protected ProgressBar pb1;
    protected ProgressBar pb2;
    protected ProgressBar pb3;
    protected ProgressBar pb4;

    protected LayoutInflater mInflater;

    //Attributes
    private int mMax = 100;
    private int mMin = 0;
    private int mNoStrengthColor = Color.LTGRAY;
    private int mStrengthColor1 = Color.DKGRAY;
    private int mStrengthColor2 = Color.DKGRAY;
    private int mStrengthColor3 = Color.DKGRAY;
    private int mStrengthColor4 = Color.DKGRAY;

    public PasswordStrengthBar(Context context) {
        super(context,null);
        init(context);
    }

    public PasswordStrengthBar(Context context, AttributeSet attrs) {
        super(context, attrs,0);
        init(context);
    }

    public PasswordStrengthBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    //initiate views
    protected void init(Context context){
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

//    android:progressBackgroundTint="#ffffff"
//    android:progressTint="#000"

    //to set the password strength to default chosen color
    public void setNoStrengthColor(int noStrengthColor){
        this.mNoStrengthColor = noStrengthColor;

        Drawable bckgrndDr = new ColorDrawable(Color.RED);
        Drawable secProgressDr = new ColorDrawable(Color.DKGRAY);
        Drawable progressDr = new ScaleDrawable(new ColorDrawable(Color.LTGRAY), Gravity.LEFT, 1, -1);
        LayerDrawable resultDr = new LayerDrawable(new Drawable[] { bckgrndDr, secProgressDr, progressDr });
        pb1.setProgressDrawable(resultDr);

        pb2.getProgressDrawable().setColorFilter(mNoStrengthColor,PorterDuff.Mode.SRC_IN);
        pb3.getProgressDrawable().setColorFilter(mNoStrengthColor,PorterDuff.Mode.SRC_IN);
        pb4.getProgressDrawable().setColorFilter(mNoStrengthColor,PorterDuff.Mode.SRC_IN);
    }

    //to get the maximum and minimum value to which the password strength can be calculated
    public int getMax(){
        return mMax;
    }
    public int getMin(){
        return mMin;
    }

    //to set the maximum and minimum value to which the password strength can be calculated
    public void setMax(int max){
        this.mMax = max;
    }
    public void setMin(int min){
        this.mMin = min;
    }

    //to set the background of the bar
    public void setBackground(int backgroundColor){
        plb.setBackgroundColor(backgroundColor);
    }

    //to set the color of the bar
    public void setStrengthColor(int color1, int color2, int color3, int color4){
        this.mStrengthColor1 = color1;
        this.mStrengthColor2 = color2;
        this.mStrengthColor3 = color3;
        this.mStrengthColor4 = color4;
        //TODO: set color according to the strength
    }

    //to get the strength of the bar
    public int getStrength(){
        return (pb1.getProgress()+pb2.getProgress()+pb3.getProgress()+pb4.getProgress())/4 ;
    }

    //to set the strength of the bar
    public void setStrength(int strength){
        pb1.setProgress(strength);
    }


}
