package com.example.uniqueloginandsignup

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.InputType
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private var isMobileLogin = false
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        val signInBtn = findViewById<MaterialButton>(R.id.signInBtn)

        signInBtn.setOnClickListener {
            val emailOrMobile = findViewById<TextInputEditText>(R.id.etEmailOrMobile).text.toString().trim()
            val etPassword = findViewById<TextInputEditText>(R.id.etPassword).text.toString().trim()

            if(emailOrMobile.isEmpty() || etPassword.isEmpty()){
                Toast.makeText(this,"Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
            else {
                auth.signInWithEmailAndPassword(emailOrMobile, etPassword)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Sign In Successful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Sign In Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }




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
            }, 700)

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