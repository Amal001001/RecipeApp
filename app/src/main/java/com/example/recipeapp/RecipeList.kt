package com.example.recipeapp

import com.google.gson.annotations.SerializedName

class RecipeList  {

   var data: List<RecipeListItem>? = null

  class RecipeListItem {
    @SerializedName("author")
    var author: String? = null
    @SerializedName("ingredients")
    var ingredients: String? = null
    @SerializedName("instructions")
    var instructions: String? = null
    @SerializedName("title")
    var title: String? = null

    constructor(title: String?, author: String?, ingredients: String?, instructions: String?) {
     this.title = title
     this.author = author
     this.ingredients = ingredients
     this.instructions = instructions
    }

  }


}