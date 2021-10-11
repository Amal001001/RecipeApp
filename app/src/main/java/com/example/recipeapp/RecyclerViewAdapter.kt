package com.example.recipeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recipe_row.view.*
import kotlinx.android.synthetic.main.recipe_row.view.tvrecipeAuthor
import kotlinx.android.synthetic.main.recipe_row.view.tvrecipeName

class RecyclerViewAdapter (var recipes: ArrayList<RecipeList.RecipeListItem>): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>(){
    class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recipe_row,parent,false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.itemView.apply {
            tvrecipeName.text = recipe.title
            tvrecipeAuthor.text = recipe.author
            tvrecipeIngr.text = recipe.ingredients
            tvrecipeInst.text = recipe.instructions
        }
    }

    override fun getItemCount() = recipes.size
    }
