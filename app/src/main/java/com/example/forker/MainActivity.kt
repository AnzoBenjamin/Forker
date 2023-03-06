package com.example.forker


import RandomRecipeApiResponse
import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.forker.adapters.RandomRecipeAdapter
import com.example.forker.listeners.RandomRecipeResponseListener
import com.google.android.material.navigation.NavigationView



class MainActivity : AppCompatActivity(){
    lateinit var builder: AlertDialog.Builder
    lateinit var manager: RequestManager
    lateinit var randomRecipeAdapter: RandomRecipeAdapter
    lateinit var recyclerView: RecyclerView
    private lateinit var drawerLayout: DrawerLayout
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Gets the drawer layout
        drawerLayout = findViewById(R.id.drawer_layout)


        val navigationView: NavigationView = findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle navigation changes here
            true
        }

        //Gets the hamburger icon
        val hamburgerIcon: View = findViewById(R.id.hamburger_icon)

        //Allows the navigation to be shown and hidden when the hamburger is pressed
        hamburgerIcon.setOnClickListener {
            //Closes the navigation drawer
            if (drawerLayout.isDrawerOpen(navigationView)) {
                drawerLayout.closeDrawer(navigationView)
            }
            //Opens the navigation drawer
            else {
                drawerLayout.openDrawer(navigationView)
            }
        }

        builder = AlertDialog.Builder(this)
        builder.setCancelable(true)

        val dialog = builder.create()
        dialog.setTitle("Loading...")

        manager = RequestManager(this)
        manager.getRandomRecipes(listener)
        dialog.show()
        dialog.dismiss()
    }
    private val listener: RandomRecipeResponseListener = object : RandomRecipeResponseListener {
        override fun didFetch(response: RandomRecipeApiResponse, message: String) {
            recyclerView = findViewById(R.id.recipe_list)
            recyclerView.hasFixedSize()
            recyclerView.setLayoutManager(GridLayoutManager(this@MainActivity, 1))

            randomRecipeAdapter = RandomRecipeAdapter(this@MainActivity, response.recipes)
            recyclerView.adapter=randomRecipeAdapter
        }

        override fun didError(message: String) {
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        }
    }
}

