package com.example.superheroesapp.adapters

import com.example.superheroesapp.data.Biography
import com.example.superheroesapp.data.Image
import com.example.superheroesapp.data.SuperheroResponse

class SuperheroProvider {
    companion object{

        fun findAll() : List<SuperheroResponse>{
            return emptyList()
        }

       /* fun findById(id :Int) : SuperheroResponse{
            return  SuperheroResponse()
            val bio : Biography
            return SuperheroResponse(69, this.textoBuscar, Image(""), bio, )
        }*/

        var textoBuscar : String = "Super"

    }
}