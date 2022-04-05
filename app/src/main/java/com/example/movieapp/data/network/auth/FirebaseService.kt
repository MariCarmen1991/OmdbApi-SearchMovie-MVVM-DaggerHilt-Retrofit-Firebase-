package com.example.movieapp.data.network.auth

import android.app.Application
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.movieapp.ui.view.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.userProfileChangeRequest
import javax.inject.Inject


class FirebaseService @Inject constructor(  private val firebaseAuth: FirebaseAuth,  private val application: Application)  {



    private var doitRegister : Boolean = false
    private var doitAuth : Boolean = false
    private lateinit var user: FirebaseUser


    fun login(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){

                    user = firebaseAuth.currentUser!!
                    Log.d("UserAuth:", "signInWithEmail:success")
                    Toast.makeText( application.applicationContext, "Successful authentication: Welcome! ", Toast.LENGTH_SHORT).show()
                    doitAuth=true
                    val intent= Intent(application.applicationContext, MainActivity::class.java)
                    startActivity(application.applicationContext,intent, null)

                }else{

                    Toast.makeText( application.applicationContext, "Authentication failed if you don't have an account you can create one", Toast.LENGTH_SHORT).show()

                    Log.w("UserAuth", "signInWithEmail:failure", task.exception)
                }
            }

    }


    // REGISTRO

    fun newUser(email: String, password: String, name: String){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task->
                if (task.isSuccessful){
                    val nuser: FirebaseUser = firebaseAuth.currentUser!!
                    updateUser(nuser,name)


                    Toast.makeText( application.applicationContext, "Successful resgister: Welcome! ", Toast.LENGTH_SHORT).show()
                    doitRegister=true

                    Log.d("MariCarmen", "New User"+ nuser.email+ doitRegister +" name: "+nuser.displayName)
                    //pantalla a la que quieras dirigir
                }
            }.addOnFailureListener{exception->
                Log.w("UserAuth", "Register With Email:failure", exception)
                Toast.makeText( application.applicationContext, exception.toString(), Toast.LENGTH_SHORT).show()

            }

    }

    // LogOut

    fun updateUser(user: FirebaseUser, name:String){
        val userChangeRequest = userProfileChangeRequest {
            displayName = name

        }

        user.updateProfile(userChangeRequest)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("MariCarmen", "User profile updated."+user.displayName)
                }
            }


    }



    fun logOut()=  firebaseAuth.signOut()

    fun currentUser()= firebaseAuth.currentUser

    fun succesfulRegister()= doitRegister

    fun succesfulLogin()= doitAuth

    fun userId()= user

}