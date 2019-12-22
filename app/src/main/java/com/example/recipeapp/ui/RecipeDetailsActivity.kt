package com.example.recipeapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.model.Recipe
import kotlinx.android.synthetic.main.activity_recipe_details.*
import java.lang.Exception

class RecipeDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)
        initViews()
    }

    private fun initViews() {
        supportActionBar?.hide()
        val recipe = intent.extras
        if(recipe != null){
            val recipe: Recipe = recipe.get("Recipe") as Recipe
            Glide.with(this).load(recipe.image).into(ivRecipeDetails)
            tvRecipeTitle.text = recipe.title
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return try {
            finish()
            true
        } catch (e: Exception) {
            false
        }
    }

}
