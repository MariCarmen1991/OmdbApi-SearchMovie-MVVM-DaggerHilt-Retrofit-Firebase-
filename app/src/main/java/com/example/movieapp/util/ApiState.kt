package com.example.movieapp.util

import com.example.movieapp.data.model.FilmDetail
import com.example.movieapp.data.model.SearchResult


sealed class ApiState{

    object Loading: ApiState()

    class Failure(val message: Throwable):ApiState()
    class Succes(val data: SearchResult): ApiState()
    object Empty: ApiState()

    class FailureDetail(val message: Throwable):ApiState()

    class SuccesDetail(val data:FilmDetail): ApiState()

    class EmptyDetail(val emptyFilm: Boolean) : ApiState()


}
