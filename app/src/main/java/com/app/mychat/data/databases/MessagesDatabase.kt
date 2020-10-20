package com.app.mychat.data.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.mychat.data.entitites.Messages
import com.app.mychat.data.daos.MessagesDao

@Database(entities = [Messages::class], version = 1, exportSchema = false)
abstract class MessagesDatabase: RoomDatabase() {

    abstract fun messagesDao(): MessagesDao

    companion object{
        @Volatile //make visible to other threads
        private var INSTANCE: MessagesDatabase? = null

        fun getDatabase(context: Context): MessagesDatabase {
            val tempInstance =
                INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance  = Room.databaseBuilder(
                    context.applicationContext,
                    MessagesDatabase::class.java,
                    "messages_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}

