package com.example.sharelove.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sharelove.R

class DashboardFragment : Fragment() {
    private lateinit var btnLogin: Button
    private lateinit var register: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        btnLogin = view.findViewById(R.id.btnLoginDashBoard)
        register = view.findViewById(R.id.txtSubregisterDashBoard)
        btnLogin.setOnClickListener {
            Toast.makeText(
                activity,
                "Login Now Is Clicked",
                Toast.LENGTH_LONG
            )
                .show()
            val secondFragment = LoginFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.frameLayout, secondFragment)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }
        register.setOnClickListener {
            Toast.makeText(
                activity,
                "Register Now Is Clicked",
                Toast.LENGTH_LONG
            )
                .show()
            val secondFragment = SignUpFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.frameLayout, secondFragment)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

}