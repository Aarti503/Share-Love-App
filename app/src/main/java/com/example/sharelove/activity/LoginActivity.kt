package com.example.sharelove.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.sharelove.DatabaseHandler
import com.example.sharelove.R
import com.google.android.material.snackbar.Snackbar
import im.dino.dbinspector.helpers.DatabaseHelper
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*

class LoginActivity : AppCompatActivity() {

    lateinit var btnLogIn: Button
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var newuser: TextView
    lateinit var newusersignin: TextView
    lateinit var userType:EditText
    lateinit var databaseHandler: DatabaseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val context = this
        databaseHandler = DatabaseHandler(context)
        userType = findViewById(R.id.signingUpAsUser)
        btnLogIn = findViewById(R.id.btnLog)
        username = findViewById<EditText>(R.id.usernameLog)
        password = findViewById<EditText>(R.id.passwordLog)
        newuser = findViewById(R.id.newuser)
        newusersignin = findViewById(R.id.newusersignin)
        val data = databaseHandler.viewUser()
        var userType = signingUpAsUser.text.toString()
        title = "Login"
        btnLogIn.setOnClickListener {
           if(validateUser()){
               Toast.makeText(
                   this@LoginActivity,
                   "Login Successful",
                   Toast.LENGTH_LONG
               ).show()
           }else{
               Toast.makeText(
                   this@LoginActivity,
                   "Invalid User",
                   Toast.LENGTH_LONG
               ).show()
           }

        }

        newusersignin.setOnClickListener {
            Toast.makeText(
                this@LoginActivity,
                "login Is Clicked",
                Toast.LENGTH_LONG
            )
                .show()
            val intent = Intent(
                this@LoginActivity,
                SignupActivity::class.java
            )
            startActivity(intent)
        }
    }

      fun validateUser(): Boolean {
        var phoneEntered = username.text.toString()
        var passwordEntered = password.text.toString().trim()
        if (databaseHandler.checkUserData("$phoneEntered", "$passwordEntered")) {
            return false;
        } else if (phoneEntered.isEmpty()) {
            username.error = "Field can't be empty"
            return false;
        } else if (passwordEntered.isEmpty()) {
            password.error = "Field can't be empty"
            return false;
        } else {
            return true;
        }
    }
}
