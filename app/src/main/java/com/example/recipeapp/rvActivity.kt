package com.example.recipeapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_rv.*
import kotlinx.android.synthetic.main.recipe_row.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class rvActivity : AppCompatActivity() {
    var recipeDetails: List<RecipeList.RecipeListItem>? = null
    lateinit var card: CardView
    var recipes = arrayListOf<RecipeList.RecipeListItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv)

        val rvMain = findViewById<RecyclerView>(R.id.rvMain)
        rvMain.adapter = RecyclerViewAdapter(recipes)
        rvMain.layoutManager = LinearLayoutManager(this)


        getRecipes(onResult = {
            recipeDetails = it

            for (recipe in recipeDetails!!) {
                val title = recipe.title
                val author = recipe.author
                val ingr = recipe.ingredients
                val inst = recipe.instructions
                recipes.add(RecipeList.RecipeListItem(title,author,ingr,inst))
              //  card = findViewById(R.id.card)
              //  card.setOnClickListener {recipeCard()}
            }
            rvMain.adapter!!.notifyDataSetChanged()
        })

    }

    fun getRecipes(onResult: (List<RecipeList.RecipeListItem>?) -> Unit) {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait")
        progressDialog.show()
        if (apiInterface != null) {
            apiInterface.doGetRecipe()?.enqueue(object : Callback<List<RecipeList.RecipeListItem>> {
                override fun onResponse(
                    call: Call<List<RecipeList.RecipeListItem>>,
                    response: Response<List<RecipeList.RecipeListItem>>
                ) {
                    progressDialog.dismiss()
                    for (recipe in response.body()!!) {
                        val title = recipe.title
                        val author = "author: ${recipe.author}"
                        val ingr = "Ingredients:\n${recipe.ingredients}"
                        val inst = "Directions:\n${recipe.instructions}"
                        recipes.add(RecipeList.RecipeListItem(title,author,ingr,inst))
//                        card = findViewById(R.id.card)
//                        card.setOnClickListener {recipeCard()}
                    }
                    rvMain.adapter!!.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<List<RecipeList.RecipeListItem>>, t: Throwable) {
                    onResult(null)
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "" + t.message, Toast.LENGTH_SHORT).show();
                }

            })
        }
    }
}
