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

class IngredientAdapter(private val instruction: List<Instruction>)
    : RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_ingredient, parent, false)
        )
    }

    override fun getItemCount(): Int = instruction.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(instruction[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(instruction: Instruction) {
            //itemView.tvInstrNr.text = instruction.name
            //itemView.tvIngredients.text = step.steps[0].ingredients.toString()
            itemView.tvInstrNr.text = instruction.steps[0].toString()
            //itemView.tvIngredients.text = ingredients.name
            //itemView.tvEquipment.text = equipment.name
        }
    }
}