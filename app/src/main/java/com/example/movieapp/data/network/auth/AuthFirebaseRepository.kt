package com.example.movieapp.data.network.auth

import com.example.movieapp.data.dao.UserDao
import com.example.movieapp.data.database.entities.UserEntity
import javax.inject.Inject

class AuthFirebaseRepository @Inject constructor( private val  firebase:FirebaseService, private val userDao: UserDao)  {


    fun login(email: String, password:String) = firebase.login(email, password)

    fun register(email: String, password: String, name:String)=firebase.newUser(email,password, name)

    fun logOut()= firebase.logOut()

    fun currentUser()=firebase.currentUser()


    fun doItRegisterAuth () = firebase.succesfulRegister()

    fun doItLogInAuth () = firebase.succesfulLogin()


    //room

    suspend fun insertUser(user:UserEntity){
        userDao.insertUser(user)
    }



}