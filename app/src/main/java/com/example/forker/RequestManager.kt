package com.example.forker

import RandomRecipeApiResponse
import android.content.Context
import com.example.forker.listeners.RandomRecipeResponseListener
import com.google.android.gms.common.api.internal.ApiKey
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//Creating a request manager with retrofit

class RequestManager ( private val context: Context) {
    // Fetching initial request from the spoonacular api
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.spoonacular.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //Implementation of the interface to actually call 10 random recipes
    fun getRandomRecipes(listener: RandomRecipeResponseListener){
        val callRandomRecipes: CallRandomRecipes = retrofit.create(CallRandomRecipes::class.java)
        val call: Call<RandomRecipeApiResponse> = callRandomRecipes.callRandomRecipes(context.getString(R.string.api_key), "10")
        call.enqueue(object : Callback<RandomRecipeApiResponse>{
            override fun onResponse(
                call: Call<RandomRecipeApiResponse>,
                response: Response<RandomRecipeApiResponse>
            ) {
                //If response fails
               if(!response.isSuccessful){
                   listener.didError(response.message())
                   return
               }
                //IF response succeeds
                listener.didFetch(response.body()!!, response.message())
            }

            //Another condition when response fails
            override fun onFailure(call: Call<RandomRecipeApiResponse>, t: Throwable) {
                listener.didError(t.message!!)
            }

        })
    }

    //Interface that gets random recipes from spoonacular. Apikey and number of recipes is also specified
    private interface CallRandomRecipes{
        @GET("recipes/random")
        fun callRandomRecipes(
            @Query ("apiKey") apiKey: String,
            @Query ("number") number: String
        ): Call<RandomRecipeApiResponse>
    }
}