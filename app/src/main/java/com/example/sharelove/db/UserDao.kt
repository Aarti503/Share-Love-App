package com.example.sharelove.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.sharelove.model.Model

@Dao
interface UserDao {


    @Insert
    fun addDonation(donation: Model.Donation)

    @Insert
    fun addUser(user: Model.UserClass)

    @Insert
    suspend fun addMultipleUsers(vararg user: Model.UserClass)

    @Update
    suspend fun updateUser(user: Model.UserClass)

    @Delete
    suspend fun deleteUser(user: Model.UserClass)


}

