package com.example.recipeapp.ui

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.webkit.URLUtil
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.model.*
import kotlinx.android.synthetic.main.activity_recipe_details.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_step.*
import java.lang.Exception

class RecipeDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private val instruction = arrayListOf<Instruction>()
    private val ingredients = arrayListOf<Ingredient>()
    private val steps = arrayListOf<Step>()
    private lateinit var progressBar: ProgressBar
    private val stepAdapter = StepAdapter(steps)
    private val ingredientAdapter = IngredientAdapter(ingredients)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)
        initViewModel()
        initViews()
    }

    private fun initViews() {
        supportActionBar?.hide()
        val recipeIntent = intent.extras
        progressBar = ProgressBar(this)
        val colorCodeDark = Color.parseColor("#D81B60")
        progressBar.indeterminateTintList = ColorStateList.valueOf(colorCodeDark)
        val layoutParameters = RelativeLayout.LayoutParams(200, 200)
        layoutParameters.addRule(RelativeLayout.CENTER_IN_PARENT)
        detailsPage.addView(progressBar, layoutParameters)

        if(recipeIntent != null){
            val recipe: Recipe = recipeIntent.get("Recipe") as Recipe
            if (URLUtil.isValidUrl(recipe.image)) { Glide.with(this).load(recipe.image).into(ivRecipeDetails) }
            else { Glide.with(this).load("https://spoonacular.com/recipeImages/" +  recipe.image).into(ivRecipeDetails) }
            tvRecipeTitle.text = recipe.title
            tvRecipeInstructionsTitle.text = "Instructions"
            tvRecipeIngredientsTitle.text = "Ingredients"
            viewModel.getRecipeSteps(recipe.id)
            viewModel.getRecipeIngredientsAndInstructions(recipe.id)
            viewModel.getRecipeIngredients(recipe.id)
            rvInstructions.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            rvInstructions.adapter = stepAdapter

            rvIngredients.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            rvIngredients.adapter = ingredientAdapter
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.instruction.observe(this, Observer {
            instruction.clear()
            instruction.addAll(it)
            stepAdapter.notifyDataSetChanged()
        })
        viewModel.step.observe(this, Observer {
            steps.clear()
            steps.addAll(it)
            stepAdapter.notifyDataSetChanged()
        })

        viewModel.ingredient.observe(this, Observer {
            ingredients.clear()
            ingredients.addAll(it)
            ingredientAdapter.notifyDataSetChanged()
        })

        viewModel.isLoading.observe(this, Observer {
            if (it) {
                progressBar.visibility = ProgressBar.VISIBLE
            } else {
                progressBar.visibility = ProgressBar.GONE
            }
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
