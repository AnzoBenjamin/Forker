package com.example.forker.adapters

import Recipes
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.forker.R
import com.squareup.picasso.Picasso

//
class RandomRecipeAdapter(private val context: Context, private val recipeList: List<Recipes>): RecyclerView.Adapter<RandomRecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomRecipeViewHolder {
        return RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.recipe_item, parent, false))
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: RandomRecipeViewHolder, position: Int) {
        holder.recipeTitle.text = recipeList[position].title
        holder.recipeTitle.isSelected = true
        holder.recipeRatings.text=recipeList[position].aggregateLikes.toString()+" Likes"
        holder.recipeServings.text = recipeList[position].servings.toString()+" Servings"
        holder.recipeTime.text=recipeList[position].readyInMinutes.toString() + " Minutes"

        Picasso.get().load(recipeList.get(position).image).into(holder.recipeImage)
    }
}
//Class that's supposed to hold requested data and populate xml items

class RandomRecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var recipeList: CardView = itemView.findViewById(R.id.recipe_card)
    var recipeTitle: TextView = itemView.findViewById(R.id.recipe_title)
    var recipeServings: TextView = itemView.findViewById(R.id.recipe_servings)
    var recipeRatings: TextView = itemView.findViewById(R.id.recipe_ratings)
    var recipeTime: TextView = itemView.findViewById(R.id.recipe_time)
    var recipeImage: ImageView = itemView.findViewById(R.id.recipe_image)
}