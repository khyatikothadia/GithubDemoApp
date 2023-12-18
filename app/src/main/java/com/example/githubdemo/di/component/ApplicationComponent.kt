package com.example.githubdemo.di.component

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.githubdemo.di.module.NetworkModule
import com.example.githubdemo.di.module.ViewModelModule
import com.example.githubdemo.ui.userinfo.UserInfoFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing Application scoped instances.
 */
@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(userInfoFragment: UserInfoFragment) // for field inject property inside the UserInfoFragment

    fun getMap(): Map<Class<*>, ViewModel>

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}