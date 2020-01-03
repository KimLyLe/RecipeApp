package com.example.recipeapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.model.*
import kotlinx.android.synthetic.main.item_ingredient.view.*
import kotlinx.android.synthetic.main.item_product.view.*

class IngredientAdapter(private val step: List<Step>)
    : RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_ingredient, parent, false)
        )
    }

    override fun getItemCount(): Int = step.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(step[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(step: Step) {
            //itemView.tvInstrNr.text = instruction.name
            //itemView.tvIngredients.text = step.steps[0].ingredients.toString()
            itemView.tvStep.text = step.steps
            //itemView.tvIngredients.text = ingredients.name
            //itemView.tvEquipment.text = equipment.name
        }
    }
}