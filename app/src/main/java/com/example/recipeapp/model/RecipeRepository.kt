package com.example.recipeapp.model

class RecipeRepository {

    private val recipeApi: RecipesApiService = RecipeApi.createApi()


    fun getRecipeList() = recipeApi.getRecipeList()
}