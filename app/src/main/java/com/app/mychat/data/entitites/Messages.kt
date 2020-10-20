package com.app.mychat.data.entitites

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "messages_table")
data class Messages(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "id") val id: Int,
    @ColumnInfo (name = "name") val name: String,
    @ColumnInfo (name = "message") val message: String,
    @ColumnInfo (name = "time") val time: String
)


