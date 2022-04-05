package com.example.movieapp.ui.view.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.adapter.AuthAdapter
import com.example.movieapp.databinding.ActivityUserBinding
import com.example.movieapp.ui.viewmodel.UserViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserActivity : AppCompatActivity() {
    private var tableLayout: TabLayout?=null
    lateinit var viewPager: ViewPager2

    private lateinit var binding: ActivityUserBinding


    private val userViewModel : UserViewModel by viewModels ()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = binding.viewPagerMenu
        tableLayout= binding.tabLayout

        setupViewPager(viewPager)


    }



     fun setupViewPager(viewPager2: ViewPager2) {


        viewPager2.adapter = AuthAdapter(supportFragmentManager,lifecycle)

         TabLayoutMediator(tableLayout!!, viewPager2){
             tab, position ->

             when(position){
                 0->tab.text = "SIGN IN"
                 1->tab.text = " REGISTER"

             }
         }.attach()

    }









}



