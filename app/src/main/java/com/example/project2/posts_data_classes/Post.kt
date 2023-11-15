package com.example.project2.posts_data_classes

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "Posts"
)
data class Post(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val ImageURL: String,
    val Likes: Int,
    val Location: String,
    val Post_Text: String,
    val Title: String
)