package com.example.project2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.project2.posts_data_classes.Post

@Dao
interface PostsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(post:Post):Long

    @Query("SELECT * FROM Posts")
    fun getAllPosts():LiveData<List<Post>>


}