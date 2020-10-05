package com.android.thenishchalraj.passwordstrength;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
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
 * Created by thenishchalraj on 10.10.2018.
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
    private int mStrengthColor1 = Color.RED;
    private int mStrengthColor2 = Color.YELLOW;
    private int mStrengthColor3 = Color.GREEN;
    private int mStrengthColor4 = Color.DKGRAY;

    //New Attributes by AJIRI GUNN
    private int passBarsNum;
    private boolean shouldUseCustomBars;
    private int strength;
    private int defaultBarColor;
    private int[] strenthColorsArray;

    public int getPassBarsNum() {
        return passBarsNum;
    }

    private void setPassBarsNum(int passBarsNum) {
        this.passBarsNum = passBarsNum;
        shouldUseCustomBars =true;
    }

    private void setupStrengthColors(int passBars) {
        int r=255;
        int g=0;
        int b=0;
        strenthColorsArray =new int[passBars];
        double colorDiff=Math.floor(250/(Math.ceil(passBars/2)));//differences between colors

        //START with RED. Increase GREEN component till mid-bar.REDUCE RED component.
        for(int i=0;i<passBars;i++){
            int newColor=Color.rgb(r,g,b);
            strenthColorsArray[i]=newColor;
            if(i>=Math.floor(passBars/2)) {//
                r -= colorDiff;
            }else{
                g+=colorDiff;
            }
        }
    }

//
    public PasswordStrengthBar(Context context) {
        super(context,null);
        init(context, null);
    }

    public PasswordStrengthBar(Context context, AttributeSet attrs) {
        super(context, attrs,0);
        init(context,attrs);
    }

    public PasswordStrengthBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**initiate views*/
    protected void init(Context context, AttributeSet attrs){
        mInflater = LayoutInflater.from(context);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.password_strength, 0, 0);
        try {
            mMax=a.getInteger(R.styleable.password_strength_maxProgress,0);
            mMin=a.getInteger(R.styleable.password_strength_minProgress,0);
            passBarsNum =a.getInteger(R.styleable.password_strength_bars,0);
            strength=a.getInteger(R.styleable.password_strength_strength,0);
            defaultBarColor =a.getColor(R.styleable.password_strength_defColor,Color.GRAY);
        } finally {
            a.recycle();
        }

        if(passBarsNum ==0){
            setDefaultViews();
        }else {
            setCustomBarViews();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setMinStrength(mMin);
            setMaxStrength(mMax);
        }
    }

    private void setCustomBarViews(){
        shouldUseCustomBars =true;
        setupStrengthColors(passBarsNum);
        defaultBarColor = Color.DKGRAY;
        this.invalidate();
    }

    private void setDefaultViews(){
        //        load view from xml
        shouldUseCustomBars =false;
        View view = mInflater.inflate(R.layout.password_strength_bar,this,true);
        //init UI
        plb = view.findViewById(R.id.progressLinearBar);
        pb1 = view.findViewById(R.id.pBar1);
        pb2 = view.findViewById(R.id.pBar2);
        pb3 = view.findViewById(R.id.pBar3);
        pb4 = view.findViewById(R.id.pBar4);
        setMaxStrength(mMax);
        setMinStrength(mMin);
        setStrength(strength);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(!shouldUseCustomBars){
            return;
        }
        float width=getWidth();
        float height=getHeight();
        float rectRadius=getHeight()/2;
        float percentPerBar=mMax/ passBarsNum;

        float barWidth=((100-0.01f* passBarsNum)*width)/(100* passBarsNum);
        float left= (float) (0.001*width);
        float top= (float) (height*0.01);
        float bottom=(float) (height*0.99);
        float right=left+barWidth;

        for(int i = 1; i<= passBarsNum; i++){

            if (i*percentPerBar<=strength || i*percentPerBar<=mMin) {
                //Draw a fully colored curved bar
                drawCurvedBar(canvas,left,top,right,bottom,rectRadius,strenthColorsArray[i-1]);

            } else if(i*percentPerBar>=strength &&(i-1)*percentPerBar<strength ) {

                float percentProgress=(strength-(i-1)*percentPerBar)/100;
                float tempRight=percentProgress*barWidth+left;
                //Draw colored part of progressBar
                drawBar(canvas,left,top,tempRight,bottom,strenthColorsArray[i-1]);
                //Draw neutral part of progressBar
                drawBar(canvas,tempRight,top,right,bottom,defaultBarColor);
            }else{
                //Draw a neutral colored curved bar
                drawCurvedBar(canvas,left,top,right,bottom,rectRadius,defaultBarColor);
            }
            left= (float) (right+(0.001*width));
            right=left+barWidth;
        }
    }

    private void drawCurvedBar(Canvas canvas, float left, float top, float right, float bottom, float radius, int color){
        Paint p=new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(color);
        RectF f=new RectF(left,top,right,bottom);
        canvas.drawRoundRect(f,radius,radius,p);
    }

    private void drawBar(Canvas canvas, float left, float top, float right, float bottom,  int color){
        Paint p=new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(color);
        RectF f=new RectF(left,top,right,bottom);
        canvas.drawRect(f,p);
    }
    /**
     * Implement the below method to set the password strength to default chosen color
     */
    public void setStrengthColor(int noStrengthColor, int color1, int color2, int color3, int color4){

        this.mNoStrengthColor = noStrengthColor;
        this.mStrengthColor1 = color1;
        this.mStrengthColor2 = color2;
        this.mStrengthColor3 = color3;
        this.mStrengthColor4 = color4;

        Drawable backgroundDr = new ColorDrawable(noStrengthColor);

        //color layers for password bar 1
        Drawable progressDr1 = new ScaleDrawable(new ColorDrawable(color1), Gravity.LEFT, 1, -1);
        LayerDrawable resultDr1 = new LayerDrawable(new Drawable[] { backgroundDr, progressDr1 });
        pb1.setProgressDrawable(resultDr1);

        //color layers for password bar 2
        Drawable progressDr2 = new ScaleDrawable(new ColorDrawable(color2), Gravity.LEFT, 1, -1);
        LayerDrawable resultDr2 = new LayerDrawable(new Drawable[] { backgroundDr, progressDr2 });
        pb2.setProgressDrawable(resultDr2);

        //color layers for password bar 3
        Drawable progressDr3 = new ScaleDrawable(new ColorDrawable(color3), Gravity.LEFT, 1, -1);
        LayerDrawable resultDr3 = new LayerDrawable(new Drawable[] { backgroundDr, progressDr3 });
        pb3.setProgressDrawable(resultDr3);

        //color layers for password bar 4
        Drawable progressDr4 = new ScaleDrawable(new ColorDrawable(color4), Gravity.LEFT, 1, -1);
        LayerDrawable resultDr4 = new LayerDrawable(new Drawable[] { backgroundDr, progressDr4 });
        pb4.setProgressDrawable(resultDr4);

    }


    /**
     * Implement the below two methods to get the maximum
     * and minimum value to which the password strength can be calculated
     */
    public int getMaxStrength(){
        return mMax;
    }
    public int getMinStrength(){
        return mMin;
    }


    /**
     * implement the below two methods to set the maximum
     * and minimum value to which the password strength can
     * be calculated
     */
    public void setMaxStrength(int max) {
        this.mMax = max;
        if (!shouldUseCustomBars) {
            max /= 4;
            pb1.setMax(max);
            pb2.setMax(max);
            pb3.setMax(max);
            pb4.setMax(max);
        }
    }

    @SuppressLint("NewApi")
