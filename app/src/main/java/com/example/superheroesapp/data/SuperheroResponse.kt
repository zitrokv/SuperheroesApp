package com.example.superheroesapp.data

import com.google.gson.TypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import retrofit2.http.GET
import java.lang.Exception

data class SuperheroResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: Image,
    @SerializedName("biography") val biography: Biography,
    @SerializedName("appearance") val appearance: Appearance,
    val labelJSON : SuperheroPrueba
) {
}

data class SuperheroPrueba(
     val resultado: ReaderJsonValues<String>
)

data class Image(
    @SerializedName("url") val url: String
)

data class Biography(
    @SerializedName("full-name") val fullName: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("alignment") val alignment: String
)

data class Appearance(
    @SerializedName("gender") val gender: String,
    @SerializedName("race") val race: String

)

class Stats (
    @JsonAdapter(IntegerAdapter::class) @SerializedName("intelligence") val intelligence: Int,
    @JsonAdapter(IntegerAdapter::class) @SerializedName("strength") val strength: Int,
    @JsonAdapter(IntegerAdapter::class) @SerializedName("speed") val speed: Int,
    @JsonAdapter(IntegerAdapter::class) @SerializedName("durability") val durability: Int,
    @JsonAdapter(IntegerAdapter::class) @SerializedName("power") val power: Int,
    @JsonAdapter(IntegerAdapter::class) @SerializedName("combat") val combat: Int,
)


class ReaderJsonValues<T>(name: T) {
    val campoNombre: T
        get() {
            return this.campoNombre
        }

    companion object {
        @JsonAdapter(labelsJsonAdapter::class) var otro: String="hola"
    }

}

class IntegerAdapter : TypeAdapter<Int>() {
    override fun write(out: JsonWriter?, value: Int) {
        out?.value(value)
    }

    override fun read(valorJson: JsonReader?): Int {
        if (valorJson != null) {
            val value: String = valorJson.nextString()
            if (value != "null") {
                return value.toInt()
            }
        }
        return 0
    }

}

class labelsJsonAdapter : TypeAdapter<String>() {

    override fun write(out: JsonWriter?, value: String?) {
        out?.value(value)
    }

    override fun read(valorJson: JsonReader?): String {
        var value:String = ""
        try {

            if (valorJson != null) {
                value = valorJson.nextString()
                if (value != "null") {
                    return value
                }
            }
        }catch(ex:Exception){}

        finally {
            return value
        }


        return "nadita"
    }


}