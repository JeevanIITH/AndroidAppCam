package com.example.project2.repositories

import com.example.project2.apis.ApiInstance
import com.example.project2.database.PostsDatabase

class PostsRepository(val db : PostsDatabase) {
    suspend fun  getPosts() =
        ApiInstance.api.getAllPosts()
}