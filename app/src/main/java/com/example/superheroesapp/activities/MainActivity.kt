package com.example.superheroesapp.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.superheroesapp.R
import com.example.superheroesapp.adapters.SuperheroAdapter
import com.example.superheroesapp.data.SuperheroApiService
import com.example.superheroesapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.lang.Exception
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    lateinit var adapter: SuperheroAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        //super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SuperheroAdapter()

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        searchByName("super")
    }

    private fun searchByName(query: String){
        //segundo plano ó hilo secundario paralelo
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = getRetrofit().create(SuperheroApiService::class.java)
                val result = apiService.findSuperheroesByName(query)

                runOnUiThread {
                    adapter.updateData(result.results)
                }

            }catch (e: Exception){
                e.printStackTrace()
            }
        }

    }

    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/api/2ba7f57e36c5a4d961aa9ee05639d6d6/")
            //.client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}