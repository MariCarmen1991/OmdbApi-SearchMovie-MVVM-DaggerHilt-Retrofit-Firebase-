package com.example.movieapp.data.network.omdApi

import com.example.movieapp.data.model.Search
import com.example.movieapp.data.model.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmApiClient {

    @GET("/?apikey=39315eab&")
    suspend fun getAllFilms(@Query(value = "s") searchWord : String): Response<SearchResult>
}