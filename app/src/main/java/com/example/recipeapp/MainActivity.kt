package com.example.recipeapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
//    private var recipeDetails: List<RecipeList.RecipeListItem>? = null
//    lateinit var rvMain: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val title = findViewById<View>(R.id.etTitle) as EditText
        val author = findViewById<View>(R.id.etAuthor) as EditText
        val inge = findViewById<View>(R.id.etIngr) as EditText
        val ins = findViewById<View>(R.id.etInst) as EditText
        val savebtn = findViewById<View>(R.id.badd) as Button
        val showbtn = findViewById<View>(R.id.bview) as Button

        savebtn.setOnClickListener {
            var f = RecipeList.RecipeListItem(
                title.text.toString(), author.text.toString(),
                inge.text.toString(), ins.text.toString()
            )

            addRecipe(f, onResult = {
                title.setText("")
                author.setText("")
                inge.setText("")
                ins.setText("")
                Toast.makeText(applicationContext, "Save Success!", Toast.LENGTH_SHORT).show()
            })
        }

        showbtn.setOnClickListener { rvIntent() }
        }

  fun addRecipe(recipe: RecipeList.RecipeListItem, onResult: (RecipeList?) -> Unit) {
    val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

    val progressDialog = ProgressDialog(this@MainActivity)
    progressDialog.setMessage("Please wait")
    progressDialog.show()

    if (apiInterface != null) {
        apiInterface.addRecipe(recipe).enqueue(object : Callback<RecipeList> {
            override fun onResponse(
                call: Call<RecipeList>,
                response: Response<RecipeList>
            ) {
                onResult(response.body())
                progressDialog.dismiss()
            }

            override fun onFailure(call: Call<RecipeList>, t: Throwable) {
                onResult(null)
                Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }
        })
    }
  }

  fun rvIntent() {
    intent = Intent(applicationContext, rvActivity::class.java)
    startActivity(intent)
  }
}
