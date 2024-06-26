package com.example.superheroesapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.superheroesapp.R
import com.example.superheroesapp.adapters.SuperheroAdapter
import com.example.superheroesapp.adapters.SuperheroProvider
import com.example.superheroesapp.data.SuperheroApiService
import com.example.superheroesapp.data.SuperheroResponse
import com.example.superheroesapp.databinding.ActivityMainBinding
import com.example.superheroesapp.utils.RetrofitUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    lateinit var adapter: SuperheroAdapter

    var superheroList = emptyList<SuperheroResponse>()
    private var progressBar: ProgressBar? = null
    private var i = 0
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        //super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        superheroList= emptyList()
        adapter = SuperheroAdapter(superheroList){ opcionClick -> verDetalle( superheroList[opcionClick])}


        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)


        searchByName(SuperheroProvider.textoBuscar)
    }

    override fun onResume() {
        super.onResume()
        adapter.updateData(SuperheroProvider.findAll(), "")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //var inflater:MenuInflater = menuInflater
        //inflater.inflate(R.menu.menu_activity_main, menu)

        menuInflater.inflate(R.menu.menu_activity_main, menu)


        val searchViewItem = menu.findItem(R.id.buscar)
        val searchView = searchViewItem.actionView as androidx.appcompat.widget.SearchView
        searchView.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    searchByName(SuperheroProvider.textoBuscar)
                } else {
                    SuperheroProvider.textoBuscar = query
                    searchByName(query)
                }
                adapter.updateData(SuperheroProvider.findAll(),"")

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
        //return super.onCreateOptionsMenu(menu)
    }

    private fun searchByName(query: String){
        //segundo plano ó hilo secundario paralelo
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = RetrofitUtils.getRetrofit().create(SuperheroApiService::class.java)
                val result = apiService.findSuperheroesByName(query)

                runOnUiThread {
                    if (result.response == "success") {
                        superheroList = result.results
                        adapter.updateData(superheroList, query)
                    } else {
                        adapter.updateData(emptyList(), query)
                    }
                }

            }catch (e: Exception){
                e.printStackTrace()
            }
        }

    }

    //fun verDetalle(superheroDetalle: SuperheroResponse){
    fun verDetalle(superheroDetalle: SuperheroResponse){
        //Log.i("verTraza", getString(superheroDetalle.name))
        Toast.makeText(this,superheroDetalle.name, Toast.LENGTH_LONG).show()
        /********************/
        /*progressBar!!.visibility = View.VISIBLE

        i = progressBar!!.progress

        Thread(Runnable {
            // this loop will run until the value of i becomes 99
            while (i < 1000) {
                i += 1
                // Update the progress bar and display the current value
                handler.post(Runnable {
                    progressBar!!.progress = i
                    // setting current progress to the textview
                    //txtView!!.text = i.toString() + "/" + progressBar.max
                })
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

            // setting the visibility of the progressbar to invisible
            // or you can use View.GONE instead of invisible
            // View.GONE will remove the progressbar
            progressBar!!.visibility = View.INVISIBLE

        }).start()*/
        /********************/

        val intent : Intent = Intent(this, DetalleActivity::class.java) //::class.java
        intent.putExtra("HEROES_ID", superheroDetalle.id)


        //progressBar!!.visibility = View.GONE
        //si quieres ver la activity esto es necesario
        startActivity(intent)

    }

    //esta funcion se movió a RetrofitUtils para llamarse en comun desde Main y Detalle Activity
    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/api/2ba7f57e36c5a4d961aa9ee05639d6d6/")
            //.client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}