//    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setMinStrength(int min) {
        this.mMin = min;
        if (!shouldUseCustomBars) {
            min /= 4;
            pb1.setMin(min);
            pb2.setMin(min);
            pb3.setMin(min);
            pb4.setMin(min);
        }
    }


    /**
     * implement the below method to get the strength of the bar
     */
    public int getStrength(){
        if(!shouldUseCustomBars)//IF new implementation isn't in effect
            return pb1.getProgress()+pb2.getProgress()+pb3.getProgress()+pb4.getProgress() ;
        else
            return strength;
    }


    /**
     * Don't want complex ways to set the strength then use simple calculations
     * Below method to set the strength of the bar
     */
    public void setStrength(int strength){
        this.strength=strength;

        if(shouldUseCustomBars){
            this.invalidate();
            return;
        }

        if(strength <= mMin){
            //set all the progress bar to its minimum value
            pb1.setProgress(mMin/4);
            pb2.setProgress(mMin/4);
            pb3.setProgress(mMin/4);
            pb4.setProgress(mMin/4);
        }
        else if(strength >= mMax){
            //set all the progress bar to its maximum value
            pb1.setProgress(mMax/4);
            pb2.setProgress(mMax/4);
            pb3.setProgress(mMax/4);
            pb4.setProgress(mMax/4);
        }
        else{
            //set the progress bar accordingly
            if(strength-mMax/4 >= mMin/4 ){
                pb1.setProgress(mMax/4);
                pb2.setProgress(mMin);
                pb3.setProgress(mMin);
                pb4.setProgress(mMin);
                strength -= (mMax/4);
                if(strength-mMax/4 >= mMin/4 ){
                    pb2.setProgress(mMax/4);
                    pb3.setProgress(mMin);
                    pb4.setProgress(mMin);
                    strength -= (mMax/4);
                    if(strength-mMax/4 >= mMin/4 ){
                        pb3.setProgress(mMax/4);
                        pb4.setProgress(mMin);
                        strength -= (mMax/4);
                        if(strength-mMax/4 >= mMin/4 ){
                            pb4.setProgress(mMax/4);
                        }else{
                            pb4.setProgress(strength);
                        }
                    }else{
                        pb3.setProgress(strength);
                        pb4.setProgress(mMin);
                    }
                }else{
                    pb2.setProgress(strength);
                    pb3.setProgress(mMin);
                    pb4.setProgress(mMin);
                }
            }else{
                pb1.setProgress(strength);
                pb2.setProgress(mMin);
                pb3.setProgress(mMin);
                pb4.setProgress(mMin);
            }
        }
    }
}
