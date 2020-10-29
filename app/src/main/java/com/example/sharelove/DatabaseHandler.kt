package com.example.sharelove

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context):
        SQLiteOpenHelper(context, DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "userDetails"
        private const val TABLE_USER_DATA = "USERDATA"
        private const val KEY_ID = "_id"
        private const val KEY_NAME = "name"
        private const val KEY_EMAIL = "email"
        private const val KEY_PASS = "password"
        private const val KEY_PHONE = "phoneNumber"
        private const val KEY_USER = "userType"
        private const val KEY_ADDRESS = "address"
        private const val KEY_CITY = "city"
        private const val KEY_STATE = "state"
        private const val KEY_COUNTRY = "country"
        private const val KEY_PINCODE = "pincode"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields
        val CREATE_TABLE_USER = ("CREATE TABLE " + TABLE_USER_DATA + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_PASS + " TEXT,"
                + KEY_PHONE + " INTEGER,"
                + KEY_USER + " TEXT,"
                + KEY_ADDRESS + " TEXT,"
                + KEY_CITY + " TEXT,"
                + KEY_STATE + " TEXT,"
                + KEY_COUNTRY + " TEXT,"
                + KEY_PINCODE + " INTEGER" + ")")
        db?.execSQL(CREATE_TABLE_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_USER_DATA")
        onCreate(db)
    }

    //INSERTION OF ROW
    fun addUser(user: UserClass): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, user.name)
        contentValues.put(KEY_EMAIL, user.email)
        contentValues.put(KEY_PASS, user.password)
        contentValues.put(KEY_PHONE, user.phoneNumber)
        contentValues.put(KEY_USER, user.userType)
        contentValues.put(KEY_ADDRESS, user.address)
        contentValues.put(KEY_CITY, user.city)
        contentValues.put(KEY_STATE, user.state)
        contentValues.put(KEY_COUNTRY, user.country)
        contentValues.put(KEY_PINCODE, user.pincode)
        val success = db.insert(TABLE_USER_DATA, null, contentValues)
        db.close()
        return success // closing database connection
    }

    // to voew
    fun viewUser(): ArrayList<UserClass> {
        val userList: ArrayList<UserClass> = ArrayList<UserClass>()
        val selectQuery = "SELECT* FROM $TABLE_USER_DATA"
        val db = this.readableDatabase
        var cursor:
                Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                var id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                var name = cursor.getString(cursor.getColumnIndex(KEY_NAME))
                var email = cursor.getString(cursor.getColumnIndex(KEY_EMAIL))
                var password = cursor.getString(cursor.getColumnIndex(KEY_PASS))
                var phoneNumber = cursor.getString(cursor.getColumnIndex(KEY_PHONE))
                var userType = cursor.getString(cursor.getColumnIndex(KEY_USER))
                var address = cursor.getString(cursor.getColumnIndex(KEY_ADDRESS))
                var city = cursor.getString(cursor.getColumnIndex(KEY_CITY))
                var state = cursor.getString(cursor.getColumnIndex(KEY_STATE))
                var country = cursor.getString(cursor.getColumnIndex(KEY_COUNTRY))
                var pincode = cursor.getString(cursor.getColumnIndex(KEY_PINCODE))

                val user = UserClass(
                    id = id, name = name, email = email, password = password, phoneNumber = phoneNumber,
                    userType = userType, address = address,
                    city = city, state = state, country = country, pincode = pincode
                )
                userList.add(user)
            } while (cursor.moveToNext())
        }
        return userList
    }

    fun checkUser(): ArrayList<UserClass> {
        val userList: ArrayList<UserClass> = ArrayList<UserClass>()
        val selectQuery = "SELECT* FROM $TABLE_USER_DATA"
        val db = this.readableDatabase
        var cursor:
                Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                var id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                var name = cursor.getString(cursor.getColumnIndex(KEY_NAME))
                var email = cursor.getString(cursor.getColumnIndex(KEY_EMAIL))
                var password = cursor.getString(cursor.getColumnIndex(KEY_PASS))
                var phoneNumber = cursor.getString(cursor.getColumnIndex(KEY_PHONE))
                var userType = cursor.getString(cursor.getColumnIndex(KEY_USER))
                var address = cursor.getString(cursor.getColumnIndex(KEY_ADDRESS))
                var city = cursor.getString(cursor.getColumnIndex(KEY_CITY))
                var state = cursor.getString(cursor.getColumnIndex(KEY_STATE))
                var country = cursor.getString(cursor.getColumnIndex(KEY_COUNTRY))
                var pincode = cursor.getString(cursor.getColumnIndex(KEY_PINCODE))

                val user = UserClass(
                    id = id, name = name, email = email, password = password, phoneNumber = phoneNumber,
                    userType = userType, address = address,
                    city = city, state = state, country = country, pincode = pincode
                )
                userList.add(user)
            } while (cursor.moveToNext())
        }
        return userList
    }

    fun checkUserEmail(email: String): Boolean {
        val columns = arrayOf(KEY_ID)
        val db = this.readableDatabase
        val selection = "$KEY_EMAIL = ?"
        val selectionArgs = arrayOf(email)
        val cursor = db.query(
            TABLE_USER_DATA,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0) {
            return true
        }
        return false
    }

    fun checkUserPhone(phoneNumber: String): Boolean {
        val columns = arrayOf(KEY_ID)
        val db = this.readableDatabase
        val selection = "$KEY_PHONE = ?"
        val selectionArgs = arrayOf(phoneNumber)
        val cursor = db.query(
            TABLE_USER_DATA,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0) {
            return true
        }
        return false
    }
    fun checkUserData(phoneNumber: String, password: String): Boolean {
        val columns = arrayOf(KEY_ID)
        val db = this.readableDatabase
        val selection = "$KEY_PHONE = ? AND $KEY_PASS = ?"
        val selectionArgs = arrayOf(phoneNumber, password)
        val cursor = db.query(
            TABLE_USER_DATA,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0)
            return false

            return true
        }

}


