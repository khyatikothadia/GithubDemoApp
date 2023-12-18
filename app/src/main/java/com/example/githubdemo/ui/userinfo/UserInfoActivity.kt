package com.example.githubdemo.ui.userinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.githubdemo.R
import com.example.githubdemo.databinding.ActivityUserInfoBinding

/**
 * Launcher activity. Kept light and simple to delegate view logic to fragment(s) it attaches.
 */
class UserInfoActivity : AppCompatActivity() {

    private lateinit var activityUserInfoBinding: ActivityUserInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityUserInfoBinding = ActivityUserInfoBinding.inflate(layoutInflater)
        val view = activityUserInfoBinding.root
        setContentView(view)
        setSupportActionBar(activityUserInfoBinding.toolbar)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<UserInfoFragment>(R.id.fragment_container_view)
            }
        }
    }
}