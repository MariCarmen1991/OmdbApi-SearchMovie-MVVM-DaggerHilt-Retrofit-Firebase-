package com.example.movieapp.data.network.omdbApiDetail

import android.icu.text.CaseMap
import com.example.movieapp.data.model.FilmDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FilmDetailRepository @Inject constructor( private val apiDetail: FilmDetailService) {


    fun getDetailFilm (title: String) : Flow<FilmDetail> {

        return flow { val filmResult= apiDetail.getDetailFilm(title)
        emit(filmResult)}.flowOn(Dispatchers.IO)


    }
}