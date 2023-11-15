package com.example.project2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.project2.repositories.PostsRepository
import com.example.project2.database.PostsDatabase
import com.example.project2.ui.PostsViewModel
import com.example.project2.ui.PostsViewModelProviderFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private  lateinit var navController: NavController

    lateinit var postsViewModel: PostsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        setupWithNavController(bottomNavigationView , navController)



        val postsRepository = PostsRepository(PostsDatabase(this))
        val postViewModelProviderFactory = PostsViewModelProviderFactory(postsRepository)

        postsViewModel = ViewModelProvider(this,postViewModelProviderFactory).get(PostsViewModel::class.java)

    }
}