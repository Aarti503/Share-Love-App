package com.example.sharelove.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.example.sharelove.DatabaseHandler
import com.example.sharelove.R

class LoginFragment : Fragment() {
    lateinit var btnLogin: Button
    private lateinit var username: EditText
    lateinit var password: EditText
    private lateinit var newuser: TextView
    private lateinit var newusersignin: TextView
    var awesomeValidation: AwesomeValidation? = null
    lateinit var databaseHandler: DatabaseHandler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        newuser = view.findViewById(R.id.newUser)
        username = view.findViewById(R.id.usernameLogin)
        password = view.findViewById(R.id.passwordLogin)
        newusersignin = view.findViewById(R.id.newUserSignIn)
        btnLogin = view.findViewById(R.id.btnLogin)

        val context = this.context
        databaseHandler = DatabaseHandler(context)
        awesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        var title = "Login"
        btnLogin.setOnClickListener {
            if (validateUser()) {
                Toast.makeText(
                    activity,
                    "Login Successful",
                    Toast.LENGTH_LONG
                ).show()
                val secondFragment = WantAsFragment()
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.frameLayout, secondFragment)
                transaction?.addToBackStack(null)
                transaction?.commit()
            } else {
                Toast.makeText(
                    activity,
                    "Invalid Credentials",
                    Toast.LENGTH_LONG
                ).show()
            }

        }
        newusersignin.setOnClickListener {
            Toast.makeText(
                activity,
                "Register Successful",
                Toast.LENGTH_LONG
            ).show()
            val secondFragment = SignUpFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.frameLayout, secondFragment)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }

        return view
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



