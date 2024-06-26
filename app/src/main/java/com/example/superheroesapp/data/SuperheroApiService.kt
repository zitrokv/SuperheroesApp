package com.example.superheroesapp.data

import retrofit2.http.GET
import retrofit2.http.Path

interface SuperheroApiService {
    @GET("search/{name}")
    suspend fun findSuperheroesByName(@Path("name") query: String): SuperheroListResponse

    @GET("{id}")
    suspend fun findSuperheroesById(@Path("id") id: Int): SuperheroResponse
}