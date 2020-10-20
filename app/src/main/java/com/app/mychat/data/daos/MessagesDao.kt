package com.app.mychat.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.app.mychat.data.entitites.Messages

@Dao
interface MessagesDao {

    @Insert
    suspend fun addMessage (messages: Messages)

    @Query("SELECT * FROM messages_table WHERE name= :name ORDER BY id ASC")
    fun readAllData(name: String): LiveData<List<Messages>>
}





