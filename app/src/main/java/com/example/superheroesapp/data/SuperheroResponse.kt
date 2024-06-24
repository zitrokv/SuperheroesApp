package com.example.superheroesapp.data

import com.google.gson.annotations.SerializedName

data class SuperheroResponse(
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: Image
) {
}

data class Image(
    @SerializedName("url") val url: String

)

