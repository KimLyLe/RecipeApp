package com.example.recipeapp.model

import com.google.gson.annotations.SerializedName

data class Instruction (
    @SerializedName("name")var name: String,
    @SerializedName("steps")var steps: List<Step>
)