package com.example.project2.apis

import com.example.project2.posts_data_classes.AllPosts
import retrofit2.Response
import retrofit2.http.GET

interface PostsApi {

    @GET("/posts/all_posts")
    suspend fun getAllPosts(
//    put query here.
    ):Response<AllPosts>

}