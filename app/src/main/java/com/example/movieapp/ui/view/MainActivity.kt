package com.example.movieapp.ui.view


import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.ui.viewmodel.DetailFilmViewModel
import com.example.movieapp.ui.viewmodel.SearchViewModel
import com.example.movieapp.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity()   {


    private lateinit var binding: ActivityMainBinding
    //ViewModel
    private val viewModel by viewModels<SearchViewModel>()
    private val detailviewModel by viewModels<DetailFilmViewModel>()
    private val userViewModel by viewModels<UserViewModel>()
    private lateinit var toolbar : Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolbar=binding.toolbar


        supportActionBar
        toolbar.inflateMenu(R.menu.main_menu)

        val searchItem = toolbar.menu.findItem(R.id.app_bar_search)


        Log.d("Mari Carmen:", "casa")

        if(searchItem!=null){

            val searchView =searchItem.actionView as SearchView

            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{

                override fun onQueryTextSubmit(query: String?): Boolean {

                    viewModel.getSearchResult(query!!)

                    sendTitle(query)
                    Log.d("Mari Carmen:", "casa"+query)

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })

        }

        chargueMenu()



    }








    fun chargueMenu(){

        //--------Toolbar Items-------------------

        toolbar.setOnMenuItemClickListener {


            when (it.itemId) {

                R.id.Profile -> {
                    val profileFragment = ProfileFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, profileFragment).commit()


                }
                R.id.logOut -> {
                    userViewModel.logOut()

                        userViewModel.forgetUser()
                       onBackPressed()

                    Log.d("Mari Carmen:", "l333")

                }
                R.id.Saved -> {
                    Log.d("Mari Carmen:", "kkk")

                }
            }
            true
        }





    }


    private fun sendTitle(sendSearch: String){

        val bundle = Bundle()
        bundle.putString("busqueda", sendSearch)
        val searchFragment = SearchFragment()
        searchFragment.setArguments(bundle)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, searchFragment).addToBackStack(null).commit()
    }






}

