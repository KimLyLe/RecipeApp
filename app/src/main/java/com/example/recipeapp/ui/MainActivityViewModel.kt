package com.example.recipeapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.recipeapp.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Integer.parseInt

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val recipeRepository = RecipeRepository()
    val recipe = MutableLiveData<List<Recipe>>()
    val instruction = MutableLiveData<List<Instruction>>()
    val ingredient = MutableLiveData<List<Ingredient>>()
    val step = MutableLiveData<List<Step>>()
    val error = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>(false)


    /**
     * Get a random number trivia from the repository using Retrofit.
     * onResponse if the response is successful populate the [trivia] object.
     * If the call encountered an error then populate the [error] object.
     */
    fun getRecipeListSearch(searchInput: String) {
        isLoading.value = true
        recipeRepository.getRecipeListSearch(searchInput)
            .enqueue(object : Callback<SearchResultsList> {
                override fun onResponse(
                    call: Call<SearchResultsList>,
                    response: Response<SearchResultsList>
                ) {
                    isLoading.value = false
                    if (response.isSuccessful) recipe.value = response.body()?.resultsList
                    else error.value = "An error occurred: ${response.errorBody().toString()}"
                }
                override fun onFailure(call: Call<SearchResultsList>, t: Throwable) {
                    error.value = t.message
                    isLoading.value = false
                }
            })
    }

    fun getRecipeList() {
        isLoading.value = true
        recipeRepository.getRecipeList().enqueue(object : Callback<RecipeList> {
            override fun onResponse(call: Call<RecipeList>, response: Response<RecipeList>) {
                isLoading.value = false
                if (response.isSuccessful) recipe.value = response.body()?.resultsList
                else error.value = "An error occurred: ${response.errorBody().toString()}"
            }
            override fun onFailure(call: Call<RecipeList>, t: Throwable) {
                error.value = t.message
                isLoading.value = false
            }
        })
    }


    fun getRecipeIngredientsAndInstructions(recipeId: String) {
        isLoading.value = true
        recipeRepository.getRecipeIngredientsAndInstructions(recipeId)
            .enqueue(object : Callback<List<Instruction>> {
                override fun onResponse(
                    call: Call<List<Instruction>>,
                    response: Response<List<Instruction>>
                ) {
                    isLoading.value = false
                    if (response.isSuccessful) instruction.value = response.body()
                    else error.value = "An error occurred: ${response.errorBody().toString()}"
                }
                override fun onFailure(call: Call<List<Instruction>>, t: Throwable) {
                    error.value = t.message
                    isLoading.value = false
                }
            })
    }

    fun getRecipeSteps(recipeId: String) {
        isLoading.value = true
        recipeRepository.getRecipeSteps(recipeId).enqueue(object : Callback<List<Instruction>> {
            override fun onResponse(
                call: Call<List<Instruction>>,
                response: Response<List<Instruction>>
            ) {
                isLoading.value = false
                if (response.isSuccessful)
                    for (instruction in response.body()!!) {
                        step.value = instruction.steps
                    }
                else error.value = "An error occurred: ${response.errorBody().toString()}"
            }
            override fun onFailure(call: Call<List<Instruction>>, t: Throwable) {
                error.value = t.message
                isLoading.value = false
            }
        })
    }

    fun getRecipeIngredients(recipeId: String) {
        isLoading.value = true
        recipeRepository.getRecipeIngredients(recipeId).enqueue(object : Callback<ExtendedIngredients> {
                override fun onResponse(call: Call<ExtendedIngredients>, response: Response<ExtendedIngredients>) {
                    isLoading.value = false
                    if (response.isSuccessful) ingredient.value = response.body()?.ingredientsList
                    else error.value = "An error occurred: ${response.errorBody().toString()}"
                }
                override fun onFailure(call: Call<ExtendedIngredients>, t: Throwable) {
                    error.value = t.message
                    isLoading.value = false
                }
            })
    }
}