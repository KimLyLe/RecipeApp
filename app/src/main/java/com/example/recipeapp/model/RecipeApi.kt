package com.example.recipeapp.model

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder

class RecipeApi {
    companion object {
        // The base url off the api.
        private const val baseUrl = "https://api.spoonacular.com/recipes/"

        /**
         * @return [RecipesApiService] The service class off the retrofit client.
         */
        fun createApi(): RecipesApiService {
            // Create an OkHttpClient to be able to make a log of the network traffic
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

// Create the Retrofit instance
            val recipeApi = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // Return the Retrofit NumbersApiService
            return recipeApi.create(RecipesApiService::class.java)
        }
    }
}