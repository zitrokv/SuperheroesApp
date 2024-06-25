package com.example.horoscopo

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color

class SessionManager (context: Context) {

    companion object{
        const val SUPERHEROE_FAVORITO = "HOROSCOPO_FAVORITO"
    }
    private val sharedPrefs: SharedPreferences //  para guardar una variable en el SharedPreferences se accede a Editor --> SharedPreferences.Editor

    //no recibe parametros, se inicializa después del constructor (se podría inicializar prefs arriba)
    init{
        sharedPrefs = context.getSharedPreferences("horoscopo_session", Context.MODE_PRIVATE)

    }

    fun enviarFavorito(id: String){
        val editor = sharedPrefs.edit()
        editor.putString(SUPERHEROE_FAVORITO, id)
        editor.apply()

    }

    fun obtenerFavorito() : String? {
        return sharedPrefs.getString(SUPERHEROE_FAVORITO, null)
    }

    fun pintarFondoCelda() : Int
    {

        (sharedPrefs.getString(SUPERHEROE_FAVORITO, null)) ?: return Color.YELLOW
        return Color.TRANSPARENT
    }

    fun esFavorito(horoscopeId: String) : Boolean {
        return obtenerFavorito()?.equals(horoscopeId) ?: false
    }
}
