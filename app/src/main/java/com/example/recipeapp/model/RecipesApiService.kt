package com.example.recipeapp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesApiService {

    // The GET method needs to retrieve the movies published in the given year
    @GET("random?number=10&tags=vegetarian&apiKey=3459d326bd934141b86d2a1c72016792")
    fun getRecipeList(): Call<RecipeList>

    // The GET method needs to retrieve the movies published in the given year
    @GET("movie?api_key=f896fbd8b02afe7da46fd7cdac552d39&language=en-US&sort_by=popularity.desc")
    fun getRecipeListSearch(@Query(value="year", encoded=true) year: Int): Call<RecipeList>

}