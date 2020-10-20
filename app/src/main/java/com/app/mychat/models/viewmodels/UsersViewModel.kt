package com.app.mychat.models.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.app.mychat.data.databases.UsersDatabase
import com.app.mychat.data.entitites.Users
import com.app.mychat.data.respositories.UsersRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersViewModel(application: Application) : AndroidViewModel(application) {

    val readAllUsers: LiveData<List<Users>>
    val repository: UsersRespository

    init {
        val usersDao = UsersDatabase.getDatabase(application).usersDao()
        repository =
            UsersRespository(usersDao)
        readAllUsers = repository.readAllUsers
    }

    fun addUsers(users: Users) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUsers(users)
        }
    }

    fun updateTime(time: String, name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTime(time, name)
        }
    }

    fun updateLastMessage(message: String, name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getLastMessage(message, name)
        }
    }

}


