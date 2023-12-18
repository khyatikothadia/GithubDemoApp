package com.example.githubdemo.ui.userinfo

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.githubdemo.data.model.UserRepos
import com.example.githubdemo.databinding.FragmentRepositoryDetailsBinding
import com.example.githubdemo.util.Constants.BUNDLE_REPO_DETAILS
import com.example.githubdemo.util.Constants.BUNDLE_TOTAL_FORKS_COUNT

/**
 * User repository details fragment displaying and handling interactions with the view.
 */
class UserRepositoryDetailsFragment : Fragment() {

    private lateinit var repositoryDetailsBinding: FragmentRepositoryDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        repositoryDetailsBinding =
            FragmentRepositoryDetailsBinding.inflate(inflater, container, false)
        return repositoryDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userReposDetails: UserRepos? = requireArguments().getParcelable(BUNDLE_REPO_DETAILS)
        val totalForks = requireArguments().getInt(BUNDLE_TOTAL_FORKS_COUNT)
        if (userReposDetails != null) {
            loadRepositoryDetails(userReposDetails, totalForks)
        }
    }

    /**
     * Display user repository details and total forks count
     *
     * @param userReposDetails User repository details
     * @param totalForks total forks count
     */
    private fun loadRepositoryDetails(userReposDetails: UserRepos, totalForks: Int) {
        repositoryDetailsBinding.repositoryDetail = userReposDetails
        if (totalForks > 5000) {
            repositoryDetailsBinding.fragmentRepositoryDetailsTotalForks.setTextColor(
                Color.parseColor(
                    "#FF0000"
                )
            )
        }
        repositoryDetailsBinding.fragmentRepositoryDetailsTotalForks.text =
            totalForks.toString()
    }
}