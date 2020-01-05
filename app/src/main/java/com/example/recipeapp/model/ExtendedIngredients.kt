package com.example.recipeapp.model

import com.google.gson.annotations.SerializedName

data class ExtendedIngredients (
    @SerializedName("extendedIngredients")var ingredientsList: List<Ingredient>
)