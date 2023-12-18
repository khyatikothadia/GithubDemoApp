package com.example.githubdemo.di.module

import androidx.lifecycle.ViewModel
import com.example.githubdemo.viewmodel.UserInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

/**
 * Dagger module responsible for providing dependencies related to ViewModels.
 */
@Module
abstract class ViewModelModule {

    /**
     * Binds the [UserInfoViewModel] to the [ViewModel] key and adds it to the map of ViewModels.
     *
     * @param userInfoViewModel The ViewModel instance to be bound.
     * @return The bound ViewModel instance.
     */
    @Binds
    @ClassKey(UserInfoViewModel::class)
    @IntoMap
    abstract fun userInfoViewModel(
        userInfoViewModel: UserInfoViewModel
    ): ViewModel
}