package com.app.mychat.data.databases


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.mychat.data.entitites.Users
import com.app.mychat.data.daos.UsersDao

@Database(entities = [Users::class], version = 1, exportSchema = false)
abstract class UsersDatabase: RoomDatabase() {

    abstract fun usersDao(): UsersDao

    companion object{
        @Volatile //make visible to other threads
        private var INSTANCE: UsersDatabase? = null

        fun getDatabase(context: Context): UsersDatabase {
            val tempInstance =
                INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance  = Room.databaseBuilder(
                    context.applicationContext,
                    UsersDatabase::class.java,
                    "users_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}

