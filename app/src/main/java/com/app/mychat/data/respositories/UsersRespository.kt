package com.app.mychat.data.respositories

import androidx.lifecycle.LiveData
import com.app.mychat.data.daos.UsersDao
import com.app.mychat.data.entitites.Users

class UsersRespository(private val usersDao: UsersDao) {

    val readAllUsers: LiveData<List<Users>> = usersDao.readAllUsers()

    suspend fun addUsers(users: Users) {
        usersDao.addUsers(users)
    }

    fun updateTime(time: String, name: String) {
        usersDao.updateTime(time, name)
    }

    fun getLastMessage(message: String, name: String) {
        usersDao.getLastMessage(message, name)
    }

}

