package com.example.sharelove.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.sharelove.R
import kotlinx.android.synthetic.main.activity_contact_details.*
import kotlinx.android.synthetic.main.activity_description.*
import kotlinx.android.synthetic.main.activity_description.userNameDes


class DescriptionActivity() : AppCompatActivity() {
        lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        toolbar = findViewById(R.id.toolbar8)
        var intent = intent
        val userName = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val type = intent.getStringExtra("type")

        userNameDes.text = "Name of the User: $userName"
        userDescription.text = "Description: $description"
        userTypeDes.text = "type of Donation: $type"

        yes.setOnClickListener {
            val intent = Intent(this, ContactDetails::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
            val builder = NotificationCompat.Builder(this, "CHANNEL_ID")
                .setSmallIcon(R.drawable.yes_foreground)
                .setContentTitle("Someone Wants to Receive Your Donation")
                .setContentText("Click to get details")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(this)) {
                notify(0, builder.build())
                createNotificationChannel()
            }
        }
        //nooooooooooooooooooooooo
        no.setOnClickListener {

            val intent = Intent(this, ContactDetails::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

            val builder = NotificationCompat.Builder(this, "CHANNEL_ID")
                .setSmallIcon(R.drawable.yes_foreground)
                .setContentTitle("Someone Has Cancelled Your Donation")
                .setContentText("Click to get details")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(this)) {
                notify(0, builder.build())
                createNotificationChannel()
            }
        }
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Description of Donation"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("CHANNEL_ID", name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }

}

