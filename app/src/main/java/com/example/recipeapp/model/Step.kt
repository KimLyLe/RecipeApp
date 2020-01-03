package com.example.recipeapp.model

import com.google.gson.annotations.SerializedName

data class Step (
    @SerializedName("number")var number: String,
    @SerializedName("step")var steps: String,
    @SerializedName("ingredients")var ingredients: List<Ingredient>,
    @SerializedName("equipment")var equipment: List<Equipment>
)