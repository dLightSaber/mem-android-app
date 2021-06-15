package com.app.profile.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.profile.data.database.entity.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateUser(user: User)

    @Query("SELECT EXISTS (SELECT email FROM table_user WHERE email = :email)")
    fun checkForExistingUser(email: String): Boolean

    @Query("SELECT EXISTS (SELECT email, password FROM table_user WHERE email = :email and password = :password)")
    fun loginUser(email: String, password: String): Boolean

    @Query("SELECT * FROM table_user WHERE email = :email")
    fun getProfileData(email: String): User

    @Query("DELETE FROM table_user WHERE id  = :id ")
    fun deleteUser(id: Int)

    @Query("SELECT COUNT(*) FROM table_user")
    fun getCount(): Int

    @Query("DELETE FROM table_user")
    fun deleteAll()
}