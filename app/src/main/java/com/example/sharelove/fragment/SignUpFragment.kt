package com.example.sharelove.fragment

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.example.sharelove.DatabaseHandler
import com.example.sharelove.R
import com.example.sharelove.model.Model
import java.util.regex.Pattern

class SignUpFragment : Fragment() {
    lateinit var btnSign: Button
    lateinit var name: EditText
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var phoneNumber: EditText
    lateinit var userType: EditText
    lateinit var address: EditText
    lateinit var city: EditText
    lateinit var state: EditText
    lateinit var country: EditText
    lateinit var pincode: EditText

    //defining AwesomeValidation object
    var awesomeValidation: AwesomeValidation? = null
    lateinit var databaseHandler: DatabaseHandler


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        databaseHandler = DatabaseHandler(this.context)
        btnSign = view.findViewById(R.id.signin)
        name = view.findViewById(R.id.name)
        email = view.findViewById(R.id.emailId)
        password = view.findViewById(R.id.passwordEmail)
        phoneNumber = view.findViewById(R.id.phoneNumber)
        userType = view.findViewById(R.id.signingUpAsUser)
        address = view.findViewById(R.id.address2)
        city = view.findViewById(R.id.address3)
        state = view.findViewById(R.id.address4)
        country = view.findViewById(R.id.address5)
        pincode = view.findViewById(R.id.address6)
        awesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
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

        fun submitForm() {
            if (awesomeValidation!!.validate()) {
                addUser()
                val secondFragment = LoginFragment()
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.frameLayout, secondFragment)
                transaction?.addToBackStack(null)
                transaction?.commit()
            }
        }
        btnSign.setOnClickListener {
            if (validateEmail() && validatePhone() && userTypeValidate()) {
                Toast.makeText(
                    activity,
                    "User Added",
                    Toast.LENGTH_LONG
                ).show()
                submitForm()
            } else {
                Toast.makeText(
                    activity,
                    "Not Added",
                    Toast.LENGTH_LONG
                ).show()

            }

        }
        return view
    }

    private fun userTypeValidate(): Boolean {
        var userTypeValid = userType.getText().toString().trim().toLowerCase()
        if (userTypeValid == "donator") {
            userType.error = null;
            return true;
        } else if (userTypeValid == "receiver") {
            userType.error = null;
            return true;
        } else {
            userType.error = "Enter either Donator or Receiver"
            return false;
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
        val databaseHandler: DatabaseHandler =
            DatabaseHandler(context)
        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !phoneNumber.isEmpty()
            && !userType.isEmpty() && !address.isEmpty() && !city.isEmpty() && !state.isEmpty() && !country.isEmpty() && !pincode.isEmpty()
        ) {
            val status =
                databaseHandler.addUser(
                    Model.UserClass(
                        0,
                        name,
                        email,
                        password,
                        phoneNumber,
                        userType,
                        address,
                        city,
                        state,
                        country,
                        pincode
                    )
                )
            if (status > -1) {
                Toast.makeText(context, "Record saved", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(
                context,
                "Please Fill Every Field",
                Toast.LENGTH_LONG
            ).show()
        }

    }

}

private fun AwesomeValidation.addValidation(
    signUpFragment: SignUpFragment,
    signingUpAsUser: Int,
    userTypeValidate: Boolean,
    donarec: Int
) {

}

private fun AwesomeValidation.addValidation(
    signUpFragment: SignUpFragment,
    emailId: Int,
    emailAddress: Pattern?,
    emailerror: Int
) {

}

private fun AwesomeValidation.addValidation(
    signUpFragment: SignUpFragment,
    name: Int,
    s: String,
    nameerror: Int
) {

}

private fun AwesomeValidation.addValidation(
    signupActivity: SignUpFragment,
    signingUpAsUser: Int,
    match: Unit,
    donarec: Int
) {

}
