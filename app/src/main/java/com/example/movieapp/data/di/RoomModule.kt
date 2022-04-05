package com.example.movieapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.movieapp.data.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val USER_DATABASE_NAME="user_database"


    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context)= Room.databaseBuilder(context, UserDatabase::class.java, USER_DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

    @Singleton
    @Provides
    fun providesUserDao(db:UserDatabase)=db.getUserDao()


}