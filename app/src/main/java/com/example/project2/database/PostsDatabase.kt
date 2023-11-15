package com.example.project2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.project2.posts_data_classes.Post


@Database(
    entities = [Post::class],
    version = 1
)
abstract class PostsDatabase : RoomDatabase() {
    abstract  fun getPostDao():PostsDAO

    //init object . get instance of this class .
    companion object {

        //Singleton pattern .
        //Lock is to avoid race condition.
        @Volatile   //write to this will immediately visible to all threads.
        private var instance : PostsDatabase ? = null
        private val LOCK = Any()

        //get new instance if not available .
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{ instance = it}

        }
        private fun createDatabase(context:Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PostsDatabase::class.java,
                name = "posts_db.db"
            ).build()
    }
}