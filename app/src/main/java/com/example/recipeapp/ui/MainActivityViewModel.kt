package com.example.recipeapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.recipeapp.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Integer.parseInt

class MainActivityViewModel(application: Application) : AndroidViewModel(application){

    private val recipeRepository = RecipeRepository()
    val recipe = MutableLiveData<List<Recipe>>()
    val error = MutableLiveData<String>()

    /**
     * Get a random number trivia from the repository using Retrofit.
     * onResponse if the response is successful populate the [trivia] object.
     * If the call encountered an error then populate the [error] object.
     */
    fun getRecipeListSearch(searchInput: String) {
        recipeRepository.getRecipeListSearch(searchInput).enqueue(object : Callback<SearchResultsList> {
            override fun onResponse(call: Call<SearchResultsList>, response: Response<SearchResultsList>) =
                if (response.isSuccessful) recipe.value = response.body()?.resultsList
                else error.value = "An error occurred: ${response.errorBody().toString()}"

            override fun onFailure(call: Call<SearchResultsList>, t: Throwable) {
                error.value = t.message
            }
        })
    }

    fun getRecipeList() {
        recipeRepository.getRecipeList().enqueue(object : Callback<RecipeList> {
            override fun onResponse(call: Call<RecipeList>, response: Response<RecipeList>) =
                if (response.isSuccessful) recipe.value = response.body()?.resultsList
                else error.value = "An error occurred: ${response.errorBody().toString()}"

            override fun onFailure(call: Call<RecipeList>, t: Throwable) {
                error.value = t.message
            }
        })
    }
}