package com.example.movieapp.data.network.omdApi

import com.example.movieapp.data.model.SearchResult
import com.example.movieapp.data.network.omdApi.FilmService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

//Esta clase se conecta con el repositorio de datos, ya sea de una base de datos en internet como una base de datos local
class FilmRepository @Inject constructor(
    private val api: FilmService,


    ) {


    //Coroutines Flow

    fun getTheFilms(searchWord: String):
            Flow<SearchResult> {
        return flow { val searchResult= api.getFilms(searchWord)
        emit(searchResult)}.flowOn(Dispatchers.IO)

    }
}