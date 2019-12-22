package com.example.recipeapp.model

import com.google.gson.annotations.SerializedName

data class RecipeList (
    @SerializedName("recipes")var resultsList: List<Recipe>
)