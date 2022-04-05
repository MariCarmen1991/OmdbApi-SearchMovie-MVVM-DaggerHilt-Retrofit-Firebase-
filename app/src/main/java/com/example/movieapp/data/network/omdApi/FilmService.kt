package com.example.movieapp.data.network.omdApi

import com.example.movieapp.data.model.SearchResult
import com.example.movieapp.data.network.omdApi.FilmApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
//Inyectamos la interfaz con Hilt y podemos usarla en la clase

class FilmService @Inject constructor(private val api: FilmApiClient) {

    //declaramos una función que devolverá la lista de films que se ejecutara en un hilo independiente (coroutines)

    suspend fun getFilms(searchWord: String): SearchResult {
        return withContext(Dispatchers.IO) {
            val response: Response<SearchResult> = api.getAllFilms(searchWord)
            response.body()!!
        }

    }






}