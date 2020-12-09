package com.example.sharelove.activity

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.sharelove.DatabaseHandler
import com.example.sharelove.R
import com.example.sharelove.model.Model
import com.example.sharelove.model.MyListAdapterReceiver
import kotlinx.android.synthetic.main.activity_receiver_main.*


class ReceiverMainActivity : AppCompatActivity() {

    lateinit var btnViewDetails: Button
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiver_main)
        toolbar = findViewById(R.id.toolbar3)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Receiving Donations"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        val lv = findViewById<ListView>(R.id.mListView)
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        val donation: List<Model.Donation> = databaseHandler.viewDonations()
        val donationArrayId = Array<String>(donation.size) { "0" }
        val donationArrayName = Array<String>(donation.size) { "null" }
        val donationArrayType = Array<String>(donation.size) { "null" }
        val donationArrayDescription = Array<String>(donation.size) { "null" }
        var index = 0
        for (d in donation) {
            donationArrayId[index] = d.donationId.toString()
            donationArrayName[index] = d.donatorName
            donationArrayType[index] = d.donationType
            donationArrayDescription[index] = d.donationDescription
            index++
        }
        val myListAdapter = MyListAdapterReceiver(
            this, donationArrayId,
            donationArrayName,
            donationArrayType,
            donationArrayDescription
        )
        mListView.adapter = myListAdapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}