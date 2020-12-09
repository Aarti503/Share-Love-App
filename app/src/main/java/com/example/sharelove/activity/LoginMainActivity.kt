package com.example.sharelove.activity

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.sharelove.DatabaseHandler
import com.example.sharelove.R
import com.example.sharelove.model.Model
import com.example.sharelove.model.MyListAdapter
import kotlinx.android.synthetic.main.activity_login_main.*


class LoginMainActivity() : AppCompatActivity() {

    lateinit var donationType: RadioGroup
    lateinit var radioButton: RadioGroup
    lateinit var radioFood: RadioButton
    lateinit var radioOthers: RadioButton
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_main)
        radioButton = findViewById<RadioGroup>(R.id.radioButton)
        toolbar = findViewById(R.id.toolbar2)
        radioFood = findViewById<RadioButton>(R.id.radio_food)
        radioOthers = findViewById<RadioButton>(R.id.radio_others)

        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Donations"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    //method for saving records in database
    fun saveRecord(view: View) {
        var result = ""
        if (radioButton.checkedRadioButtonId != 1) {
            result += " "
            if (radioFood.isChecked)
                result += "Food\n"
            else if (radioOthers.isChecked)
                result += "Others"
        }
        val donationId = edID.text.toString()
        val donatorName = edTitle.text.toString()
        val donationType = result.toString()
        val donationDescription = edDescription.text.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        if (donationId.trim() != "" && donatorName.trim() != "" && donationType.trim() != "" && donationDescription.trim() != "") {
            val status = databaseHandler.addDonation(
                Model.Donation(
                    donationId = Integer.parseInt(donationId),
                    donatorName = donatorName,
                    donationType = donationType,
                    donationDescription = donationDescription
                )
            )
            if (status > -1) {
                Toast.makeText(applicationContext, "Record save", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(applicationContext, "Please Fill Every Field", Toast.LENGTH_LONG).show()
        }

    }

    //method for read records from database in ListView
    fun viewRecord(view: View) {
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
        val myListAdapter = MyListAdapter(
            this,
            donationArrayId,
            donationArrayName,
            donationArrayType,
            donationArrayDescription
        )
        lvDonation.adapter = myListAdapter
    }

    //method for updating records based on user id
    fun updateRecord(view: View) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.updatedialog, null)
        dialogBuilder.setView(dialogView)
        val edtDonationId = dialogView.findViewById(R.id.updateId) as EditText
        val edtdonationName = dialogView.findViewById(R.id.updateName) as EditText
        val edtdonationType = dialogView.findViewById(R.id.updateType) as EditText
        val edtdonationDecription = dialogView.findViewById(R.id.updateDescription) as EditText
        dialogBuilder.setTitle("Update Your Donation")
        dialogBuilder.setMessage("Enter data below")
        dialogBuilder.setPositiveButton("Update", DialogInterface.OnClickListener { _, _ ->
            val updateId = edtDonationId.text.toString()
            val updateName = edtdonationName.text.toString()
            val updateType = edtdonationType.text.toString()
            val updateDescription = edtdonationDecription.text.toString()
            val databaseHandler: DatabaseHandler = DatabaseHandler(this)
            if (updateId.trim() != "" && updateName.trim() != "" && updateType.trim() != "" && updateDescription.trim() != "") {
                val status = databaseHandler.updateDonation(
                    Model.Donation(
                        Integer.parseInt(updateId),
                        updateName,
                        updateType,
                        updateDescription
                    )
                )
                if (status > -1) {
                    Toast.makeText(applicationContext, "Record Update", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(applicationContext, "Fill Every Field", Toast.LENGTH_LONG).show()
            }
        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ ->
        })
        val b = dialogBuilder.create()
        b.show()
    }

    //method for deleting records based on id
    fun deleteRecord(view: View) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.deletedialog, null)
        dialogBuilder.setView(dialogView)

        val dltId = dialogView.findViewById(R.id.deleteId) as EditText
        dialogBuilder.setTitle("Delete Record")
        dialogBuilder.setMessage("Enter Name Below")
        dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->

            val deleteId = dltId.text.toString()
            val databaseHandler: DatabaseHandler = DatabaseHandler(this)
            if (deleteId.trim() != "") {
                val status = databaseHandler.deleteDonation(
                    Model.Donation(
                        Integer.parseInt(deleteId),
                        "",
                        "",
                        ""
                    )
                )
                if (status > -1) {
                    Toast.makeText(applicationContext, "record deleted", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "id or name or email cannot be blank",
                    Toast.LENGTH_LONG
                ).show()
            }

        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ ->

        })
        val b = dialogBuilder.create()
        b.show()
    }

    fun onRadioButtonClicked(view: View) {}
}