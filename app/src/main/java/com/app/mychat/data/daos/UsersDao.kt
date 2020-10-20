package com.app.mychat.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.mychat.data.entitites.Users

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUsers(users: Users)

    @Query("SELECT * FROM users_table ORDER BY time DESC")
    fun readAllUsers(): LiveData<List<Users>>

    @Query("UPDATE users_table SET time= :time WHERE name= :name")
    fun updateTime(time: String, name: String)

    @Query("UPDATE users_table SET message= :message WHERE name= :name")
    fun getLastMessage(message: String, name: String)

}



