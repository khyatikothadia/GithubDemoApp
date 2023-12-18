package com.example.githubdemo.ui.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.githubdemo.GithubApplication
import com.example.githubdemo.R
import com.example.githubdemo.data.model.UserInfo
import com.example.githubdemo.data.model.UserRepos
import com.example.githubdemo.databinding.FragmentUserInfoBinding
import com.example.githubdemo.ui.adapter.UserReposAdapter
import com.example.githubdemo.util.Constants.BUNDLE_REPO_DETAILS
import com.example.githubdemo.util.Constants.BUNDLE_TOTAL_FORKS_COUNT
import com.example.githubdemo.util.Utils
import com.example.githubdemo.viewmodel.UserInfoViewModel
import com.example.githubdemo.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

/**
 * User info fragment displaying and handling interactions with the view.
 */
class UserInfoFragment : Fragment(), OnItemClickListener {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    private lateinit var userInfoViewModel: UserInfoViewModel
    private lateinit var fragmentUserInfoBinding: FragmentUserInfoBinding
    private lateinit var userReposAdapter: UserReposAdapter
    private var lastEnteredUserId: String? = null
    private val forksCount = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize ViewModel
        (activity?.application as GithubApplication).applicationComponent.inject(this)
        userInfoViewModel =
            ViewModelProvider(this, viewModelProviderFactory)[UserInfoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        fragmentUserInfoBinding = FragmentUserInfoBinding.inflate(inflater, container, false)
        return fragmentUserInfoBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentUserInfoBinding.fragmentUserInfoBtnSearch.setOnClickListener {
            onSearchButtonClick()
        }
        setUpList()
        setUpObserver()
    }

    /**
     * Method to initialize recyclerview and user repos adapter
     */
    private fun setUpList() {
        val recyclerView = fragmentUserInfoBinding.fragmentUserInfoRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        userReposAdapter = UserReposAdapter(this)
        recyclerView.adapter = userReposAdapter
    }

    /**
     * Method to observe the livedata and updates the view
     */
    private fun setUpObserver() {
        // Observe LiveData
        userInfoViewModel.getUserInfoLiveData().observe(viewLifecycleOwner) { userInfo ->
            if (userInfo != null) {
                loadUserInfoWithAnim(userInfo)
            }
        }
        userInfoViewModel.getUserReposLiveData().observe(viewLifecycleOwner) { userRepos ->
            if (userRepos != null) {
                loadUserReposListWithAnim(userRepos)
            }
        }
    }

    /**
     * Display user info with animation
     *
     * @param userInfo User info details
     */
    private fun loadUserInfoWithAnim(userInfo: UserInfo) {
        fragmentUserInfoBinding.userInfoConstraintLayout.apply {
            visibility = View.VISIBLE
            Utils.animateView(this)
        }
        fragmentUserInfoBinding.fragmentUserInfoUserImage.load(userInfo.avatarUrl) {
            crossfade(true)
        }
        fragmentUserInfoBinding.fragmentUserInfoUserName.text = userInfo.name
    }

    /**
     * Display user repository details list with animation
     *
     * @param userReposList User repository details list
     */
    private fun loadUserReposListWithAnim(userReposList: List<UserRepos>) {
        fragmentUserInfoBinding.fragmentUserInfoRecyclerView.apply {
            visibility = View.VISIBLE
            Utils.animateView(this)
        }
        userReposAdapter.setResults(userReposList)
        forksCount.clear()
        userReposList.forEach { userRepos ->
            forksCount.add(userRepos.forks)
        }
    }

    /**
     * Handle search button click event
     */
    private fun onSearchButtonClick() {
        // Hide soft keyboard
        context?.let { view?.let { view -> Utils.hideKeyboardFrom(it, view) } }
        val userId = fragmentUserInfoBinding.fragmentUserInfoUserId.text.toString()

        //Check if entered userId is valid or not, if valid subscribe to viewModel to observe the data
        if (Utils.isValidString(userId)) {
            if (userId.isNotEmpty() && userId != lastEnteredUserId) {
                fragmentUserInfoBinding.fragmentUserInfoRecyclerView.visibility = INVISIBLE
                fragmentUserInfoBinding.userInfoConstraintLayout.visibility = INVISIBLE
                lastEnteredUserId = userId
                userInfoViewModel.getUserInfo(userId = userId.trim())
                userInfoViewModel.getUserReposDetails(userId = userId.trim())
            }
        } else {
            Toast.makeText(activity, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClick(userRepoDetails: UserRepos) {
        val bundle = Bundle()
        bundle.putParcelable(BUNDLE_REPO_DETAILS, userRepoDetails)
        bundle.putInt(BUNDLE_TOTAL_FORKS_COUNT, forksCount.sum())
        requireActivity().supportFragmentManager.commit {
            addToBackStack(null)
            replace<UserRepositoryDetailsFragment>(R.id.fragment_container_view, args = bundle)
        }
    }
}