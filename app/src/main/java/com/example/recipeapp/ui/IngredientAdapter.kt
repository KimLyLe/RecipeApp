package com.example.recipeapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.model.Ingredient
import kotlinx.android.synthetic.main.item_ingredient.view.*

class IngredientAdapter(private val ingredient: List<Ingredient>)
    : RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_ingredient, parent, false)
        )
    }

    override fun getItemCount(): Int = ingredient.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(ingredient[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(ingredient: Ingredient) {
            itemView.tvIngredientsNr.text = "* " + ingredient.amount + " " + ingredient.unit + " " + ingredient.name
        }
    }
}