package com.example.movieapp.data.network.omdbApiDetail

import com.example.movieapp.data.model.FilmDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmDetailApiClient {

    @GET("/?apikey=39315eab&")
    suspend fun getDetailFilm(@Query(value = "t") titleFilm : String): Response<FilmDetail>
}