package com.example.movieapp.ui.view

import android.app.Application
import android.icu.number.Notation.simple
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.movieapp.R
import com.example.movieapp.databinding.ProfileFragmentBinding
import com.example.movieapp.databinding.RegisterFragmentBinding
import com.example.movieapp.databinding.SearchFragmentBinding
import com.example.movieapp.ui.viewmodel.UserViewModel
import com.google.android.material.imageview.ShapeableImageView
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.android.synthetic.main.register_fragment.*

@AndroidEntryPoint
class ProfileFragment : Fragment( R.layout.profile_fragment) {

    private lateinit var binding: ProfileFragmentBinding
    private lateinit var nameUser: TextView
    private lateinit var mailUser: TextView

    private lateinit var image: ShapeableImageView


    private val userViewModel by viewModels<UserViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ProfileFragmentBinding.bind(view)
        nameUser=binding.nameTw
        image=binding.imageProfile
        mailUser=binding.mailUserTw


        setUserData()


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.profile_fragment, container, false)


        return view
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("Mari Carmen:", "12")
    }

    fun setUserData(){
        userViewModel.getDaoUsers()


        userViewModel._user.observe(viewLifecycleOwner, Observer {

            if(it!= null){

                if(it.photoType==1){
                    image.setImageResource(R.drawable.brad1)
                }


                nameUser.text=it.name
                mailUser.text=it.email

                Log.d("Mari Carmen:", "nam"+userViewModel.getDaoUsers() )




            }

        })

    }



















}