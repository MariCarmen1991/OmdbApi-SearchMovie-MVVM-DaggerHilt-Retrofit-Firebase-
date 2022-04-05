package com.example.movieapp.ui.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.R
import com.example.movieapp.data.model.FilmDetail
import com.example.movieapp.databinding.DetailFilmsBottomSheetBinding
import com.example.movieapp.ui.viewmodel.DetailFilmViewModel
import com.example.movieapp.util.ApiState
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BottomDetailSheet: BottomSheetDialogFragment() {

    val viewModelDetailFilm by viewModels<DetailFilmViewModel>()
    private lateinit var filmObject: FilmDetail
    private var titleTw: TextView? = null
    private var descriptionTw: TextView? = null
    private lateinit var ratingBar: RatingBar
    private lateinit var imageFilm: ImageView

    private var inf: View? = null
    private lateinit var rootView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DetailFilmsBottomSheetBinding.inflate(inflater, container, false)
        binding.viewModelDetail = viewModelDetailFilm
        titleTw = binding.titleFilmTw
        descriptionTw=binding.description
        ratingBar= binding.ratingBar
        imageFilm=binding.imageViewId
        titleTw!!.text = "hello"
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentResultListener("titleKey") { requestKey, bundle ->

            val result = bundle.getString("bundleKey")
            Log.d("MariCarmen", "StringRecibida-" + result.toString())
            viewModelDetailFilm.getFilmResult(result!!)

            chargueDetailsFilms()

        }


    }

    companion object {

        fun newInstance(bundle: Bundle): BottomDetailSheet {
            val fragment = BottomDetailSheet()
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    private fun chargueDetailsFilms() {
        lifecycleScope.launchWhenCreated {
            viewModelDetailFilm._stateDetailFlow.collect {
                Log.d("MariCarmen", "CHARGUE")
                when (it) {
                    is ApiState.Loading -> {
                        //binding.srlFlowers.isRefreshing = true
                        Log.d("MariCarmen", "LOADING")
                    }
                    is ApiState.FailureDetail -> {
                        it.message.printStackTrace()
                        //binding.srlFlowers.isRefreshing = false
                        Log.d("MariCarmen", "3" + it.message)
                    }
                    is ApiState.SuccesDetail -> {

                        val myObj = it.data
                        Log.d("MariCarmen", "SUCCES" + it.data)
                        titleTw!!.text = myObj.title
                        descriptionTw!!.text=myObj.plot

                        var ratingFloat = myObj.imdbRating?.toFloatOrNull()


                        createRatingBar(ratingFloat)

                        Log.d("MariCarmen", "Numbers" +myObj.imdbRating)

                        Picasso.get()
                            .load(myObj.poster)
                            .placeholder(R.mipmap.ic_launcher)
                            .error(R.mipmap.ic_launcher_round)
                            .into(imageFilm)


                    }
                    is ApiState.Empty -> {
                        println("Empty...")
                    }
                }
            }


        }
    }


    private fun createRatingBar(ratingNum: Float?){

        ratingBar.apply {

            numStars=5
            stepSize=1.0f
            max=10
            rating= (ratingNum!!*5)/10


        }
    }





}


