package com.example.sharelove.activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.sharelove.DatabaseHandler
import com.example.sharelove.R


class AddedAsSignUp : AppCompatActivity() {

    lateinit var btnAdd: Button
    lateinit var evButton1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_added_as_sign_up)
        val context = this
        val db = DatabaseHandler(context)
        btnAdd = findViewById(R.id.logBtn)
        title = "data"
        evButton1 = findViewById(R.id.evButton1)
        evButton1.setOnClickListener {
            Toast.makeText(
                this@AddedAsSignUp,
                "Submit Is Clicked",
                Toast.LENGTH_LONG
            )
                .show()
            val intent = Intent(
                this@AddedAsSignUp,
                MapsActivity::class.java
            )
            startActivity(intent)

        }
    }
    }
