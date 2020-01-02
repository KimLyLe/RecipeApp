package com.example.recipeapp.model

class RecipeRepository {

    private val recipeApi: RecipesApiService = RecipeApi.createApi()

    private val recipeApiSearch: RecipesApiService = RecipeApi.createApi()

    fun getRecipeList() = recipeApi.getRecipeList()

    fun getRecipeListSearch(searchInput: String) = recipeApiSearch.getRecipeListSearch(searchInput)
}