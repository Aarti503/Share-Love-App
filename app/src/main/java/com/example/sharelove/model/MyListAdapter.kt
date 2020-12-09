package com.example.sharelove.model

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.sharelove.R

class MyListAdapter(
    private val context: Activity,
    private val donatorId: Array<String>,
    private val donatorName: Array<String>,
    private val donationTypeTEXT: Array<String>,
    private val donationDescription: Array<String>
) : ArrayAdapter<String>(context, R.layout.row, donatorName) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.row, null, true)

        val idText = rowView.findViewById(R.id.tvID) as TextView
        val nameText = rowView.findViewById(R.id.tvTitleName) as TextView
        val typeText = rowView.findViewById(R.id.tvType) as TextView
        val descText = rowView.findViewById(R.id.tvDesc) as TextView
        idText.text = "ID: ${donatorId[position]}"
        nameText.text = "Name: ${donatorName[position]}"
        typeText.text = "Type: ${donationTypeTEXT[position]}"
        descText.text = "Description: ${donationDescription[position]}"
        return rowView
    }
}