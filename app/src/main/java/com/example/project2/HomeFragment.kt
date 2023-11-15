package com.example.project2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.project2.all_adapters.PostsAdapter
import com.example.project2.posts_data_classes.Post
import com.example.project2.ui.PostsViewModel
import com.example.project2.utils.Resource

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(R.layout.fragment_home) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var viewModel : PostsViewModel
    lateinit var postsAdapter: PostsAdapter
    lateinit var recyclerview : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home , container, false)
        recyclerview = view.findViewById(R.id.rvHomePosts)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).postsViewModel
        setupRecyclerView()

        viewModel.newposts.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    //hideProgressBar()
                    response.data?.let { postsResponse ->
//                      get all posts from postsResponse
                        val postsAcc = mutableListOf<Post>()
                        for (i in 0 until 5)
                            postsAcc.add(postsResponse[i].Post)
                        postsAdapter.differ.submitList((postsAcc))
                    }
                }

                else -> {}
            }

        })
    }

//    private  fun hideProgressBar(){
//        paginationProgressBar.visibility = View.INVISIBLE
//    }

    private fun setupRecyclerView(){
        postsAdapter = PostsAdapter()
        recyclerview.apply {
            adapter = postsAdapter
            layoutManager = LinearLayoutManager(activity)

        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}