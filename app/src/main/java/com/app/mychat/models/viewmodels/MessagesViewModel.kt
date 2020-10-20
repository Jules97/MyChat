package com.app.mychat.models.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.app.mychat.data.entitites.Messages
import com.app.mychat.data.databases.MessagesDatabase
import com.app.mychat.data.respositories.MessagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//provide data to ui
class MessagesViewModel(application: Application, name:String) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Messages>>
    private val repository: MessagesRepository

    init {
        val messagesDao = MessagesDatabase.getDatabase(application).messagesDao()
        repository =
            MessagesRepository(messagesDao)
        readAllData = repository.getMessages(name)
    }

    //launch in background thread
    fun addMessages(messages: Messages) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMessages(messages)
        }
    }

}



