package com.example.githubdemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubdemo.databinding.ItemUserReposDetailsBinding
import com.example.githubdemo.data.model.UserRepos
import com.example.githubdemo.ui.userinfo.OnItemClickListener

/**
 * Adapter for the list of [UserRepos].
 */
class UserReposAdapter(private val clickListener: OnItemClickListener) :
    RecyclerView.Adapter<UserReposAdapter.UserReposViewHolder>() {
    private var userRepoResults: List<UserRepos> = emptyList()

    /**
     * Method to update the results and notify the recycler view
     *
     * @param results User search result
     */
    fun setResults(results: List<UserRepos>) {
        userRepoResults = results
        notifyDataSetChanged()
    }

    /**
     * Create new views
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserReposViewHolder {
        val view =
            ItemUserReposDetailsBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
        return UserReposViewHolder(view)
    }

    /**
     * Return the size of the dataset
     */
    override fun getItemCount(): Int {
        return userRepoResults.size
    }

    /**
     * Replace the contents of a view
     */
    override fun onBindViewHolder(holder: UserReposViewHolder, position: Int) {
        holder.repoName.text = userRepoResults[position].name
        holder.description.text = userRepoResults[position].description
        holder.itemView.setOnClickListener {
            clickListener.onItemClick(userRepoResults[position])
        }
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class UserReposViewHolder(binding: ItemUserReposDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val repoName: TextView = binding.itemUserReposDetailsName
        val description: TextView = binding.itemUserReposDetailsDescription
    }
}