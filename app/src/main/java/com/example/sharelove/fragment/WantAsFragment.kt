package com.example.sharelove.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sharelove.R
import com.example.sharelove.activity.LoginMainActivity
import com.example.sharelove.activity.ReceiverMainActivity

class WantAsFragment : Fragment() {
    lateinit var buttonD: Button
    lateinit var buttonR: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_want_as, container, false)
        buttonD = view.findViewById(R.id.evButtonD)
        buttonR = view.findViewById(R.id.evButtonR)
        buttonD.setOnClickListener {
            Toast.makeText(
                activity,
                "Login As Donator SuccessFul",
                Toast.LENGTH_LONG
            ).show()
            val intent = Intent(
                activity,
                LoginMainActivity::class.java
            )
            startActivity(intent)
        }
        buttonR.setOnClickListener {
            Toast.makeText(
                activity,
                "Login As Receiver SuccessFul",
                Toast.LENGTH_LONG
            ).show()
            val intent = Intent(
                activity,
                ReceiverMainActivity::class.java
            )
            startActivity(intent)
        }

        return view
    }

}