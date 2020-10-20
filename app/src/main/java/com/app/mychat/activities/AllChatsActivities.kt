package com.app.mychat.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mychat.R
import androidx.lifecycle.Observer
import com.app.mychat.adapters.AllChatsAdapter
import com.app.mychat.data.entitites.Users
import com.app.mychat.models.viewmodels.UsersViewModel

class AllChatsActivities : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_all_chats)

        var rName: String
        val adapter = AllChatsAdapter(applicationContext)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        layoutManager = LinearLayoutManager(this.applicationContext)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

        usersViewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
        usersViewModel.readAllUsers.observe(this, Observer { users ->
            adapter.setData(users)
        })

        val STRING_CHARACTERS = ('a'..'z').toList().toTypedArray()
        for (i in 1..200) {
            rName = (1..6).map { STRING_CHARACTERS.random() }.joinToString("")
            insertDataToDatabse(rName)
        }

    }

    private fun insertDataToDatabse(name: String) {

        val rName = name
        val time: String = ""
        val message = ""

        val user = Users(
            rName,
            time,
            message
        )
        usersViewModel.addUsers(user)
    }
}



