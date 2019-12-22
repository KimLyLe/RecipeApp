package com.example.recipeapp.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import kotlinx.android.synthetic.main.activity_recipe_details.view.*
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipeAdapter(private val recipes: List<Recipe>, private val onClick: (Recipe) -> Unit) :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false)
        )
    }

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(recipes[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { onClick(recipes[adapterPosition]) }
        }

        fun bind(recipe: Recipe) {
            Glide.with(context).load( recipe.image).into(itemView.ivRecipe)
            itemView.tvTitleHome.text = recipe.title
        }
    }

}