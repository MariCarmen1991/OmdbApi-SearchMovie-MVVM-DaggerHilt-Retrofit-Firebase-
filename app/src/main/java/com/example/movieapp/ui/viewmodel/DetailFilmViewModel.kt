package com.example.movieapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.model.FilmDetail
import com.example.movieapp.data.network.omdbApiDetail.FilmDetailRepository
import com.example.movieapp.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailFilmViewModel @Inject constructor( private val apiDetailRepository: FilmDetailRepository) :
    ViewModel(){


    private val stateDetailFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    val _stateDetailFlow: StateFlow<ApiState> = stateDetailFlow
    private val _resultTitle= MutableLiveData<String>().apply {value =""}
    val filmResult = MutableLiveData<FilmDetail>()
    val recibido= MutableLiveData<Boolean>()

    fun getFilmResult(title: String) = viewModelScope.launch{
        _resultTitle.value =title

        stateDetailFlow.value=ApiState.Loading

        apiDetailRepository.getDetailFilm(title)
            .catch {
                e-> stateDetailFlow.value = ApiState.FailureDetail(e)
            }
            .collect {

                data-> stateDetailFlow.value=ApiState.SuccesDetail(data)
                recibido.postValue(true)
                filmResult.value=data
                Log.d("MariCarmen", " data--: "+filmResult.value)
            }



    }



}