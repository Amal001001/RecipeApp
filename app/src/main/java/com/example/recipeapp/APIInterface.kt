package com.example.recipeapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {
    @GET("/recipes/")
    fun doGetRecipe(): Call<List<RecipeList.RecipeListItem>>?

    @POST("/recipes/")
    fun addRecipe(@Body recipe: RecipeList.RecipeListItem): Call<RecipeList>
}