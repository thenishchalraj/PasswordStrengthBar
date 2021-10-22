package com.android.thenishchalraj.passwordstrength

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ScaleDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.password_strength_bar.view.*

/**
 * Created by thenishchalraj on 10.10.2018.
 */
class PasswordStrengthBar: ConstraintLayout {

    private var mInflater: LayoutInflater? = null

    //Attributes
    private var mMax = 100
    private var mMin = 0
    private var mNoStrengthColor = Color.LTGRAY
    private var mStrengthColor1 = Color.RED
    private var mStrengthColor2 = Color.YELLOW
    private var mStrengthColor3 = Color.GREEN
    private var mStrengthColor4 = Color.DKGRAY

    constructor(context: Context) : super(context, null) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    /**initiate views */
    private fun init(context: Context?) {

        // initiate inflater
        mInflater = LayoutInflater.from(context)

        // load view from xml
        val view =
            mInflater?.inflate(R.layout.password_strength_bar, this, true)

        maxStrength = mMax
        minStrength = mMin
    }

    /**
     * Implement the below method to set the password strength to default chosen color
     */
    fun setStrengthColor(noStrengthColor: Int, color1: Int, color2: Int, color3: Int, color4: Int) {
        mNoStrengthColor = noStrengthColor
        mStrengthColor1 = color1
        mStrengthColor2 = color2
        mStrengthColor3 = color3
        mStrengthColor4 = color4
        val backgroundDr: Drawable = ColorDrawable(noStrengthColor)

        //color layers for password bar 1
        val progressDr1: Drawable = ScaleDrawable(ColorDrawable(color1), Gravity.LEFT, 1F, -1F)
        val resultDr1 = LayerDrawable(arrayOf(backgroundDr, progressDr1))
        pBar1.progressDrawable = resultDr1

        //color layers for password bar 2
        val progressDr2: Drawable = ScaleDrawable(ColorDrawable(color2), Gravity.LEFT, 1F, -1F)
        val resultDr2 = LayerDrawable(arrayOf(backgroundDr, progressDr2))
        pBar2.progressDrawable = resultDr2

        //color layers for password bar 3
        val progressDr3: Drawable = ScaleDrawable(ColorDrawable(color3), Gravity.LEFT, 1F, -1F)
        val resultDr3 = LayerDrawable(arrayOf(backgroundDr, progressDr3))
        pBar3.progressDrawable = resultDr3

        //color layers for password bar 4
        val progressDr4: Drawable = ScaleDrawable(ColorDrawable(color4), Gravity.LEFT, 1F, -1F)
        val resultDr4 = LayerDrawable(arrayOf(backgroundDr, progressDr4))
        pBar4.progressDrawable = resultDr4
    }
    /**
     * Implement the below two methods to get the maximum
     * and minimum value to which the password strength can be calculated
     */
    /**
     * implement the below two methods to set the maximum
     * and minimum value to which the password strength can
     * be calculated
     */
    private var maxStrength: Int
        get() = mMax
        set(max) {
            var max = max
            mMax = max
            max /= 4
            pBar1.max = max
            pBar2.max = max
            pBar3.max = max
            pBar4.max = max
        }

    @set:RequiresApi(api = Build.VERSION_CODES.O)
    @set:SuppressLint("NewApi")
    var minStrength: Int
        get() = mMin
        set(min) {
            var min = min
            mMin = min
            min /= 4
            pBar1.min = min
            pBar2.min = min
            pBar3.min = min
            pBar4.min = min
        }
    /**
     * implement the below method to get the strength of the bar
     *///set the progress bar accordingly//set all the progress bar to its maximum value//set all the progress bar to its minimum value
    /**
     * Don't want complex ways to set the strength then use simple calculations
     * Below method to set the strength of the bar
     */
    var strength: Int
        get() = pBar1.progress + pBar2.progress + pBar3.progress + pBar4.progress
        set(strength) {
            var strength = strength
            if (strength <= mMin) {
                //set all the progress bar to its minimum value
                pBar1.progress = mMin / 4
                pBar2.progress = mMin / 4
                pBar3.progress = mMin / 4
                pBar4.progress = mMin / 4
            } else if (strength >= mMax) {
                //set all the progress bar to its maximum value
                pBar1.progress = mMax / 4
                pBar2.progress = mMax / 4
                pBar3.progress = mMax / 4
                pBar4.progress = mMax / 4
            } else {
                //set the progress bar accordingly
                if (strength - mMax / 4 >= mMin / 4) {
                    pBar1.progress = mMax / 4
                    pBar2.progress = mMin
                    pBar3.progress = mMin
                    pBar4.progress = mMin
                    strength -= mMax / 4
                    if (strength - mMax / 4 >= mMin / 4) {
                        pBar2.progress = mMax / 4
                        pBar3.progress = mMin
                        pBar4.progress = mMin
                        strength -= mMax / 4
                        if (strength - mMax / 4 >= mMin / 4) {
                            pBar3.progress = mMax / 4
                            pBar4.progress = mMin
                            strength -= mMax / 4
                            if (strength - mMax / 4 >= mMin / 4) {
                                pBar4.progress = mMax / 4
                            } else {
                                pBar4.progress = strength
                            }
                        } else {
                            pBar3.progress = strength
                            pBar4.progress = mMin
                        }
                    } else {
                        pBar2.progress = strength
                        pBar3.progress = mMin
                        pBar4.progress = mMin
                    }
                } else {
                    pBar1.progress = strength
                    pBar2.progress = mMin
                    pBar3.progress = mMin
                    pBar4.progress = mMin
                }
            }
        }
}