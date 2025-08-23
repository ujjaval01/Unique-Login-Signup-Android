package com.example.uniqueloginandsignup

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()
        val signUpBtn = findViewById<MaterialButton>(R.id.signUpBtn)

        signUpBtn.setOnClickListener {
            val email = findViewById<TextInputEditText>(R.id.etEmail).text.toString().trim()
            val name = findViewById<TextInputEditText>(R.id.etName).text.toString().trim()
            val phoneNumber = findViewById<TextInputEditText>(R.id.etPhoneNumber).text.toString().trim()
            val password = findViewById<TextInputEditText>(R.id.etPassword).text.toString().trim()
            val cPassword = findViewById<TextInputEditText>(R.id.etCPassword).text.toString().trim()

            if(email.isEmpty() || name.isEmpty() || phoneNumber.isEmpty()){
                Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show()
            }else if(!(email.endsWith("@gmail.com"))){
                Toast.makeText(this,"Please enter a valid email",Toast.LENGTH_SHORT).show()
            }else if(phoneNumber.length != 10){
                Toast.makeText(this,"Please enter a valid phone number",Toast.LENGTH_SHORT).show()
            }
            else if(password!=cPassword){
                Toast.makeText(this,"Password does not match",Toast.LENGTH_SHORT).show()
            }
            else{
                auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this){ task ->
                        if(task.isSuccessful){
                            Toast.makeText(this,"Sign Up Successful",Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this,"Sign Up Failed: ${task.exception?.message}",Toast.LENGTH_SHORT).show()
                        }
                    }
            }



        }



        val signInPage = findViewById<MaterialButton>(R.id.signInPage)

        signInPage.setOnClickListener {
            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Please wait a sec...")
            progressDialog.setCancelable(false)
            progressDialog.show()
            Handler(Looper.getMainLooper()).postDelayed({
                progressDialog.dismiss()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }, 700)

        }

    }
}