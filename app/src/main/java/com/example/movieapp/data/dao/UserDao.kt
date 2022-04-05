package com.example.movieapp.data.dao

import androidx.room.*
import com.example.movieapp.data.database.entities.UserEntity

@Dao
interface UserDao {

    @Query("SELECT*FROM user_table ORDER BY Name DESC ")
    suspend fun getUser():UserEntity


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user : UserEntity)


    @Query("UPDATE USER_TABLE SET name =:name, email= :email  WHERE id =:id")
    fun updateItem(id: Int, name: String, email: String): Int

}