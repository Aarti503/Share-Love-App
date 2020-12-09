package com.example.sharelove.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.sharelove.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_contact_details.*
import kotlinx.android.synthetic.main.activity_description.*
import kotlinx.android.synthetic.main.activity_description.userNameDes

class ContactDetails : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var button_call: FloatingActionButton
    internal var number: String? = "9999999999"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)
        button_call = findViewById(R.id.button_call)
        toolbar = findViewById(R.id.toolbar5)
        
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Contact Details"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        button_call.setOnClickListener {
            number = number.toString().trim()
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(number)))
            startActivity(intent)
        }
    }
            override fun onSupportNavigateUp(): Boolean {
                onBackPressed()
                return true
    }
}