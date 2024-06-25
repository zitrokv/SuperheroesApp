package com.example.superheroesapp.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.horoscopo.SessionManager
import com.example.superheroesapp.R
import com.example.superheroesapp.adapters.SuperheroProvider
import com.example.superheroesapp.data.SuperheroResponse
import com.example.superheroesapp.databinding.ActivityMainBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DetalleActivity : AppCompatActivity() {
    lateinit var bindingDetalle : ActivityMainBinding
    lateinit var sesion: SessionManager

    lateinit var detalleActivity: SuperheroResponse
    lateinit var textoDelDia : TextView
    lateinit var MenuItemFavorito : MenuItem

    var esFavorito = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDetalle = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingDetalle.root)

        sesion = SessionManager(this)

        val id = intent.getStringExtra("SUPERHEROE_ID")
        //val id = intent.getIntExtra("HOROSCOPO_ID",-1)
        //detalleActivity = SuperheroProvider.findById(id!!)

        //findViewById<TextView>(R.id.DetalleTextView).setText(id)
        /*findViewById<TextView>(R.id.DetalleTextView).setText(detalleHoroscopo.nombre)
        findViewById<ImageView>(R.id.detalleImageView).setImageResource(detalleHoroscopo.logo)
        findViewById<ImageView>(R.id.waterImageView).setImageResource(detalleHoroscopo.logo)
        findViewById<ImageView>(R.id.waterImageView).imageAlpha = 28
        findViewById<ImageView>(R.id.waterImageView).setBackgroundColor(255)
        findViewById<TextView>(R.id.prediccionTextView).setText(
            LocalDate.now().format(
            DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString())*/

        //icono en menu detalle
        //esFavorito = sesion.obtenerHoroscopoFavorito()?.equals(detalleHoroscopo.id) ?: false

        //textoDelDia = findViewById(R.id.textoDelDia)
        //supportActionBar?.setTitle(detalleHoroscopo.nombre)
        //supportActionBar?.setSubtitle(detalleHoroscopo.fecha)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //getDailyHoroscope()

        /*ImagenFavorito.setOnClickListener{
            if (esFavorito)
            {
                sesion.enviarHoroscopoFavorito("")
            }
            else{
                sesion.enviarHoroscopoFavorito((detalleHoroscopo.id))
            }

            esFavorito = !esFavorito

        }*/

        //findViewById<TextView>(R.id.DetalleTextView).text = getString(detalleHoroscopo.nombre)
        //findViewById<ImageView>(R.id.detalleImageView).setImageResource(detalleHoroscopo.logo)

    }
}