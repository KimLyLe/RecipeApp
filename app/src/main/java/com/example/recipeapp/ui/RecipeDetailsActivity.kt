package com.example.recipeapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.webkit.URLUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.model.*
import kotlinx.android.synthetic.main.activity_recipe_details.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_ingredient.*
import java.lang.Exception

class RecipeDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private val instruction = arrayListOf<Instruction>()
    private val ingredients = arrayListOf<Ingredient>()
    private val steps = arrayListOf<Step>()
    private val equipment = arrayListOf<Equipment>()
    private val ingredientAdapter = IngredientAdapter(steps)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)
        initViewModel()
        initViews()
    }

    private fun initViews() {
        supportActionBar?.hide()
        val recipeIntent = intent.extras

        if(recipeIntent != null){
            val recipe: Recipe = recipeIntent.get("Recipe") as Recipe
            if (URLUtil.isValidUrl(recipe.image)) { Glide.with(this).load(recipe.image).into(ivRecipeDetails) }
            else { Glide.with(this).load("https://spoonacular.com/recipeImages/" +  recipe.image).into(ivRecipeDetails) }
            tvRecipeTitle.text = recipe.title
            tvRecipeInstructionsTitle.text = "Instructions"
            viewModel.getRecipeIngredientsAndInstructions(recipe.id)
            rvInstructions.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            rvInstructions.adapter = ingredientAdapter
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.instruction.observe(this, Observer {
            instruction.clear()
            instruction.addAll(it)
            ingredientAdapter.notifyDataSetChanged()
        })
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
