package com.example.movieapp.data.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.movieapp.data.network.omdApi.FilmApiClient
import com.example.movieapp.data.network.omdbApiDetail.FilmDetailApiClient
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)   //Alcance de toda la aplicacion ya que necesitaremos retrofit en toda la app
object NetworkModule {
    //proveer retrofit
    @Singleton //para que no instancie cada vez que necesitemos retrofit
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
//podemos pedir retrofit ya que la hemos creado anteriormente
    @Singleton
    @Provides
    fun provideFilmApiClient(retrofit: Retrofit): FilmApiClient {
        return retrofit.create(FilmApiClient::class.java)


    }

    @Singleton
    @Provides
    fun provideFilmDetailApiClient(retrofit: Retrofit): FilmDetailApiClient {
        return retrofit.create(FilmDetailApiClient::class.java)


    }

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth{
        return FirebaseAuth.getInstance()
    }


    @Singleton
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)


}