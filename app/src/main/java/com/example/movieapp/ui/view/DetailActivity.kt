package com.example.movieapp.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroupOverlay
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.R
import com.example.movieapp.data.model.FilmDetail
import com.example.movieapp.databinding.DetailActivityBinding
import com.example.movieapp.ui.view.auth.UserActivity
import com.example.movieapp.ui.viewmodel.DetailFilmViewModel
import com.example.movieapp.util.ApiState
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.collect


class DetailActivity : AppCompatActivity() {

    private lateinit var binding : DetailActivityBinding
    private val viewModelDetailFilm by viewModels<DetailFilmViewModel>()
    private lateinit var filmObject : FilmDetail
    private lateinit var titleTw : TextView
    private lateinit var detailSheet: BottomSheetDialogFragment


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }




}





