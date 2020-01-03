package com.example.recipeapp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipesApiService {

    @GET("random?number=10&apiKey=3459d326bd934141b86d2a1c72016792")
    fun getRecipeList(): Call<RecipeList>

    @GET("search?apiKey=3459d326bd934141b86d2a1c72016792")
    fun getRecipeListSearch(@Query(value="query", encoded=true) searchInput: String): Call<SearchResultsList>

    @GET("{recipeId}" + "/analyzedInstructions?apiKey=3459d326bd934141b86d2a1c72016792")
    fun getRecipeIngredientsAndInstructions(@Path("recipeId") recipeId: String): Call<List<Instruction>>

}