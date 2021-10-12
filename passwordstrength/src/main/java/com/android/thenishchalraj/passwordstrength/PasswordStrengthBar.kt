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
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.annotation.RequiresApi

/**
 * Created by thenishchalraj on 10.10.2018.
 */
open class PasswordStrengthBar : LinearLayout {
    //UI
    private var plb: LinearLayout? = null
    private var pb1: ProgressBar? = null
    private var pb2: ProgressBar? = null
    private var pb3: ProgressBar? = null
    private var pb4: ProgressBar? = null
    private var mInflater: LayoutInflater? = null

    //Attributes
    private var mMax = 100
    private var mMin = 0
    private var mNoStrengthColor = Color.LTGRAY
    private var mStrengthColor1 = Color.RED
    private var mStrengthColor2 = Color.YELLOW
    private var mStrengthColor3 = Color.GREEN
    private var mStrengthColor4 = Color.DKGRAY

    constructor(context: Context?) : super(context, null) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs, 0) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    /**initiate views */
    private fun init(context: Context?) {
        mInflater = LayoutInflater.from(context)

        //load view from xml
        val view = mInflater?.inflate(R.layout.password_strength_bar, this, true)

        //init UI
        plb = view?.findViewById(R.id.progressLinearBar)
        pb1 = view?.findViewById(R.id.pBar1)
        pb2 = view?.findViewById(R.id.pBar2)
        pb3 = view?.findViewById(R.id.pBar3)
        pb4 = view?.findViewById(R.id.pBar4)
        maxStrength = mMax
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            minStrength = mMin
        }
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
        pb1!!.progressDrawable = resultDr1

        //color layers for password bar 2
        val progressDr2: Drawable = ScaleDrawable(ColorDrawable(color2), Gravity.LEFT, 1F, -1F)
        val resultDr2 = LayerDrawable(arrayOf(backgroundDr, progressDr2))
        pb2!!.progressDrawable = resultDr2

        //color layers for password bar 3
        val progressDr3: Drawable = ScaleDrawable(ColorDrawable(color3), Gravity.LEFT, 1F, -1F)
        val resultDr3 = LayerDrawable(arrayOf(backgroundDr, progressDr3))
        pb3!!.progressDrawable = resultDr3

        //color layers for password bar 4
        val progressDr4: Drawable = ScaleDrawable(ColorDrawable(color4), Gravity.LEFT, 1F, -1F)
        val resultDr4 = LayerDrawable(arrayOf(backgroundDr, progressDr4))
        pb4!!.progressDrawable = resultDr4
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
    var maxStrength: Int
        get() = mMax
        set(max) {
            var max = max
            mMax = max
            max /= 4
            pb1!!.max = max
            pb2!!.max = max
            pb3!!.max = max
            pb4!!.max = max
        }

    @set:RequiresApi(api = Build.VERSION_CODES.O)
    @set:SuppressLint("NewApi")
    var minStrength: Int
        get() = mMin
        set(min) {
            var min = min
            mMin = min
            min /= 4
            pb1!!.min = min
            pb2!!.min = min
            pb3!!.min = min
            pb4!!.min = min
        }
    /**
     * implement the below method to get the strength of the bar
     *///set the progress bar accordingly//set all the progress bar to its maximum value//set all the progress bar to its minimum value
    /**
     * Don't want complex ways to set the strength then use simple calculations
     * Below method to set the strength of the bar
     */
    var strength: Int
        get() = pb1!!.progress + pb2!!.progress + pb3!!.progress + pb4!!.progress
        set(strength) {
            var strength = strength
            if (strength <= mMin) {
                //set all the progress bar to its minimum value
                pb1!!.progress = mMin / 4
                pb2!!.progress = mMin / 4
                pb3!!.progress = mMin / 4
                pb4!!.progress = mMin / 4
            } else if (strength >= mMax) {
                //set all the progress bar to its maximum value
                pb1!!.progress = mMax / 4
                pb2!!.progress = mMax / 4
                pb3!!.progress = mMax / 4
                pb4!!.progress = mMax / 4
            } else {
                //set the progress bar accordingly
                if (strength - mMax / 4 >= mMin / 4) {
                    pb1!!.progress = mMax / 4
                    pb2!!.progress = mMin
                    pb3!!.progress = mMin
                    pb4!!.progress = mMin
                    strength -= mMax / 4
                    if (strength - mMax / 4 >= mMin / 4) {
                        pb2!!.progress = mMax / 4
                        pb3!!.progress = mMin
                        pb4!!.progress = mMin
                        strength -= mMax / 4
                        if (strength - mMax / 4 >= mMin / 4) {
                            pb3!!.progress = mMax / 4
                            pb4!!.progress = mMin
                            strength -= mMax / 4
                            if (strength - mMax / 4 >= mMin / 4) {
                                pb4!!.progress = mMax / 4
                            } else {
                                pb4!!.progress = strength
                            }
                        } else {
                            pb3!!.progress = strength
                            pb4!!.progress = mMin
                        }
                    } else {
                        pb2!!.progress = strength
                        pb3!!.progress = mMin
                        pb4!!.progress = mMin
                    }
                } else {
                    pb1!!.progress = strength
                    pb2!!.progress = mMin
                    pb3!!.progress = mMin
                    pb4!!.progress = mMin
                }
            }
        }
}