package com.example.recipeapp.model

import com.google.gson.annotations.SerializedName

data class SearchResultsList (
    @SerializedName("results")var resultsList: List<Recipe>,
    @SerializedName("totalResults")var totalResults: String
)