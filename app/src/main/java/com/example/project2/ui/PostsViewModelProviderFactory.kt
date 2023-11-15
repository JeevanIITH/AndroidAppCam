package com.example.project2.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project2.repositories.PostsRepository

class PostsViewModelProviderFactory (val postsRepository: PostsRepository) : ViewModelProvider.NewInstanceFactory()  {

    //@Suppress(...names: "UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostsViewModel(postsRepository) as T
    }


}