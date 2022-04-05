package com.example.movieapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.data.dao.UserDao
import com.example.movieapp.data.database.entities.UserEntity


@Database(entities = [UserEntity::class], version=2 )

abstract class UserDatabase: RoomDatabase() {


    abstract fun getUserDao(): UserDao


}