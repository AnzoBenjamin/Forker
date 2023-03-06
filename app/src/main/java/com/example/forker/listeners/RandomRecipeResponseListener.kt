package com.example.forker.listeners

import RandomRecipeApiResponse

interface RandomRecipeResponseListener {
    fun didFetch(response: RandomRecipeApiResponse, message: String) {

    }
    fun didError(message: String) {
        
    }
}