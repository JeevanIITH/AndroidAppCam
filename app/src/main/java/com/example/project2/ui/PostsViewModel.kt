package com.example.project2.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.project2.posts_data_classes.AllPosts
import com.example.project2.repositories.PostsRepository
import com.example.project2.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class PostsViewModel(val postsRepository: PostsRepository ): ViewModel() {

    val newposts: MutableLiveData<Resource<AllPosts>> = MutableLiveData()

    fun getAllPosts() = viewModelScope.launch {
        newposts.postValue(Resource.Loading())
        val response = postsRepository.getPosts()
        newposts.postValue(handleAllPosts(response))
    }

    private  fun handleAllPosts(response : Response<AllPosts>) : Resource<AllPosts>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return  Resource.Error(response.message())
    }


}