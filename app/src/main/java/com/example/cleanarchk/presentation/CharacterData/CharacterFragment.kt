package com.example.cleanarchk.presentation.CharacterData

import android.app.BroadcastOptions.fromBundle
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchk.R
import com.example.cleanarchk.databinding.FragmentCharacterBinding
import com.example.cleanarchk.domain.model.Character
import com.example.cleanarchk.domain.model.Film
import com.example.cleanarchk.presentation.CharacterList.CharacterListFragment
import com.example.cleanarchk.presentation.StarWarsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CharacterFragment : Fragment() {

    lateinit var binding: FragmentCharacterBinding
    lateinit var character: com.example.cleanarchk.domain.model.Character
    lateinit var filmAdapter: FilmAdapter
    private val starWarsViewModel: StarWarsViewModel by viewModels()


    var filmsCurrentPage = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCharacterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("dene", arguments?.getStringArrayList("films").toString());
        val characterId = arguments?.getString("characterId") ?: ""
        val characterName = arguments?.getString("characterName") ?: ""
        val birthYear = arguments?.getString("birthYear") ?: ""
        val eyeColor = arguments?.getString("eyeColor") ?: ""
        val hairColor = arguments?.getString("hairColor") ?: ""
        val skinColor = arguments?.getString("skinColor") ?: ""
        val gender = arguments?.getString("gender") ?: ""
        val height = arguments?.getString("height") ?: ""
        val mass = arguments?.getString("mass") ?: ""
        val films = arguments?.getStringArrayList("films") ?: emptyList<String>()

        character = Character(
            characterID = characterId,
            characterName = characterName,
            birthYear = birthYear,
            eye_color = eyeColor,
            hairColor = hairColor,
            skin_color = skinColor,
            gender = gender,
            height = height,
            mass = mass,
            films = films
        )
        observeFilmList()


        updateUI(character)
        val startIndex = 0;
        val lastIndex =
            if (character.films.size <= 10) character.films.size else 10 * (filmsCurrentPage++)
        starWarsViewModel.fetchFilmListDetails(
            character.films.subList(
                startIndex,
                lastIndex
            )
        )
    }

    private fun setupUI(filmAdapter: FilmAdapter) {

        binding.recyclerFilms.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = filmAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val layoutManager = recyclerView.layoutManager as GridLayoutManager
                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                    val totalItemCount = layoutManager.itemCount

                    if (lastVisibleItemPosition == totalItemCount - 1) {
                        if (totalItemCount != character.films.size) {
                            val startIndex = (10 * filmsCurrentPage) + 1
                            var lastIndex = 10 * (filmsCurrentPage++)
                            if (character.films.size < lastIndex) {
                                lastIndex = character.films.size
                            }
                            starWarsViewModel.fetchFilmListDetails(
                                character.films.subList(
                                    startIndex,
                                    lastIndex
                                )
                            )
                        }
                    }
                }
            })
        }
    }

    private fun observeFilmList() {
        CoroutineScope(Dispatchers.Main).launch {
            starWarsViewModel.film.collect { filmstate ->
                if (filmstate.isLoading) {
                 binding.progressbar.visibility = View.VISIBLE
                } else if (filmstate.error.isNotBlank()) {
                    binding.progressbar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        filmstate.error,
                        Toast.LENGTH_LONG
                    )
                        .show()
                } else {
                    binding.progressbar.visibility = View.GONE
                    filmAdapter = FilmAdapter(filmstate.film)
                    setupUI(filmAdapter)
                    Log.e("bakk143", filmstate.film.toString())

                }
            }
        }
    }

    private fun updateUI(character: Character) {
        binding.textCharacterName.text = character.characterName
        binding.textBirthYear.text = "Birth Year: ${character.birthYear}"
        binding.textEyeColor.text = "Eye Color: ${character.eye_color}"
        binding.textGender.text = "Gender: ${character.gender}"
        binding.textHairColor.text = "Hair Color: ${character.hairColor}"
        binding.textHeight.text = "Height: ${character.height}"
        binding.textMass.text = "Mass: ${character.mass}"
        binding.textSkinColor.text = "Skin Color: ${character.skin_color}"
    }
}