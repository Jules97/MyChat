package com.app.mychat.data.respositories

import androidx.lifecycle.LiveData
import com.app.mychat.data.entitites.Messages
import com.app.mychat.data.daos.MessagesDao

//access multiple data sources
class MessagesRepository(private val messagesDao: MessagesDao) {

    fun getMessages(name:String):LiveData<List<Messages>>{
        return messagesDao.readAllData(name)
    }

    suspend fun addMessages(messages: Messages){
        messagesDao.addMessage(messages)
    }

}



