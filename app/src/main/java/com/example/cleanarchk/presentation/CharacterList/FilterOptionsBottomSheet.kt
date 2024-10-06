package com.example.cleanarchk.presentation.CharacterList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleanarchk.R
import com.example.cleanarchk.databinding.FragmentFilterOptionsBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FilterOptionsBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding : FragmentFilterOptionsBottomSheetBinding

    interface FilterOptionsListener {
        fun onFilterOptionsSelected(gender: String, eyeColor: String)
    }

    private var filterOptionsListener: FilterOptionsListener? = null

    fun setFilterOptionsListener(listener: FilterOptionsListener) {
        this.filterOptionsListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilterOptionsBottomSheetBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btDone.setOnClickListener {
            val selectedGender: String = when (binding.radioGroupName.checkedRadioButtonId) {
                R.id.MaleGender -> "Male"
                R.id.femaleGender -> "Female"
                else -> ""
            }
            val enteredEyeColor: String = binding.etEyeColor.text.toString()

            filterOptionsListener?.onFilterOptionsSelected(selectedGender, enteredEyeColor)
            dismiss()
        }
    }
}