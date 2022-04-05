package com.example.movieapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.R
import com.example.movieapp.ui.view.auth.UserActivity
import com.example.movieapp.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)



        if(userViewModel.getPreferencesUser()==true){

            startActivity(Intent(this, MainActivity::class.java ))
        }
        else{
            startActivity(Intent(this, UserActivity::class.java ))

        }



    }

    override fun onResume() {
        super.onResume()

        finish()
    }
}

