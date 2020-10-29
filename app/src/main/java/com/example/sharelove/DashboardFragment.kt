package com.example.sharelove

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.sharelove.activity.LoginActivity
import com.example.sharelove.activity.SignupActivity
import kotlinx.android.synthetic.main.fragment_dashboard.view.*

class DashboardFragment : Fragment() {
    lateinit var btnLogin: Button
    lateinit var register: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        btnLogin = view.findViewById(R.id.btnLogin)
        register =view.findViewById(R.id.txtSubregister)

     view.btnLogin.setOnClickListener {

            val intent = Intent(
                activity,
                LoginActivity::class.java
            )
            startActivity(intent)

        }
        register.setOnClickListener {
            val intent = Intent(
                activity,
                SignupActivity::class.java
            )
            startActivity(intent)
        }

        return view
    }
}