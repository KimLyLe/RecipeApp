package com.example.recipeapp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesApiService {

    // The GET method needs to retrieve the movies published in the given year
    @GET("random?number=10&apiKey=3459d326bd934141b86d2a1c72016792")
    fun getRecipeList(): Call<RecipeList>

    // The GET method needs to retrieve the movies published in the given year
    @GET("search?apiKey=3459d326bd934141b86d2a1c72016792")
    fun getRecipeListSearch(@Query(value="query", encoded=true) searchInput: String): Call<SearchResultsList>

}