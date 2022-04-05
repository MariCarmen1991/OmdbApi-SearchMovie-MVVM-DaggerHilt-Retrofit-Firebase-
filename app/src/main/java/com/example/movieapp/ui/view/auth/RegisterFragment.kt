package com.example.movieapp.ui.view.auth

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.movieapp.R
import com.example.movieapp.databinding.RegisterFragmentBinding
import com.example.movieapp.databinding.SearchFragmentBinding
import com.example.movieapp.ui.viewmodel.SearchViewModel
import com.example.movieapp.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.register_fragment.*

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.register_fragment) {

    private val userViewModel by viewModels<UserViewModel>()
    private lateinit var binding: RegisterFragmentBinding
    private lateinit var signUpEmail: EditText
    private lateinit var signUpPassword: EditText
    private lateinit var signUpName: EditText
    private lateinit var signUpButton: Button
    private lateinit var spinner: Spinner



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RegisterFragmentBinding.bind(view)

        signUpButton= binding.buttonSu
        signUpEmail= binding.SignUpEtEmailAddress
        signUpPassword= binding.signUpEtPassword
        signUpName=binding.SignUpName
        spinner=binding.spinner

        newUser()
        createSpinner()
    }

    private fun newUser(){


        signUpButton.setOnClickListener(View.OnClickListener {
            var email=signUpEmail.text.toString()
            var password=signUpPassword.text.toString()
            var name=signUpName.text.toString()

            Log.d("MariCarmen", "Nuevo Usuario")
            userViewModel.newUser(email,password,name)

            Log.d("MariCarmen", "res" + userViewModel.authenticated.value)

        })


    }


    fun createSpinner() {

        ArrayAdapter.createFromResource(
            requireActivity().applicationContext,
            R.array.actorsList,
            R.layout.spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)

                spinner.adapter = adapter


                spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {

                        userViewModel._resultPhoto.value=position


                        Log.d("MariCarmen", "posicion : " + position.toString())
                    }

                }
                adapter.setDropDownViewResource(R.layout.spinner_layout)


            }

    }




}