package com.example.movieapp.adapter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movieapp.ui.view.auth.RegisterFragment
import com.example.movieapp.ui.view.auth.SignInFragment



class AuthAdapter (fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle){


    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
       return  when(position){
            0 -> return SignInFragment()
            1 -> return RegisterFragment()
           else-> Fragment()

        }
    }




}