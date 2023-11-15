package com.example.project2.all_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.AsyncListUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project2.R
import com.example.project2.posts_data_classes.Post
import org.w3c.dom.Text

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostViewHolder>(){

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val postTitle: TextView
        val postDate : TextView
        val postImage : ImageView
        val postContent : TextView
        val postLikeButton : Button

        init {
            postTitle = itemView.findViewById(R.id.postTitle)
            postDate = itemView.findViewById(R.id.postDate)
            postImage = itemView.findViewById(R.id.postImageView)
            postContent = itemView.findViewById(R.id.postTextContent)
            postLikeButton = itemView.findViewById(R.id.postLikeButton)
        }
    }



    private val differCallback = object : DiffUtil.ItemCallback<Post>(){
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.post_layout,
            parent,
            false
        )

        return PostViewHolder(
         view
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val p = differ.currentList[position]
        holder.postTitle.text = p.Title
        holder.postDate.text = "No "
        Glide.with(holder.postImage).load(p.ImageURL)
        holder.postContent.text = p.Post_Text
        holder.postLikeButton.text = p.Likes.toString()

    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }





}