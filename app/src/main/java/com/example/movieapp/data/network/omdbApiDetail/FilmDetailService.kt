package com.example.movieapp.data.network.omdbApiDetail

import com.example.movieapp.data.model.FilmDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class FilmDetailService @Inject constructor( private val apiDetail: FilmDetailApiClient) {


    suspend fun getDetailFilm(title: String) :FilmDetail{
        return withContext(Dispatchers.IO){
            val response: Response<FilmDetail> = apiDetail.getDetailFilm(title)
            response.body()!!
        }
    }
}