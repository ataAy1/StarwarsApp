package com.example.cleanarchk.presentation.CharacterData

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.example.cleanarchk.databinding.ItemFilmCardBinding
import com.example.cleanarchk.domain.model.Film

class FilmAdapter(private val films: List<Film>) :
    RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    inner class FilmViewHolder(private val binding: ItemFilmCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(film: Film) {
            Log.e("bakk13x", film.title.toString())
            binding.filmTitle.text = film.title
            binding.filmCreated.text = film.created
            binding.filmDirector.text = film.director
            binding.filmEdited.text = film.edited
            binding.filmProducer.text = film.producer
            binding.filmReleaseDate.text = film.release_date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        // Inflate the item layout and create the ViewHolder
        val binding =
            ItemFilmCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        // Bind the data at the specified position to the ViewHolder
        val film = films[position]
        Log.e("bakk13", "dasdas")

        holder.bind(film)
    }

    override fun getItemCount(): Int {
        // Return the size of the dataset (number of films)
        return films.size
    }
}