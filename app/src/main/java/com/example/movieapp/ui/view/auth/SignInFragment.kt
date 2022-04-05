package com.example.movieapp.ui.view.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.movieapp.R
import com.example.movieapp.databinding.RegisterFragmentBinding
import com.example.movieapp.databinding.SigninFragmentBinding
import com.example.movieapp.ui.view.MainActivity
import com.example.movieapp.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.signin_fragment) {

    private val userViewModel by viewModels<UserViewModel>()

    private lateinit var binding: SigninFragmentBinding

    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var signInButton: Button
    private lateinit var remeberCheckBox: CheckBox


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SigninFragmentBinding.bind(view)

        emailEt= binding.etMail
        passwordEt= binding.etPassword
        signInButton= binding.signInButton
        remeberCheckBox= binding.rememberCheckId

       logIn()

    }

    private fun logIn(){
        signInButton.setOnClickListener(View.OnClickListener {
            var email=emailEt.text.toString()
            var password=passwordEt.text.toString()

            userViewModel.logIn(email, password)

            if(remeberCheckBox.isChecked){
                Log.d("MariCarmen","check: "+userViewModel.userId.value)
                val userId= userViewModel.userId.value
                userViewModel.rememberUser(userId.toString())

            }

            if(userViewModel.authenticated.value==true){

                startActivity(Intent(context, MainActivity::class.java ))

            }

        })

    }









}