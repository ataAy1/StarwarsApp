package com.example.cleanarchk.presentation.CharacterList

interface SortOptionsListener {
    fun onSortOptionSelected(attribute: SortAttribute, sortOrder: SortOrder)
}

enum class SortAttribute {
    NAME,
    HEIGHT,
    MASS
}

enum class SortOrder {
    ASCENDING,
    DESCENDING
}