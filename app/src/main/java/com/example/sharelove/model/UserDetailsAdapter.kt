package com.example.sharelove.model

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import com.example.sharelove.R
import com.example.sharelove.activity.DescriptionActivity


class UserDetailsAdapter(
    private val context: Activity,
    private val donatorId: Array<String>,
    private val donatorName: Array<String>,
    private val donationTypeTEXT: Array<String>,
    private val donationDescription: Array<String>
) : ArrayAdapter<String>(context, R.layout.row2, donatorName) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.row2, null, true)
        val idText = rowView.findViewById(R.id.tvID) as TextView
        val nameText = rowView.findViewById(R.id.tvTitleName) as TextView
        val typeText = rowView.findViewById(R.id.tvType) as TextView
        val descText = rowView.findViewById(R.id.tvDesc) as TextView
        val viewDetails = rowView.findViewById(R.id.btnViewDetails) as Button
        idText.text = "ID: ${donatorId[position]}"
        nameText.text = "Name: ${donatorName[position]}"
        typeText.text = "Type: ${donationTypeTEXT[position]}"
        descText.text = "Description: ${donationDescription[position]}"
        viewDetails.text = "View Details"


        viewDetails.setOnClickListener {
            val intent = Intent(context, DescriptionActivity::class.java)
            intent.putExtra("name", donatorName[position]).toString()
            intent.putExtra("type", donationTypeTEXT[position]).toString()
            intent.putExtra("description", donationDescription[position]).toString()
            context.startActivity(intent)
            Toast.makeText(context, donatorName[position], Toast.LENGTH_SHORT)
                .show()
        }
        return rowView
    }


}

private operator fun TextView.get(position: Int): String {
    TODO("Not yet implemented")
}
