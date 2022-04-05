package com.example.movieapp.ui.viewmodel


import android.util.Log
import androidx.lifecycle.*
import com.example.movieapp.data.model.FilmDetail
import com.example.movieapp.data.model.Search
import com.example.movieapp.data.network.omdApi.FilmRepository
import com.example.movieapp.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel  @Inject constructor(
    val filmRepository: FilmRepository
) : ViewModel() {

    private val stateFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    val _stateFlow: StateFlow<ApiState> = stateFlow
    private val _result=MutableLiveData<String>().apply { value="" }
    val listOfFilms= MutableLiveData<List<Search>>()


    fun getSearchResult(searchWord:String)= viewModelScope.launch {
        _result.value=searchWord
        stateFlow.value=ApiState.Loading
        filmRepository.getTheFilms(searchWord)
            .catch { e->
            stateFlow.value=ApiState.Failure(e) }
            .collect {
            data->stateFlow.value=ApiState.Succes(data)
                listOfFilms.value= data.Search!!
                Log.d("Mari Carmen:", "gg"+listOfFilms.value)

            }

    }

}











