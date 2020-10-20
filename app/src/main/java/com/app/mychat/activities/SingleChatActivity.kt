package com.app.mychat.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mychat.R
import com.app.mychat.adapters.SingleChatAdapter
import com.app.mychat.data.entitites.Messages
import com.app.mychat.models.modelfactory.ChatViewModelFactory
import com.app.mychat.models.viewmodels.MessagesViewModel
import com.app.mychat.models.viewmodels.UsersViewModel
import kotlinx.android.synthetic.main.activity_chat_main.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class SingleChatActivity : AppCompatActivity() {

    private lateinit var message: EditText
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_main)

        val back = findViewById<ImageView>(R.id.back)
        val name: TextView = findViewById(R.id.name)
        val send: ImageView = findViewById(R.id.send)
        message = findViewById(R.id.messageET)

        val sName: String

        if (TextUtils.isEmpty(name.text)) {
            val intent = getIntent()
            name.text = intent.getStringExtra("name")?.toString()
        }

        sName = name.text.toString()

        val adapter = SingleChatAdapter(applicationContext)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        layoutManager = LinearLayoutManager(this.applicationContext)
        layoutManager.stackFromEnd = true

        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

        usersViewModel = UsersViewModel(application)

        val messagesViewModel: MessagesViewModel by viewModels {
            ChatViewModelFactory(
                application,
                sName
            )
        }
        messagesViewModel.readAllData.observe(this, Observer { messages ->
            adapter.setData(messages)
        })

        send.setOnClickListener {
            insertDataToDatabse(messagesViewModel, sName, usersViewModel)
        }

        back.setOnClickListener {
            val i = Intent(applicationContext, AllChatsActivities::class.java)
            startActivity(i)
            finish()
        }
    }

    private fun insertDataToDatabse(
        messagesViewModel: MessagesViewModel,
        name: String,
        usersViewModel: UsersViewModel
    ) {

        val time: String
        val date1: String
        val messageString = message.text.toString()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            time = current.format(formatter)

        } else {
            val date = Date()
            val formatter = SimpleDateFormat("HH:mm:ss", Locale.US)
            time = formatter.format(date)
        }

        if (inputCheck(messageString)) {
            val message = Messages(
                0,
                name,
                messageString,
                time
            )

            messagesViewModel.addMessages(message)
            messageET.text.clear()

            usersViewModel.updateTime(time, name)
            usersViewModel.updateLastMessage(messageString, name)

        } else
            Toast.makeText(applicationContext, "enter a message", Toast.LENGTH_SHORT).show()
    }

    private fun inputCheck(message: String): Boolean {
        return !(TextUtils.isEmpty(message))
    }
}



