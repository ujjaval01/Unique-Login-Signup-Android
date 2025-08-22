package com.example.uniqueloginandsignup

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.InputType
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private var isMobileLogin = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val etEmailOrMobile = findViewById<TextInputEditText>(R.id.etEmailOrMobile)
        val tvLoginWithMobile = findViewById<TextView>(R.id.tvLoginWithMobile)

        val signUpPage = findViewById<MaterialButton>(R.id.signUpPage)
        signUpPage.setOnClickListener {
            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Please wait a sec...")
            progressDialog.setCancelable(false) // back press se cancel na ho
            progressDialog.show()

            Handler(Looper.getMainLooper()).postDelayed({
                progressDialog.dismiss()

                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
                finish()
            }, 1000) // 2 second delay

        }


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