package com.example.movieapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int=0,
    @ColumnInfo(name = "Name") var name: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "token") var token: String,
    @ColumnInfo(name = "photoType") var photoType: Int=0



)


