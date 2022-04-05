package com.example.movieapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Search(
    @SerializedName("Title") var title: String = "",
    @SerializedName("Year") val year: String = "",
    @SerializedName("imdbID") val imdbID: String = "",
    @SerializedName("Type") val type: String = "",
    @SerializedName("Poster") val poster: String=""


)