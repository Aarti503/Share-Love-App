package com.example.sharelove.activity

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Patterns
import android.util.Range
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.example.sharelove.DatabaseHandler
import com.example.sharelove.R
import com.example.sharelove.UserClass
import com.google.common.collect.Range.closed

class SignupActivity : AppCompatActivity() {
    lateinit var btnSign: Button
    lateinit var name: EditText
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var phoneNumber: EditText
    lateinit var userType: EditText
    lateinit var address:EditText
    lateinit var city: EditText
    lateinit var state: EditText
    lateinit var country: EditText
    lateinit var pincode: EditText

    //defining AwesomeValidation object
    var awesomeValidation: AwesomeValidation? = null
    lateinit var databaseHandler: DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val context = this
        databaseHandler = DatabaseHandler(context)
        title = "Login"
        btnSign = findViewById(R.id.signin)
        name = findViewById(R.id.name)
        email = findViewById(R.id.emailId)
        password = findViewById(R.id.passwordEmail)
        phoneNumber = findViewById(R.id.phoneNumber)
        userType = findViewById(R.id.signingUpAsUser)
        address = findViewById(R.id.address2)
        city = findViewById(R.id.address3)
        state = findViewById(R.id.address4)
        country = findViewById(R.id.address5)
        pincode = findViewById(R.id.address6)
        awesomeValidation = AwesomeValidation(ValidationStyle.BASIC)


        //adding validation to edittexts
        awesomeValidation!!.addValidation(
            this,
            R.id.name,
            "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",
            R.string.nameerror
        )
        awesomeValidation!!.addValidation(
            this,
            R.id.emailId,
            Patterns.EMAIL_ADDRESS,
            R.string.emailerror
        )
        awesomeValidation!!.addValidation(
            this,
            R.id.phoneNumber,
            "^\\d{10}$",
            R.string.mobileerror
        )
        awesomeValidation!!.addValidation(
            this,
            R.id.signingUpAsUser,
            match(),
            R.string.donarec
        )


        fun submitForm() {
            if (awesomeValidation!!.validate()) {
                Toast.makeText(this, "Validation Successfull", Toast.LENGTH_LONG).show();
                addUser()

                val intent = Intent(
                    this@SignupActivity,
                    AddedAsSignUp::class.java
                )
                startActivity(intent)
            }
        }
        btnSign.setOnClickListener {
            if (validateEmail() && validatePhone()) {
                Toast.makeText(
                    this@SignupActivity,
                    "User Added",
                    Toast.LENGTH_LONG
                ).show()
                submitForm()
            } else {
                Toast.makeText(
                    this@SignupActivity,
                    "Not Added",
                    Toast.LENGTH_LONG
                ).show()

            }

        }

    }
    fun validateEmail(): Boolean {
         var emailInput = email.getText().toString().trim()
         if (databaseHandler.checkUserEmail("$emailInput")) {
             email.error = "Email already exist"
             return false;
         } else if (emailInput.isEmpty()) {
             email.error = "Field can't be empty"
             return false;
         } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
             email.error = "Please enter a valid email address"
             return false;
         } else {
             email.error = null;
             return true;
         }
     }
    fun validatePhone(): Boolean {
        var phoneInput = phoneNumber.getText().toString().trim()
        if (databaseHandler.checkUserPhone("$phoneInput")) {
            phoneNumber.error = "Phone Number already exist"
            return false;
        } else if (phoneInput.isEmpty()) {
            phoneNumber.error = "Field can't be empty"
            return false;
        } else {
            phoneNumber.error = null;
            return true;
        }
    }

    fun match() {
        val usernamePattern = "[a-zA-Z0-9]+"
        val regex = Regex(usernamePattern)
        regex matches ("Donator")
        regex.matches("Receiver")
    }

    private fun addUser() {
        val name = name.text.toString()
        val email = email.text.toString()
        val password = password.text.toString()
        val phoneNumber = phoneNumber.text.toString()
        val userType = userType.text.toString()
        val address = address.text.toString()
        val city = city.text.toString()
        val state = state.text.toString()
        val country = country.text.toString()
        val pincode = pincode.text.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !phoneNumber.isEmpty()
            && !userType.isEmpty() && !address.isEmpty() && !city.isEmpty() && !state.isEmpty() && !country.isEmpty() && !pincode.isEmpty()) {
            val status =
                databaseHandler.addUser(UserClass(0, name, email, password, phoneNumber, userType,address,city,state,country,pincode))
            if (status > -1) {
                Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Please Fill Every Field",
                Toast.LENGTH_LONG
            ).show()
        }

    }
}

private fun AwesomeValidation.addValidation(signupActivity: SignupActivity, signingUpAsUser: Int, match: Unit, donarec: Int) {

}
