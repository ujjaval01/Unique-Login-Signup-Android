package com.example.uniqueloginandsignup

import android.os.Bundle
import android.text.InputType
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private var isMobileLogin = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val etEmailOrMobile = findViewById<TextInputEditText>(R.id.etEmailOrMobile)
        val tvLoginWithMobile = findViewById<TextView>(R.id.tvLoginWithMobile)

        tvLoginWithMobile.setOnClickListener {
            if (!isMobileLogin) {
                // Email -> Mobile me change
                etEmailOrMobile.hint = "Enter your mobile number"
                etEmailOrMobile.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.baseline_lock_24, 0, 0, 0
                )
                etEmailOrMobile.inputType =
                    InputType.TYPE_CLASS_PHONE // mobile ke liye number pad
                tvLoginWithMobile.text = "Login with Email"
                isMobileLogin = true
            } else {
                // Mobile -> Email me wapas change
                etEmailOrMobile.hint = "Enter your email"
                etEmailOrMobile.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.baseline_email_24, 0, 0, 0
                )
                etEmailOrMobile.inputType =
                    InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                tvLoginWithMobile.text = "Login with Mobile"
                isMobileLogin = false
            }
        }
    }

}