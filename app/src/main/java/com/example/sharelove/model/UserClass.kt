package com.example.sharelove.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

object Model {
    @Entity
    data class UserClass(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var name: String,
        var email: String,
        var password: String,
        var phoneNumber: String,
        var userType: String,
        var address: String,
        var city: String,
        var state: String,
        var country: String,
        var pincode: String
    )

    @Entity
    data class Donation(
        @PrimaryKey(autoGenerate = true)
        @ForeignKey(
            entity = UserClass::class,
            parentColumns = ["id"],
            childColumns = ["donationId"],
            onDelete = ForeignKey.CASCADE
        )
        var donationId: Int,
        val donatorName: String,
        val donationType: String,
        val donationDescription: String
    )
}
