package com.example.recipeapp.model

class RecipeRepository {

    private val recipeApi: RecipesApiService = RecipeApi.createApi()

    private val recipeApiSearch: RecipesApiService = RecipeApi.createApi()

    private val recipeApiIngredients : RecipesApiService = RecipeApi.createApi()

    fun getRecipeList() = recipeApi.getRecipeList()

    fun getRecipeListSearch(searchInput: String) = recipeApiSearch.getRecipeListSearch(searchInput)

    fun getRecipeIngredientsAndInstructions(recipeId: String) = recipeApiIngredients.getRecipeIngredientsAndInstructions(recipeId)
}