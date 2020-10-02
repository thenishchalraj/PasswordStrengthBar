package com.android.thenishchalraj.passwordstrengthbar

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.thenishchalraj.passwordstrength.PasswordStrengthBar

class MainActivity : AppCompatActivity() {
    private lateinit var passwordStrengthBar: PasswordStrengthBar
    private lateinit var passwordField: EditText
    private lateinit var check: TextView
    private lateinit var see: Button
    private var mColor1 = 0
    private var mColor2 = 0
    private var mColor3 = 0
    private var mColor4 = 0
    private var shown = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mColor1 = ContextCompat.getColor(this, R.color.colorRed)
        mColor2 = ContextCompat.getColor(this, R.color.colorOrange)
        mColor3 = ContextCompat.getColor(this, R.color.colorLightGreen)
        mColor4 = ContextCompat.getColor(this, R.color.colorDarkGreen)
        passwordStrengthBar = findViewById(R.id.passwordBarCheck)
        passwordField = findViewById(R.id.passwordFieldCheck)
        check = findViewById(R.id.strengthText)
        see = findViewById(R.id.visibilityButton)
        passwordStrengthBar.setStrengthColor(Color.LTGRAY, mColor1, mColor2, mColor3, mColor4)
        passwordField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                calculation(s.toString())
                see.visibility = if (s.isNotBlank()) View.VISIBLE else View.GONE
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        see.setOnClickListener(View.OnClickListener {
            //this hole onclick func make password visible for 1.5second only
            passwordField.transformationMethod = null
            shown = true
            see.setText(R.string.hide_password)
            passwordField.setSelection(passwordField.text.length) //this helps cursor to be at last position even if password is seen by user
            Log.d("TAG", "done hiding ")
            val stop = Thread {
                try {
                    Thread.sleep(1500)
                    runOnUiThread {
                        passwordField.transformationMethod = AsteriskPasswordTransformationMethod()
                        shown = false
                        see.setText(R.string.see_password)
                        passwordField.setSelection(passwordField.text.length) //this helps cursor to be at last position even if password is seen by user
                        Log.d("TAG", "done seeing ")
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            stop.start()
        })
    }

    //the below method calculates the strength of the password and this can be different for different applications
    @SuppressLint("ResourceAsColor")
    private fun calculation(data: String) {
        var length = 0
        var uppercase = 0
        var lowercase = 0
        var digits = 0
        var symbols = 0
        var bonus = 0
        var requirements = 0
        var lettersOnly = 0
        var numbersOnly = 0
        var cuc = 0
        var clc = 0
        length = data.length
        for (i in data.indices) {
            if (Character.isUpperCase(data[i])) uppercase++ else if (Character.isLowerCase(data[i])) lowercase++ else if (Character.isDigit(data[i])) digits++
            symbols = length - uppercase - lowercase - digits
        }
        for (j in 1 until data.length - 1) {
            if (Character.isDigit(data[j])) bonus++
        }
        var k = 0
        while (k < data.length) {
            if (Character.isUpperCase(data[k])) {
                k++
                if (k < data.length) {
                    if (Character.isUpperCase(data[k])) {
                        cuc++
                        k--
                    }
                }
            }
            k++
        }
        var l = 0
        while (l < data.length) {
            if (Character.isLowerCase(data[l])) {
                l++
                if (l < data.length) {
                    if (Character.isLowerCase(data[l])) {
                        clc++
                        l--
                    }
                }
            }
            l++
        }
        if (length > 7) {
            requirements++
        }
        if (uppercase > 0) {
            requirements++
        }
        if (lowercase > 0) {
            requirements++
        }
        if (digits > 0) {
            requirements++
        }
        if (symbols > 0) {
            requirements++
        }
        if (bonus > 0) {
            requirements++
        }
        if (digits == 0 && symbols == 0) {
            lettersOnly = 1
        }
        if (lowercase == 0 && uppercase == 0 && symbols == 0) {
            numbersOnly = 1
        }
        val total = ((length * 4 + (length - uppercase) * 2
                + (length - lowercase) * 2 + digits * 4 + symbols * 6
                + bonus * 2 + requirements * 2) - lettersOnly * length * 2
                - numbersOnly * length * 3 - cuc * 2 - clc * 2)
        when {
            total in 1..50 -> {
                check.setText(R.string.bad)
                passwordStrengthBar.strength = total / 2
            }
            total in 51..70 -> {
                check.setText(R.string.average)
                passwordStrengthBar.strength = total * 5 / 7
            }
            total in 71..80 -> {
                check.setText(R.string.good)
                passwordStrengthBar.strength = total * 7 / 8
            }
            total >= 81 -> {
                check.setText(R.string.best)
                passwordStrengthBar.strength = total
            }
            else -> {
                check.text = ""
                passwordStrengthBar.strength = passwordStrengthBar.minStrength
            }
        }
    }
}

internal class AsteriskPasswordTransformationMethod : PasswordTransformationMethod() {
    //this class helps to display password in asterisk(*) format
    override fun getTransformation(source: CharSequence, view: View): CharSequence {
        return PasswordCharSequence(source)
    }

    private class PasswordCharSequence(private val mSource: CharSequence) : CharSequence {

        override val length: Int
            get() = mSource.length

        override fun get(index: Int) = '*'

        override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
            return mSource.subSequence(startIndex, endIndex)
        }
    }
}