package com.example.movieapp.ui.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.UserDatabase
import com.example.movieapp.data.dao.UserDao
import com.example.movieapp.data.database.entities.UserEntity
import com.example.movieapp.data.model.auth.User
import com.example.movieapp.data.network.auth.AuthFirebaseRepository
import com.example.movieapp.ui.view.MainActivity
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import android.content.SharedPreferences as SharedPreferences

@HiltViewModel
class UserViewModel @Inject constructor(
    val authRepository: AuthFirebaseRepository, private val sharedPreferences: SharedPreferences , val userDao: UserDao
) : ViewModel(){

    //input
    var _resultEmail = MutableLiveData<String>()
    var _resultPassword = MutableLiveData<String>()
    var _resultName = MutableLiveData<String>()
    var _resultPhoto = MutableLiveData<Int>()
    var authenticated = MutableLiveData<Boolean>()
    var userId=MutableLiveData<String>()
    var _user= MutableLiveData<UserEntity>()
    var register = MutableLiveData<Boolean>()



    fun logIn(email:String, password:String){
        _resultEmail.value=email
        _resultPassword.value=password




        if(email.isNullOrEmpty() || password.isNullOrEmpty()){

            Log.d("MariCarmen", "La autenticación ha fallado")
        }
        else{

            authRepository.login(email, password)
            authenticated.value=authRepository.doItLogInAuth()
            currentUser()

            Log.d("MariCarmen", "La autenticación se ha realizado")


        }

    }

    fun newUser(email: String, password: String, name:String){
        _resultEmail.value=email
        _resultPassword.value=password
        _resultName.value=name

        if(email.isNullOrEmpty() || password.isNullOrEmpty()){

            Log.d("MariCarmen", "Correo o Contraseña no válidos"
                    +email)
        }
        else{

            Log.d("MariCarmen", "Registro finalizado")

            var user  = UserEntity(0,"","","",0)
            user.email = authRepository.currentUser()?.email.toString()
            user.token = authRepository.currentUser()?.uid.toString()
            user.name = authRepository.currentUser()?.displayName.toString()
            user.photoType= _resultPhoto.value!!
            addUser(user)
            Log.d("MariCarmen", "userddd"+user.copy())


            authRepository.register(email, password, name)
        }




    }

    fun logOut(){
        authRepository.logOut()
        forgetUser()
    }

    fun currentUser(){

        userId.value= authRepository.currentUser()?.uid
        Log.d("MariCarmen", "userId: "+authRepository.currentUser()?.uid)

    }


    fun rememberUser(uidUser: String){
        sharedPreferences
        sharedPreferences.edit().putString("uidUser", uidUser).apply()

    }


    fun getPreferencesUser(): Boolean? {
        sharedPreferences
        if(sharedPreferences.contains("uidUser")){
            Log.d("MariCarmen", "EXISTE "+sharedPreferences.getString("uidUser", null))
            return true
        }
        else{
            return false
        }
    }

    fun forgetUser(){
        sharedPreferences.edit().clear().apply()
    }


    //DAO
    fun addUser(userAdd: UserEntity){

        viewModelScope.launch {
            userDao.insertUser(userAdd)
            Log.d("MariCarmen", "DAOUSERS "+userAdd.name)

        }
    }


    fun getDaoUsers(){
        viewModelScope.launch{
            _user.value=  userDao.getUser()

            println(userDao.getUser())


        }

    }

    fun update(id:Int, name: String, email: String){
        viewModelScope.launch {
            userDao.updateItem(id,name, email)

        }

    }






}


