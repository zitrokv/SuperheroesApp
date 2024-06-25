package com.example.superheroesapp.adapters

import android.graphics.Color
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroesapp.data.SuperheroResponse
import com.example.superheroesapp.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperheroAdapter(private var dataSet: List<SuperheroResponse> = emptyList()) : RecyclerView.Adapter<SuperheroViewHolder>(){

    private var highlightText: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        val binding = ItemSuperheroBinding.inflate(LayoutInflater.from(parent.context))
        //return SuperheroViewHolder(ItemSuperheroBinding.bind(parent.rootView))
        return SuperheroViewHolder(binding)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        holder.render(dataSet[position])

        if (highlightText != null) {
            holder.highlight(highlightText!!)
        }

        //aqui declaramos el evento de click onItemClickListener
        //holder.itemView.setOnClickListener {onItemClickListener(position)}

    }

    fun updateData(dataSet: List<SuperheroResponse>, highlight: String? ){
        this.highlightText = highlight
        this.dataSet = dataSet
        notifyDataSetChanged()
    }


}

class SuperheroViewHolder(private val binding: ItemSuperheroBinding) : RecyclerView.ViewHolder(binding.root)
{
    fun render(superhero :SuperheroResponse){
        binding.nameTextView.text = superhero.name
        Picasso.get().load(superhero.image.url).into(binding.avatarImageView)
    }

    // Subraya el texto que coincide con la busqueda
    fun highlight(text: String) {
        try {
            val highlighted = binding.nameTextView.text.toString().highlight(text)
            //textView.text = highlighted
        } catch (e: Exception) { }
    }
    val imageUrl = "https://www.example.com/imagen.jpg"


    //ejemplo de extensión para el metodo String, que hace resaltar el texto buscado
    fun String.highlight(text: String) : SpannableString {
        val str = SpannableString(this)
        val startIndex = str.indexOf(text, 0, true)
        str.setSpan(BackgroundColorSpan(Color.rgb(244,144,255)), startIndex, startIndex + text.length, 0)
        return str
    }

}