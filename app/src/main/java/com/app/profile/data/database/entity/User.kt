package com.app.profile.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    var firstName: String,
    var lastName: String,
    var email: String,
    var phoneNumber: String,
    var password: String,
    var profilePic: String,
    var address: String
)